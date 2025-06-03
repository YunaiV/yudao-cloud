package cn.iocoder.yudao.module.prod.controller.admin.tasklist;

import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.constraints.*;
import jakarta.validation.*;
import jakarta.servlet.http.*;
import java.util.*;
import java.util.stream.Collectors;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.prod.controller.admin.tasklist.vo.*;
import cn.iocoder.yudao.module.prod.dal.dataobject.tasklist.TaskListDO;
import cn.iocoder.yudao.module.prod.dal.dataobject.tasklist.InsertPointDO;
import cn.iocoder.yudao.module.prod.service.tasklist.TaskListService;

@Tag(name = "管理后台 - 任务列")
@RestController
@RequestMapping("/prod/task-list")
@Validated
public class TaskListController {

    @Resource
    private TaskListService taskListService;

    @PostMapping("/create")
    @Operation(summary = "创建任务列")
    @PreAuthorize("@ss.hasPermission('prod:task-list:create')")
    public CommonResult<Long> createTaskList(@Valid @RequestBody TaskListSaveReqVO createReqVO) {
        if (createReqVO.getTaskType() && !createReqVO.getInsertPoints().isEmpty()) {
            return CommonResult.error(1000020, "AI任务无需插入点");
        }
        return success(taskListService.createTaskList(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新任务列")
    @PreAuthorize("@ss.hasPermission('prod:task-list:update')")
    public CommonResult<Boolean> updateTaskList(@Valid @RequestBody TaskListSaveReqVO updateReqVO) {
        if (updateReqVO.getTaskType() && updateReqVO.getInsertPoints() != null && !updateReqVO.getInsertPoints().isEmpty()) {
            return CommonResult.error(1000020, "AI任务无需插入点");
        }
        taskListService.updateTaskList(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除任务列")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('prod:task-list:delete')")
    public CommonResult<Boolean> deleteTaskList(@RequestParam("id") Long id) {
        taskListService.deleteTaskList(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除任务列")
                @PreAuthorize("@ss.hasPermission('prod:task-list:delete')")
    public CommonResult<Boolean> deleteTaskListList(@RequestParam("ids") List<Long> ids) {
        taskListService.deleteTaskListListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得任务列")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('prod:task-list:query')")
    public CommonResult<TaskListRespVO> getTaskList(@RequestParam("id") Long id) {
        TaskListDO taskList = taskListService.getTaskList(id);
        TaskListRespVO respVO = BeanUtils.toBean(taskList, TaskListRespVO.class);
        
        // 获取插入点数据
        List<InsertPointDO> insertPoints = taskListService.getInsertPointsByTaskId(id);
        if (insertPoints != null && !insertPoints.isEmpty()) {
            List<TaskListRespVO.InsertPointRespVO> insertPointRespVOs = new ArrayList<>();
            
            if (!taskList.getTaskType()) {
                // 人工提示词：以key做分组，value以逗号分割
                Map<String, List<String>> groupedPoints = new HashMap<>();
                for (InsertPointDO insertPoint : insertPoints) {
                    String point = insertPoint.getPoint();
                    String content = insertPoint.getContent();
                    
                    groupedPoints.computeIfAbsent(point, k -> new ArrayList<>()).add(content);
                }
                
                // 转换为响应VO
                for (Map.Entry<String, List<String>> entry : groupedPoints.entrySet()) {
                    TaskListRespVO.InsertPointRespVO insertPointRespVO = new TaskListRespVO.InsertPointRespVO();
                    insertPointRespVO.setPoint(entry.getKey());
                    insertPointRespVO.setContent(String.join(",", entry.getValue()));
                    insertPointRespVOs.add(insertPointRespVO);
                }
            } else {
                // 插入点任务：直接显示
                for (InsertPointDO insertPoint : insertPoints) {
                    TaskListRespVO.InsertPointRespVO insertPointRespVO = new TaskListRespVO.InsertPointRespVO();
                    insertPointRespVO.setId(insertPoint.getId());
                    insertPointRespVO.setPoint(insertPoint.getPoint());
                    insertPointRespVO.setContent(insertPoint.getContent());
                    insertPointRespVOs.add(insertPointRespVO);
                }
            }
            
            respVO.setInsertPoints(insertPointRespVOs);
        }
        
        return success(respVO);
    }

    @GetMapping("/page")
    @Operation(summary = "获得任务列分页")
    @PreAuthorize("@ss.hasPermission('prod:task-list:query')")
    public CommonResult<PageResult<TaskListRespVO>> getTaskListPage(@Valid TaskListPageReqVO pageReqVO) {
        PageResult<TaskListDO> pageResult = taskListService.getTaskListPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, TaskListRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出任务列 Excel")
    @PreAuthorize("@ss.hasPermission('prod:task-list:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportTaskListExcel(@Valid TaskListPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<TaskListDO> list = taskListService.getTaskListPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "任务列.xls", "数据", TaskListRespVO.class,
                        BeanUtils.toBean(list, TaskListRespVO.class));
    }

    // ==================== 子表（插入点） ====================

    @GetMapping("/insert-point/page")
    @Operation(summary = "获得插入点分页")
    @Parameter(name = "id", description = "主键")
    @Parameter(name = "point", description = "插入点搜索")
    @PreAuthorize("@ss.hasPermission('prod:task-list:query')")
    public CommonResult<PageResult<InsertPointDO>> getInsertPointPage(PageParam pageReqVO,
                                                                     @RequestParam("id") Long id,
                                                                     @RequestParam(value = "point", required = false) String point) {
        if (point != null && !point.trim().isEmpty()) {
            return success(taskListService.getInsertPointPage(pageReqVO, id, point.trim()));
        } else {
            return success(taskListService.getInsertPointPage(pageReqVO, id));
        }
    }

    @PostMapping("/insert-point/create")
    @Operation(summary = "创建插入点")
    @PreAuthorize("@ss.hasPermission('prod:task-list:create')")
    public CommonResult<Long> createInsertPoint(@Valid @RequestBody InsertPointDO insertPoint) {
        return success(taskListService.createInsertPoint(insertPoint));
    }

    @PutMapping("/insert-point/update")
    @Operation(summary = "更新插入点")
    @PreAuthorize("@ss.hasPermission('prod:task-list:update')")
    public CommonResult<Boolean> updateInsertPoint(@Valid @RequestBody InsertPointDO insertPoint) {
        taskListService.updateInsertPoint(insertPoint);
        return success(true);
    }

    @DeleteMapping("/insert-point/delete")
    @Parameter(name = "id", description = "编号", required = true)
    @Operation(summary = "删除插入点")
    @PreAuthorize("@ss.hasPermission('prod:task-list:delete')")
    public CommonResult<Boolean> deleteInsertPoint(@RequestParam("id") Long id) {
        taskListService.deleteInsertPoint(id);
        return success(true);
    }

    @DeleteMapping("/insert-point/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除插入点")
    @PreAuthorize("@ss.hasPermission('prod:task-list:delete')")
    public CommonResult<Boolean> deleteInsertPointList(@RequestParam("ids") List<Long> ids) {
        taskListService.deleteInsertPointListByIds(ids);
        return success(true);
    }

	@GetMapping("/insert-point/get")
	@Operation(summary = "获得插入点")
	@Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('prod:task-list:query')")
	public CommonResult<InsertPointDO> getInsertPoint(@RequestParam("id") Long id) {
	    return success(taskListService.getInsertPoint(id));
	}

}