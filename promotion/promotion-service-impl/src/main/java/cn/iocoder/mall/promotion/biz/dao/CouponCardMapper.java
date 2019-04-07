package cn.iocoder.mall.promotion.biz.dao;

import cn.iocoder.mall.promotion.biz.dataobject.CouponCardDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponCardMapper {

    CouponCardDO selectById(@Param("id") Integer id);

    List<CouponCardDO> selectListByPage(@Param("status") Integer status);

    Integer selectCountByPage(@Param("status") Integer status);

    int selectCountByUserIdAndTemplateId(@Param("userId") Integer userId,
                                         @Param("templateId") Integer templateId);

    void insert(CouponCardDO couponCardDO);

    int update(CouponCardDO couponCardDO);

}
