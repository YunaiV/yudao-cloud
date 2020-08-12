package cn.iocoder.mall.promotionservice.dal.mysql.mapper.activity;

import cn.iocoder.mall.promotionservice.dal.mysql.dataobject.activity.PromotionActivityDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PromotionActivityMapper extends BaseMapper<PromotionActivityDO> {

    default List<PromotionActivityDO> selectListByStatus(@Param("statuses") Collection<Integer> statuses) {
        return selectList(new QueryWrapper<PromotionActivityDO>().in("status", statuses));
    }

    List<PromotionActivityDO> selectListByPage(@Param("title") String title,
                                               @Param("activityType") Integer activityType,
                                               @Param("statuses") Collection<Integer> statuses,
                                               @Param("offset") Integer offset,
                                               @Param("limit") Integer limit);

    Integer selectCountByPage(@Param("title") String title,
                              @Param("activityType") Integer activityType,
                              @Param("statuses") Collection<Integer> statuses);

}
