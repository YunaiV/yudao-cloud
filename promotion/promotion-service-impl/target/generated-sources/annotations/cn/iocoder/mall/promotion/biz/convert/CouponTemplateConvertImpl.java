package cn.iocoder.mall.promotion.biz.convert;

import cn.iocoder.mall.promotion.api.bo.CouponTemplateBO;
import cn.iocoder.mall.promotion.api.dto.CouponCardTemplateAddDTO;
import cn.iocoder.mall.promotion.api.dto.CouponCardTemplateUpdateDTO;
import cn.iocoder.mall.promotion.api.dto.CouponCodeTemplateAddDTO;
import cn.iocoder.mall.promotion.api.dto.CouponCodeTemplateUpdateDTO;
import cn.iocoder.mall.promotion.biz.dataobject.CouponTemplateDO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-04-06T00:20:10+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 11.0.1 (Oracle Corporation)"
)
public class CouponTemplateConvertImpl implements CouponTemplateConvert {

    @Override
    public List<CouponTemplateBO> convertToBO(List<CouponTemplateDO> templateList) {
        if ( templateList == null ) {
            return null;
        }

        List<CouponTemplateBO> list = new ArrayList<CouponTemplateBO>( templateList.size() );
        for ( CouponTemplateDO couponTemplateDO : templateList ) {
            list.add( convert( couponTemplateDO ) );
        }

        return list;
    }

    @Override
    public CouponTemplateDO convert(CouponCodeTemplateUpdateDTO template) {
        if ( template == null ) {
            return null;
        }

        CouponTemplateDO couponTemplateDO = new CouponTemplateDO();

        return couponTemplateDO;
    }

    @Override
    public CouponTemplateDO convert(CouponCardTemplateAddDTO template) {
        if ( template == null ) {
            return null;
        }

        CouponTemplateDO couponTemplateDO = new CouponTemplateDO();

        couponTemplateDO.setTitle( template.getTitle() );
        couponTemplateDO.setDescription( template.getDescription() );
        couponTemplateDO.setQuota( template.getQuota() );
        couponTemplateDO.setTotal( template.getTotal() );
        couponTemplateDO.setPriceAvailable( template.getPriceAvailable() );
        couponTemplateDO.setRangeType( template.getRangeType() );
        couponTemplateDO.setRangeValues( template.getRangeValues() );
        couponTemplateDO.setDateType( template.getDateType() );
        couponTemplateDO.setValidStartTime( template.getValidStartTime() );
        couponTemplateDO.setValidEndTime( template.getValidEndTime() );
        couponTemplateDO.setFixedEndTerm( template.getFixedEndTerm() );
        couponTemplateDO.setPreferentialType( template.getPreferentialType() );
        couponTemplateDO.setPercentOff( template.getPercentOff() );
        couponTemplateDO.setPriceOff( template.getPriceOff() );
        couponTemplateDO.setDiscountPriceLimit( template.getDiscountPriceLimit() );

        return couponTemplateDO;
    }

    @Override
    public CouponTemplateDO convert(CouponCardTemplateUpdateDTO template) {
        if ( template == null ) {
            return null;
        }

        CouponTemplateDO couponTemplateDO = new CouponTemplateDO();

        couponTemplateDO.setId( template.getId() );
        couponTemplateDO.setTitle( template.getTitle() );
        couponTemplateDO.setDescription( template.getDescription() );
        couponTemplateDO.setQuota( template.getQuota() );
        couponTemplateDO.setTotal( template.getTotal() );
        couponTemplateDO.setRangeType( template.getRangeType() );
        couponTemplateDO.setRangeValues( template.getRangeValues() );

        return couponTemplateDO;
    }

    @Override
    public CouponTemplateDO convert(CouponCodeTemplateAddDTO template) {
        if ( template == null ) {
            return null;
        }

        CouponTemplateDO couponTemplateDO = new CouponTemplateDO();

        return couponTemplateDO;
    }

    @Override
    public CouponTemplateBO convert(CouponTemplateDO template) {
        if ( template == null ) {
            return null;
        }

        CouponTemplateBO couponTemplateBO = new CouponTemplateBO();

        couponTemplateBO.setId( template.getId() );
        couponTemplateBO.setTitle( template.getTitle() );
        couponTemplateBO.setDescription( template.getDescription() );
        couponTemplateBO.setType( template.getType() );
        couponTemplateBO.setCodeType( template.getCodeType() );
        couponTemplateBO.setStatus( template.getStatus() );
        couponTemplateBO.setQuota( template.getQuota() );
        couponTemplateBO.setTotal( template.getTotal() );
        couponTemplateBO.setPriceAvailable( template.getPriceAvailable() );
        couponTemplateBO.setRangeType( template.getRangeType() );
        couponTemplateBO.setRangeValues( template.getRangeValues() );
        couponTemplateBO.setDateType( template.getDateType() );
        couponTemplateBO.setValidStartTime( template.getValidStartTime() );
        couponTemplateBO.setValidEndTime( template.getValidEndTime() );
        couponTemplateBO.setPreferentialType( template.getPreferentialType() );
        couponTemplateBO.setPercentOff( template.getPercentOff() );
        couponTemplateBO.setPriceOff( template.getPriceOff() );
        couponTemplateBO.setDiscountPriceLimit( template.getDiscountPriceLimit() );
        couponTemplateBO.setStatFetchNum( template.getStatFetchNum() );
        couponTemplateBO.setCreateTime( template.getCreateTime() );
        couponTemplateBO.setFixedStartTerm( template.getFixedStartTerm() );
        couponTemplateBO.setFixedEndTerm( template.getFixedEndTerm() );

        return couponTemplateBO;
    }
}
