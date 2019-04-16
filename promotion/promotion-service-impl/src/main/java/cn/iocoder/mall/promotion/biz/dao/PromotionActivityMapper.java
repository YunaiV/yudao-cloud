package cn.iocoder.mall.promotion.biz.dao;

import cn.iocoder.mall.promotion.biz.dataobject.PromotionActivityDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PromotionActivityMapper {

    PromotionActivityDO selectById(@Param("id") Integer id);

    List<PromotionActivityDO> selectListByStatus(@Param("statuses")Collection<Integer> statuses);

    void insert(PromotionActivityDO activity);

}
