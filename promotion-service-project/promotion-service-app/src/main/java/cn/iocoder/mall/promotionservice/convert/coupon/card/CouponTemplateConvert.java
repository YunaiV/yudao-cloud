package cn.iocoder.mall.promotionservice.convert.coupon.card;

import cn.iocoder.common.framework.util.StringUtils;
import cn.iocoder.mall.promotion.api.rpc.coupon.dto.*;
import cn.iocoder.mall.promotion.api.rpc.coupon.dto.template.CouponTemplateRespDTO;
import cn.iocoder.mall.promotionservice.dal.mysql.dataobject.coupon.CouponTemplateDO;
import cn.iocoder.mall.promotionservice.service.coupon.bo.CouponCardTemplateAddBO;
import cn.iocoder.mall.promotionservice.service.coupon.bo.CouponCardTemplateUpdateBO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CouponTemplateConvert {

    CouponTemplateConvert INSTANCE = Mappers.getMapper(CouponTemplateConvert.class);

//    @Mappings({})
//    CouponTemplateBO convertToBO(CouponTemplateDO banner);


    List<CouponTemplateReqDTO> convertToDTO(List<CouponTemplateDO> templateList);

    @Mappings({})
    CouponTemplateDO convert(CouponCodeTemplateUpdateReqDTO template);

    @Mappings({})
    CouponTemplateDO convert(CouponCardTemplateAddReqDTO template);

    @Mappings({})
    CouponTemplateDO convert(CouponCardTemplateUpdateReqDTO template);

    @Mappings({})
    CouponTemplateDO convert(CouponCodeTemplateAddReqDTO template);

    @Mappings({})
    CouponTemplateDO convert(CouponCardTemplateAddBO template);

    @Mappings({})
    CouponTemplateDO convert(CouponCardTemplateUpdateBO template);

    @Mapping(source = "rangeValues", target = "rangeValues", qualifiedByName = "translateStringToIntList")
    CouponTemplateRespDTO convert(CouponTemplateDO bean);

    @Named("translateStringToIntList")
    default List<Integer> translateStringToIntList(String str) {
        return StringUtils.splitToInt(str, ",");
    }

}
