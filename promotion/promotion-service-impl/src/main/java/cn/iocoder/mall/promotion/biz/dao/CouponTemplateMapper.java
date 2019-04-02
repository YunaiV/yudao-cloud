package cn.iocoder.mall.promotion.biz.dao;

import cn.iocoder.mall.promotion.biz.dataobject.CouponTemplateDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponTemplateMapper {

    CouponTemplateDO selectById(@Param("id") Integer id);

    List<CouponTemplateDO> selectListByPage(@Param("title") String title,
                                            @Param("status") Integer status,
                                            @Param("preferentialType") Integer preferentialType,
                                            @Param("offset") Integer offset,
                                            @Param("limit") Integer limit);

    Integer selectCountByPage(@Param("title") String title,
                              @Param("status") Integer status,
                              @Param("preferentialType") Integer preferentialType);

    void insert(CouponTemplateDO couponTemplate);

    int update(CouponTemplateDO couponTemplate);

}
