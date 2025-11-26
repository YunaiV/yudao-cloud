package cn.iocoder.yudao.module.mp.controller.admin.template;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import java.io.IOException;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.mp.controller.admin.template.vo.MsgTemplateLogPageReqVO;
import cn.iocoder.yudao.module.mp.controller.admin.template.vo.MsgTemplateLogRespVO;
import cn.iocoder.yudao.module.mp.controller.admin.template.vo.MsgTemplateLogSaveReqVO;
import cn.iocoder.yudao.module.mp.dal.dataobject.template.MsgTemplateLogDO;
import cn.iocoder.yudao.module.mp.service.template.MsgTemplateLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
/**
 * @author dengsl
 */
@Tag(name = "管理后台 - 微信模版消息发送记录")
@RestController
@RequestMapping("/mp/template/log")
@Validated
public class MsgTemplateLogController {

    @Resource
    private MsgTemplateLogService msgTemplateLogService;

    @PostMapping("/create")
    @Operation(summary = "创建微信模版消息发送记录")
    @PreAuthorize("@ss.hasPermission('mp:template-log:create')")
    public CommonResult<Long> createMsgTemplateLog(@Valid @RequestBody MsgTemplateLogSaveReqVO createReqVO) {
        return success(msgTemplateLogService.createMsgTemplateLog(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新微信模版消息发送记录")
    @PreAuthorize("@ss.hasPermission('mp:template-log:update')")
    public CommonResult<Boolean> updateMsgTemplateLog(@Valid @RequestBody MsgTemplateLogSaveReqVO updateReqVO) {
        msgTemplateLogService.updateMsgTemplateLog(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除微信模版消息发送记录")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('mp:template-log:delete')")
    public CommonResult<Boolean> deleteMsgTemplateLog(@RequestParam("id") Long id) {
        msgTemplateLogService.deleteMsgTemplateLog(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除微信模版消息发送记录")
                @PreAuthorize("@ss.hasPermission('mp:template-log:delete')")
    public CommonResult<Boolean> deleteMsgTemplateLogList(@RequestParam("ids") List<Long> ids) {
        msgTemplateLogService.deleteMsgTemplateLogListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得微信模版消息发送记录")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('mp:template-log:query')")
    public CommonResult<MsgTemplateLogRespVO> getMsgTemplateLog(@RequestParam("id") Long id) {
        MsgTemplateLogDO msgTemplateLog = msgTemplateLogService.getMsgTemplateLog(id);
        return success(BeanUtils.toBean(msgTemplateLog, MsgTemplateLogRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得微信模版消息发送记录分页")
    @PreAuthorize("@ss.hasPermission('mp:template-log:query')")
    public CommonResult<PageResult<MsgTemplateLogRespVO>> getMsgTemplateLogPage(@Valid MsgTemplateLogPageReqVO pageReqVO) {
        PageResult<MsgTemplateLogDO> pageResult = msgTemplateLogService.getMsgTemplateLogPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MsgTemplateLogRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出微信模版消息发送记录 Excel")
    @PreAuthorize("@ss.hasPermission('mp:template-log:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportMsgTemplateLogExcel(@Valid MsgTemplateLogPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<MsgTemplateLogDO> list = msgTemplateLogService.getMsgTemplateLogPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "微信模版消息发送记录.xls", "数据", MsgTemplateLogRespVO.class,
                        BeanUtils.toBean(list, MsgTemplateLogRespVO.class));
    }

}