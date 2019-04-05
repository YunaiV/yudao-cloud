package cn.iocoder.mall.promotion.biz.convert;

import cn.iocoder.mall.promotion.api.bo.CouponTemplateBO;
import cn.iocoder.mall.promotion.api.dto.CouponCardTemplateAddDTO;
import cn.iocoder.mall.promotion.api.dto.CouponCodeTemplateAddDTO;
import cn.iocoder.mall.promotion.biz.dataobject.CouponTemplateDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CouponTemplateConvert {

    CouponTemplateConvert INSTANCE = Mappers.getMapper(CouponTemplateConvert.class);

//    @Mappings({})
//    CouponTemplateBO convertToBO(CouponTemplateDO banner);
//
    @Mappings({})
    List<CouponTemplateBO> convertToBO(List<CouponTemplateDO> templateList);

    @Mappings({})
    CouponTemplateDO convert(CouponCodeTemplateAddDTO template);

    @Mappings({})
    CouponTemplateDO convert(CouponCardTemplateAddDTO template);

    @Mappings({})
    CouponTemplateBO convert(CouponTemplateDO template);

}
