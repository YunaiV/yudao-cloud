package cn.iocoder.mall.promotion.biz.dao;

import cn.iocoder.mall.promotion.biz.dataobject.CouponCardDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponCardMapper {

    CouponCardDO selectById(@Param("id") Integer id);

    List<CouponCardDO> selectListByUserIdAndStatus(@Param("userId") Integer userId,
                                                   @Param("status") Integer status);

    List<CouponCardDO> selectListByPage(@Param("userId") Integer userId,
                                        @Param("status") Integer status,
                                        @Param("offset") Integer offset,
                                        @Param("limit") Integer limit);

    Integer selectCountByPage(@Param("userId") Integer userId,
                              @Param("status") Integer status);

    int selectCountByUserIdAndTemplateId(@Param("userId") Integer userId,
                                         @Param("templateId") Integer templateId);

    void insert(CouponCardDO couponCardDO);

    int update(CouponCardDO couponCardDO);

    int updateByIdAndStatus(@Param("id") Integer id,
                            @Param("status") Integer status,
                            @Param("updateObj") CouponCardDO updateObj);

}
