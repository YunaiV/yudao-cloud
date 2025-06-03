package cn.iocoder.yudao.module.prod.service.tasklist;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.prod.controller.admin.tasklist.vo.*;
import cn.iocoder.yudao.module.prod.dal.dataobject.tasklist.TaskListDO;
import cn.iocoder.yudao.module.prod.dal.dataobject.tasklist.InsertPointDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.prod.dal.mysql.tasklist.TaskListMapper;
import cn.iocoder.yudao.module.prod.dal.mysql.tasklist.InsertPointMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.prod.enums.ErrorCodeConstants.*;

/**
 * 任务列 Service 实现类
 *
 * @author 麦芽
 */
@Service
@Validated
public class TaskListServiceImpl implements TaskListService {

    @Resource
    private TaskListMapper taskListMapper;
    @Resource
    private InsertPointMapper insertPointMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createTaskList(TaskListSaveReqVO createReqVO) {
        // 插入任务列表
        TaskListDO taskList = BeanUtils.toBean(createReqVO, TaskListDO.class);
        taskListMapper.insert(taskList);
        
        // 处理插入点数据
        if (createReqVO.getInsertPoints() != null && !createReqVO.getInsertPoints().isEmpty()) {
            if (!createReqVO.getTaskType()) {
                // 人工提示词：以逗号分割替换内容，创建多行插入点
                createInsertPointsForManualPrompt(taskList.getId(), createReqVO.getInsertPoints());
            }
//            else if (createReqVO.getTaskType()) {
//                // 插入点任务：直接创建插入点
//                createInsertPoints(taskList.getId(), createReqVO.getInsertPoints());
//            }
        }
        
        // 返回
        return taskList.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateTaskList(TaskListSaveReqVO updateReqVO) {
        // 校验存在
        validateTaskListExists(updateReqVO.getId());
        
        // 更新任务列表
        TaskListDO updateObj = BeanUtils.toBean(updateReqVO, TaskListDO.class);
        taskListMapper.updateById(updateObj);
        
        // 处理插入点数据
        handleInsertPointsUpdate(updateReqVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteTaskList(Long id) {
        // 校验存在
        validateTaskListExists(id);
        // 删除
        taskListMapper.deleteById(id);

        // 删除子表
        deleteInsertPointByTaskId(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteTaskListListByIds(List<Long> ids) {
        // 校验存在
        validateTaskListExists(ids);
        // 删除
        taskListMapper.deleteByIds(ids);
    
        // 删除子表
        deleteInsertPointByTaskIds(ids);
    }

    private void validateTaskListExists(List<Long> ids) {
        List<TaskListDO> list = taskListMapper.selectByIds(ids);
        if (CollUtil.isEmpty(list) || list.size() != ids.size()) {
            throw exception(TASK_LIST_NOT_EXISTS);
        }
    }

    private void validateTaskListExists(Long id) {
        if (taskListMapper.selectById(id) == null) {
            throw exception(TASK_LIST_NOT_EXISTS);
        }
    }

    @Override
    public TaskListDO getTaskList(Long id) {
        return taskListMapper.selectById(id);
    }

    @Override
    public PageResult<TaskListDO> getTaskListPage(TaskListPageReqVO pageReqVO) {
        return taskListMapper.selectPage(pageReqVO);
    }

    // ==================== 子表（插入点） ====================

    @Override
    public PageResult<InsertPointDO> getInsertPointPage(PageParam pageReqVO, Long id) {
        return insertPointMapper.selectPage(pageReqVO, id);
    }

    @Override
    public PageResult<InsertPointDO> getInsertPointPage(PageParam pageReqVO, Long id, String point) {
        return insertPointMapper.selectPage(pageReqVO, id, point);
    }

    @Override
    public Long createInsertPoint(InsertPointDO insertPoint) {
        insertPointMapper.insert(insertPoint);
        return insertPoint.getId();
    }

    @Override
    public void updateInsertPoint(InsertPointDO insertPoint) {
        // 校验存在
        validateInsertPointExists(insertPoint.getId());
        // 更新
        insertPoint.clean(); // 解决更新情况下：updateTime 不更新
        insertPointMapper.updateById(insertPoint);
    }

    @Override
    public void deleteInsertPoint(Long id) {
        // 删除
        insertPointMapper.deleteById(id);
    }

    @Override
    public void deleteInsertPointListByIds(List<Long> ids) {
        // 删除
        insertPointMapper.deleteByIds(ids);
    }

    @Override
    public InsertPointDO getInsertPoint(Long id) {
        return insertPointMapper.selectById(id);
    }

    @Override
    public List<InsertPointDO> getInsertPointsByTaskId(Long taskId) {
        return insertPointMapper.selectByTaskId(taskId);
    }

    private void validateInsertPointExists(Long id) {
        if (insertPointMapper.selectById(id) == null) {
            throw exception(INSERT_POINT_NOT_EXISTS);
        }
    }

    private void deleteInsertPointByTaskId(Long id) {
        insertPointMapper.deleteByTaskId(id);
    }

    private void deleteInsertPointByTaskIds(List<Long> ids) {
        insertPointMapper.deleteByTaskIds(ids);
    }

    /**
     * 创建插入点列表
     */
    private void createInsertPoints(Long taskId, List<TaskListSaveReqVO.InsertPointReqVO> insertPointReqVOs) {
        List<InsertPointDO> insertPoints = new ArrayList<>();
        for (TaskListSaveReqVO.InsertPointReqVO reqVO : insertPointReqVOs) {
            InsertPointDO insertPoint = InsertPointDO.builder()
                    .taskId(taskId)
                    .point(reqVO.getPoint())
                    .content(reqVO.getContent())
                    .build();
            insertPoints.add(insertPoint);
        }
        
        for (InsertPointDO insertPoint : insertPoints) {
            insertPointMapper.insert(insertPoint);
        }
    }
    
    /**
     * 为人工提示词创建插入点列表（以逗号分割替换内容）
     */
    private void createInsertPointsForManualPrompt(Long taskId, List<TaskListSaveReqVO.InsertPointReqVO> insertPointReqVOs) {
        List<InsertPointDO> insertPoints = new ArrayList<>();
        for (TaskListSaveReqVO.InsertPointReqVO reqVO : insertPointReqVOs) {
            String point = reqVO.getPoint();
            String content = reqVO.getContent();
            
            if (content != null && !content.trim().isEmpty()) {
                // 以逗号分割替换内容
                String[] contentArray = content.split(",");
                for (String singleContent : contentArray) {
                    String trimmedContent = singleContent.trim();
                    if (!trimmedContent.isEmpty()) {
                        InsertPointDO insertPoint = InsertPointDO.builder()
                                .taskId(taskId)
                                .point(point)
                                .content(trimmedContent)
                                .build();
                        insertPoints.add(insertPoint);
                    }
                }
            }
        }
        
        for (InsertPointDO insertPoint : insertPoints) {
            insertPointMapper.insert(insertPoint);
        }
    }
    
    /**
     * 处理插入点更新
     */
    private void handleInsertPointsUpdate(TaskListSaveReqVO updateReqVO) {
        Long taskId = updateReqVO.getId();
        
        // 删除原有的插入点
        deleteInsertPointByTaskId(taskId);
        
        // 根据任务类型处理插入点数据
        if (updateReqVO.getInsertPoints() != null && !updateReqVO.getInsertPoints().isEmpty()) {
            if (!updateReqVO.getTaskType()) {
                // 人工提示词：以逗号分割替换内容，创建多行插入点
                createInsertPointsForManualPrompt(taskId, updateReqVO.getInsertPoints());
            }
        }
    }

}