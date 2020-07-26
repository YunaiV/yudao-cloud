package cn.iocoder.mall.promotionservice.dal.mysql.mapper.activity;

import cn.iocoder.mall.promotionservice.dal.mysql.dataobject.activity.PromotionActivityDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PromotionActivityMapper {

    PromotionActivityDO selectById(@Param("id") Integer id);

    List<PromotionActivityDO> selectListByStatus(@Param("statuses") Collection<Integer> statuses);

    void insert(PromotionActivityDO activity);

    List<PromotionActivityDO> selectListByPage(@Param("title") String title,
                                               @Param("activityType") Integer activityType,
                                               @Param("statuses") Collection<Integer> statuses,
                                               @Param("offset") Integer offset,
                                               @Param("limit") Integer limit);

    Integer selectCountByPage(@Param("title") String title,
                              @Param("activityType") Integer activityType,
                              @Param("statuses") Collection<Integer> statuses);

}
