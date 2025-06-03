package cn.iocoder.yudao.module.prod.dal.mysql.tasklist;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.prod.dal.dataobject.tasklist.TaskListDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.prod.controller.admin.tasklist.vo.*;

/**
 * 任务列 Mapper
 *
 * @author 麦芽
 */
@Mapper
public interface TaskListMapper extends BaseMapperX<TaskListDO> {

    default PageResult<TaskListDO> selectPage(TaskListPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<TaskListDO>()
                .eqIfPresent(TaskListDO::getTaskType, reqVO.getTaskType())
                .eqIfPresent(TaskListDO::getProdConfigId, reqVO.getProdConfigId())
                .eqIfPresent(TaskListDO::getTaskGenQty, reqVO.getTaskGenQty())
                .eqIfPresent(TaskListDO::getTaskSwitchNum, reqVO.getTaskSwitchNum())
                .eqIfPresent(TaskListDO::getTaskPrompt, reqVO.getTaskPrompt())
                .eqIfPresent(TaskListDO::getImgSize, reqVO.getImgSize())
                .eqIfPresent(TaskListDO::getTemplateId, reqVO.getTemplateId())
                .eqIfPresent(TaskListDO::getTaskPromptDesc, reqVO.getTaskPromptDesc())
                .betweenIfPresent(TaskListDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(TaskListDO::getId));
    }

}