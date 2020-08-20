package cn.iocoder.mall.promotionservice.dal.mysql.mapper.coupon;

import cn.iocoder.mall.promotionservice.dal.mysql.dataobject.coupon.CouponTemplateDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponTemplateMapper extends BaseMapper<CouponTemplateDO> {

    List<CouponTemplateDO> selectListByPage(@Param("type") Integer type,
                                            @Param("title") String title,
                                            @Param("status") Integer status,
                                            @Param("preferentialType") Integer preferentialType,
                                            @Param("offset") Integer offset,
                                            @Param("limit") Integer limit);

    Integer selectCountByPage(@Param("type") Integer type,
                              @Param("title") String title,
                              @Param("status") Integer status,
                              @Param("preferentialType") Integer preferentialType);

    /**
     * 更新优惠劵模板已领取的数量
     *
     * 如果超过领取上限，则返回 0
     *
     * @param id 优惠劵模板编号
     * @return 更新数量
     */
    int updateStatFetchNumIncr(@Param("id") Integer id);

}
