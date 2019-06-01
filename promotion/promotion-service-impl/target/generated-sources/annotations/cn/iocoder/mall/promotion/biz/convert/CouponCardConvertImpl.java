package cn.iocoder.mall.promotion.biz.convert;

import cn.iocoder.mall.promotion.api.bo.CouponCardAvailableBO;
import cn.iocoder.mall.promotion.api.bo.CouponCardBO;
import cn.iocoder.mall.promotion.api.bo.CouponCardDetailBO;
import cn.iocoder.mall.promotion.biz.dataobject.CouponCardDO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-05-24T11:38:52+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_121 (Oracle Corporation)"
)
public class CouponCardConvertImpl implements CouponCardConvert {

    @Override
    public List<CouponCardBO> convertToBO(List<CouponCardDO> cardList) {
        if ( cardList == null ) {
            return null;
        }

        List<CouponCardBO> list = new ArrayList<CouponCardBO>( cardList.size() );
        for ( CouponCardDO couponCardDO : cardList ) {
            list.add( convert( couponCardDO ) );
        }

        return list;
    }

    @Override
    public CouponCardBO convert(CouponCardDO card) {
        if ( card == null ) {
            return null;
        }

        CouponCardBO couponCardBO = new CouponCardBO();

        couponCardBO.setId( card.getId() );
        couponCardBO.setTemplateId( card.getTemplateId() );
        couponCardBO.setTitle( card.getTitle() );
        couponCardBO.setStatus( card.getStatus() );
        couponCardBO.setUserId( card.getUserId() );
        couponCardBO.setTakeType( card.getTakeType() );
        couponCardBO.setPriceAvailable( card.getPriceAvailable() );
        couponCardBO.setValidStartTime( card.getValidStartTime() );
        couponCardBO.setValidEndTime( card.getValidEndTime() );
        couponCardBO.setPreferentialType( card.getPreferentialType() );
        couponCardBO.setPercentOff( card.getPercentOff() );
        couponCardBO.setPriceOff( card.getPriceOff() );
        couponCardBO.setDiscountPriceLimit( card.getDiscountPriceLimit() );
        couponCardBO.setUsedTime( card.getUsedTime() );
        couponCardBO.setCreateTime( card.getCreateTime() );

        return couponCardBO;
    }

    @Override
    public CouponCardDetailBO convert2(CouponCardDO card) {
        if ( card == null ) {
            return null;
        }

        CouponCardDetailBO couponCardDetailBO = new CouponCardDetailBO();

        couponCardDetailBO.setId( card.getId() );
        couponCardDetailBO.setTemplateId( card.getTemplateId() );
        couponCardDetailBO.setTitle( card.getTitle() );
        couponCardDetailBO.setStatus( card.getStatus() );
        couponCardDetailBO.setUserId( card.getUserId() );
        couponCardDetailBO.setTakeType( card.getTakeType() );
        couponCardDetailBO.setPriceAvailable( card.getPriceAvailable() );
        couponCardDetailBO.setValidStartTime( card.getValidStartTime() );
        couponCardDetailBO.setValidEndTime( card.getValidEndTime() );
        couponCardDetailBO.setPreferentialType( card.getPreferentialType() );
        couponCardDetailBO.setPercentOff( card.getPercentOff() );
        couponCardDetailBO.setPriceOff( card.getPriceOff() );
        couponCardDetailBO.setDiscountPriceLimit( card.getDiscountPriceLimit() );
        couponCardDetailBO.setUsedTime( card.getUsedTime() );
        couponCardDetailBO.setCreateTime( card.getCreateTime() );

        return couponCardDetailBO;
    }

    @Override
    public CouponCardAvailableBO convert2(CouponCardDO card, boolean x) {
        if ( card == null ) {
            return null;
        }

        CouponCardAvailableBO couponCardAvailableBO = new CouponCardAvailableBO();

        if ( card != null ) {
            couponCardAvailableBO.setId( card.getId() );
            couponCardAvailableBO.setTemplateId( card.getTemplateId() );
            couponCardAvailableBO.setTitle( card.getTitle() );
            couponCardAvailableBO.setStatus( card.getStatus() );
            couponCardAvailableBO.setUserId( card.getUserId() );
            couponCardAvailableBO.setTakeType( card.getTakeType() );
            couponCardAvailableBO.setPriceAvailable( card.getPriceAvailable() );
            couponCardAvailableBO.setValidStartTime( card.getValidStartTime() );
            couponCardAvailableBO.setValidEndTime( card.getValidEndTime() );
            couponCardAvailableBO.setPreferentialType( card.getPreferentialType() );
            couponCardAvailableBO.setPercentOff( card.getPercentOff() );
            couponCardAvailableBO.setPriceOff( card.getPriceOff() );
            couponCardAvailableBO.setDiscountPriceLimit( card.getDiscountPriceLimit() );
            couponCardAvailableBO.setUsedTime( card.getUsedTime() );
            couponCardAvailableBO.setCreateTime( card.getCreateTime() );
        }

        return couponCardAvailableBO;
    }
}
