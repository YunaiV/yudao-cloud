package cn.iocoder.mall.promotion.application.convert;

import cn.iocoder.mall.promotion.api.bo.CouponTemplateBO;
import cn.iocoder.mall.promotion.application.convert.CouponTemplateConvert.Users;
import cn.iocoder.mall.promotion.application.vo.users.UsersCouponTemplateVO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-05-24T11:39:15+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_121 (Oracle Corporation)"
)
public class CouponTemplateConvert$UsersImpl implements Users {

    @Override
    public UsersCouponTemplateVO convert2(CouponTemplateBO template) {
        if ( template == null ) {
            return null;
        }

        UsersCouponTemplateVO usersCouponTemplateVO = new UsersCouponTemplateVO();

        usersCouponTemplateVO.setId( template.getId() );
        usersCouponTemplateVO.setTitle( template.getTitle() );
        usersCouponTemplateVO.setStatus( template.getStatus() );
        usersCouponTemplateVO.setPriceAvailable( template.getPriceAvailable() );
        usersCouponTemplateVO.setRangeType( template.getRangeType() );
        usersCouponTemplateVO.setRangeValues( template.getRangeValues() );
        usersCouponTemplateVO.setDateType( template.getDateType() );
        usersCouponTemplateVO.setValidStartTime( template.getValidStartTime() );
        usersCouponTemplateVO.setValidEndTime( template.getValidEndTime() );
        usersCouponTemplateVO.setFixedStartTerm( template.getFixedStartTerm() );
        usersCouponTemplateVO.setFixedEndTerm( template.getFixedEndTerm() );
        usersCouponTemplateVO.setPreferentialType( template.getPreferentialType() );
        usersCouponTemplateVO.setPercentOff( template.getPercentOff() );
        usersCouponTemplateVO.setPriceOff( template.getPriceOff() );
        usersCouponTemplateVO.setDiscountPriceLimit( template.getDiscountPriceLimit() );

        return usersCouponTemplateVO;
    }
}
