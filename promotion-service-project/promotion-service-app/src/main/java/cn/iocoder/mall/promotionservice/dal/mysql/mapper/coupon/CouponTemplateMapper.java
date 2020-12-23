package cn.iocoder.mall.promotionservice.dal.mysql.mapper.coupon;

import cn.iocoder.mall.mybatis.core.query.QueryWrapperX;
import cn.iocoder.mall.promotion.api.rpc.coupon.dto.template.CouponTemplatePageReqDTO;
import cn.iocoder.mall.promotionservice.dal.mysql.dataobject.coupon.CouponTemplateDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponTemplateMapper extends BaseMapper<CouponTemplateDO> {

    default IPage<CouponTemplateDO> selectPage(CouponTemplatePageReqDTO pageReqDTO) {
        return selectPage(new Page<>(pageReqDTO.getPageNo(), pageReqDTO.getPageSize()),
                new QueryWrapperX<CouponTemplateDO>().eqIfPresent("type", pageReqDTO.getType())
                    .likeIfPresent("title", pageReqDTO.getTitle())
                    .eqIfPresent("status", pageReqDTO.getStatus())
                    .eqIfPresent("preferential_type", pageReqDTO.getPreferentialType()));
    }

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
