package cn.iocoder.mall.promotionservice.convert.coupon;

import cn.iocoder.common.framework.util.StringUtils;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.promotion.api.rpc.coupon.dto.template.CouponCardTemplateCreateReqDTO;
import cn.iocoder.mall.promotion.api.rpc.coupon.dto.template.CouponCardTemplateUpdateReqDTO;
import cn.iocoder.mall.promotion.api.rpc.coupon.dto.template.CouponTemplateRespDTO;
import cn.iocoder.mall.promotionservice.dal.mysql.dataobject.coupon.CouponTemplateDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CouponTemplateConvert {

    CouponTemplateConvert INSTANCE = Mappers.getMapper(CouponTemplateConvert.class);

    @Mapping(source = "rangeValues", target = "rangeValues", qualifiedByName = "translateStringToIntList")
    CouponTemplateRespDTO convert(CouponTemplateDO bean);

    CouponTemplateDO convert(CouponCardTemplateCreateReqDTO bean);

    CouponTemplateDO convert(CouponCardTemplateUpdateReqDTO bean);

    @Mapping(source = "records", target = "list")
    PageResult<CouponTemplateRespDTO> convertPage(IPage<CouponTemplateDO> couponTemplatePage);

    @Named("translateStringToIntList")
    default List<Integer> translateStringToIntList(String str) {
        return StringUtils.splitToInt(str, ",");
    }

}
