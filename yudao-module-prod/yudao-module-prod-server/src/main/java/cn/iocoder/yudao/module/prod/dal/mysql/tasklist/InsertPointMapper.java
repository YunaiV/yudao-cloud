package cn.iocoder.yudao.module.prod.dal.mysql.tasklist;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.prod.dal.dataobject.tasklist.InsertPointDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 插入点 Mapper
 *
 * @author 麦芽
 */
@Mapper
public interface InsertPointMapper extends BaseMapperX<InsertPointDO> {

    default PageResult<InsertPointDO> selectPage(PageParam reqVO, Long id) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InsertPointDO>()
            .eq(InsertPointDO::getTaskId, id)
            .orderByDesc(InsertPointDO::getId));
    }

    default PageResult<InsertPointDO> selectPage(PageParam reqVO, Long id, String point) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InsertPointDO>()
            .eq(InsertPointDO::getTaskId, id)
            .likeIfPresent(InsertPointDO::getPoint, point)
            .orderByDesc(InsertPointDO::getId));
    }

    default int deleteById(Long id) {
        return delete(InsertPointDO::getId, id);
    }

	default int deleteByIds(List<Long> ids) {
	    return deleteBatch(InsertPointDO::getId, ids);
	}

    /**
     * 根据任务ID删除插入点
     */
    default int deleteByTaskId(Long taskId) {
        return delete(InsertPointDO::getTaskId, taskId);
    }

    /**
     * 根据任务ID列表删除插入点
     */
    default int deleteByTaskIds(List<Long> taskIds) {
        return deleteBatch(InsertPointDO::getTaskId, taskIds);
    }
    
    /**
     * 根据任务ID获取插入点列表
     */
    default List<InsertPointDO> selectByTaskId(Long taskId) {
        return selectList(new LambdaQueryWrapperX<InsertPointDO>()
            .eq(InsertPointDO::getTaskId, taskId)
            .orderByAsc(InsertPointDO::getId));
	}

}