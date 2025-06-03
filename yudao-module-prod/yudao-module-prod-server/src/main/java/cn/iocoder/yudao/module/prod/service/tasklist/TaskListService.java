package cn.iocoder.yudao.module.prod.service.tasklist;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.prod.controller.admin.tasklist.vo.*;
import cn.iocoder.yudao.module.prod.dal.dataobject.tasklist.TaskListDO;
import cn.iocoder.yudao.module.prod.dal.dataobject.tasklist.InsertPointDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 任务列 Service 接口
 *
 * @author 麦芽
 */
public interface TaskListService {

    /**
     * 创建任务列
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createTaskList(@Valid TaskListSaveReqVO createReqVO);

    /**
     * 更新任务列
     *
     * @param updateReqVO 更新信息
     */
    void updateTaskList(@Valid TaskListSaveReqVO updateReqVO);

    /**
     * 删除任务列
     *
     * @param id 编号
     */
    void deleteTaskList(Long id);

    /**
    * 批量删除任务列
    *
    * @param ids 编号
    */
    void deleteTaskListListByIds(List<Long> ids);

    /**
     * 获得任务列
     *
     * @param id 编号
     * @return 任务列
     */
    TaskListDO getTaskList(Long id);

    /**
     * 获得任务列分页
     *
     * @param pageReqVO 分页查询
     * @return 任务列分页
     */
    PageResult<TaskListDO> getTaskListPage(TaskListPageReqVO pageReqVO);

    // ==================== 子表（插入点） ====================

    /**
     * 获得插入点分页
     *
     * @param pageReqVO 分页查询
     * @param id 主键
     * @return 插入点分页
     */
    PageResult<InsertPointDO> getInsertPointPage(PageParam pageReqVO, Long id);

    /**
     * 获得插入点分页（支持搜索）
     *
     * @param pageReqVO 分页查询
     * @param id 主键
     * @param point 插入点搜索条件
     * @return 插入点分页
     */
    PageResult<InsertPointDO> getInsertPointPage(PageParam pageReqVO, Long id, String point);

    /**
     * 创建插入点
     *
     * @param insertPoint 创建信息
     * @return 编号
     */
    Long createInsertPoint(@Valid InsertPointDO insertPoint);

    /**
     * 更新插入点
     *
     * @param insertPoint 更新信息
     */
    void updateInsertPoint(@Valid InsertPointDO insertPoint);

    /**
     * 删除插入点
     *
     * @param id 编号
     */
    void deleteInsertPoint(Long id);

    /**
    * 批量删除插入点
    *
    * @param ids 编号
    */
    void deleteInsertPointListByIds(List<Long> ids);

	/**
	 * 获得插入点
	 *
	 * @param id 编号
     * @return 插入点
	 */
    InsertPointDO getInsertPoint(Long id);

    /**
     * 根据任务ID获得插入点列表
     *
     * @param taskId 任务ID
     * @return 插入点列表
     */
    List<InsertPointDO> getInsertPointsByTaskId(Long taskId);

}