package cn.iocoder.mall.promotion.application.convert;

import cn.iocoder.mall.promotion.api.bo.CouponCardBO;
import cn.iocoder.mall.promotion.api.bo.CouponCardPageBO;
import cn.iocoder.mall.promotion.application.vo.users.UsersCouponCardPageVO;
import cn.iocoder.mall.promotion.application.vo.users.UsersCouponCardVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-05-24T11:39:15+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_121 (Oracle Corporation)"
)
public class CouponCardConvertImpl implements CouponCardConvert {

    @Override
    public UsersCouponCardVO convert(CouponCardBO result) {
        if ( result == null ) {
            return null;
        }

        UsersCouponCardVO usersCouponCardVO = new UsersCouponCardVO();

        usersCouponCardVO.setId( result.getId() );
        usersCouponCardVO.setTemplateId( result.getTemplateId() );
        usersCouponCardVO.setTitle( result.getTitle() );
        usersCouponCardVO.setStatus( result.getStatus() );
        usersCouponCardVO.setPriceAvailable( result.getPriceAvailable() );
        usersCouponCardVO.setValidStartTime( result.getValidStartTime() );
        usersCouponCardVO.setValidEndTime( result.getValidEndTime() );
        usersCouponCardVO.setPreferentialType( result.getPreferentialType() );
        usersCouponCardVO.setPercentOff( result.getPercentOff() );
        usersCouponCardVO.setPriceOff( result.getPriceOff() );
        usersCouponCardVO.setDiscountPriceLimit( result.getDiscountPriceLimit() );

        return usersCouponCardVO;
    }

    @Override
    public UsersCouponCardPageVO convert2(CouponCardPageBO result) {
        if ( result == null ) {
            return null;
        }

        UsersCouponCardPageVO usersCouponCardPageVO = new UsersCouponCardPageVO();

        usersCouponCardPageVO.setList( couponCardBOListToUsersCouponCardVOList( result.getList() ) );
        usersCouponCardPageVO.setTotal( result.getTotal() );

        return usersCouponCardPageVO;
    }

    protected List<UsersCouponCardVO> couponCardBOListToUsersCouponCardVOList(List<CouponCardBO> list) {
        if ( list == null ) {
            return null;
        }

        List<UsersCouponCardVO> list1 = new ArrayList<UsersCouponCardVO>( list.size() );
        for ( CouponCardBO couponCardBO : list ) {
            list1.add( convert( couponCardBO ) );
        }

        return list1;
    }
}
