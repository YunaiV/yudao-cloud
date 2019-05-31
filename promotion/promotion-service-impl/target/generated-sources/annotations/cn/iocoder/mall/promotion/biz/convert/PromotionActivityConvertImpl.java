package cn.iocoder.mall.promotion.biz.convert;

import cn.iocoder.mall.promotion.api.bo.PromotionActivityBO;
import cn.iocoder.mall.promotion.api.bo.PromotionActivityBO.FullPrivilege.Privilege;
import cn.iocoder.mall.promotion.api.bo.PromotionActivityBO.TimeLimitedDiscount;
import cn.iocoder.mall.promotion.biz.dataobject.PromotionActivityDO;
import cn.iocoder.mall.promotion.biz.dataobject.PromotionActivityDO.FullPrivilege;
import cn.iocoder.mall.promotion.biz.dataobject.PromotionActivityDO.TimeLimitedDiscount.Item;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-05-24T11:38:52+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_121 (Oracle Corporation)"
)
public class PromotionActivityConvertImpl implements PromotionActivityConvert {

    @Override
    public PromotionActivityBO convertToBO(PromotionActivityDO activity) {
        if ( activity == null ) {
            return null;
        }

        PromotionActivityBO promotionActivityBO = new PromotionActivityBO();

        promotionActivityBO.setId( activity.getId() );
        promotionActivityBO.setTitle( activity.getTitle() );
        promotionActivityBO.setActivityType( activity.getActivityType() );
        promotionActivityBO.setStatus( activity.getStatus() );
        promotionActivityBO.setStartTime( activity.getStartTime() );
        promotionActivityBO.setEndTime( activity.getEndTime() );
        promotionActivityBO.setTimeLimitedDiscount( timeLimitedDiscountToTimeLimitedDiscount( activity.getTimeLimitedDiscount() ) );
        promotionActivityBO.setFullPrivilege( fullPrivilegeToFullPrivilege( activity.getFullPrivilege() ) );

        return promotionActivityBO;
    }

    @Override
    public List<PromotionActivityBO> convertToBO(List<PromotionActivityDO> activityList) {
        if ( activityList == null ) {
            return null;
        }

        List<PromotionActivityBO> list = new ArrayList<PromotionActivityBO>( activityList.size() );
        for ( PromotionActivityDO promotionActivityDO : activityList ) {
            list.add( convertToBO( promotionActivityDO ) );
        }

        return list;
    }

    protected cn.iocoder.mall.promotion.api.bo.PromotionActivityBO.TimeLimitedDiscount.Item itemToItem(Item item) {
        if ( item == null ) {
            return null;
        }

        cn.iocoder.mall.promotion.api.bo.PromotionActivityBO.TimeLimitedDiscount.Item item1 = new cn.iocoder.mall.promotion.api.bo.PromotionActivityBO.TimeLimitedDiscount.Item();

        item1.setSpuId( item.getSpuId() );
        item1.setPreferentialType( item.getPreferentialType() );
        item1.setPreferentialValue( item.getPreferentialValue() );

        return item1;
    }

    protected List<cn.iocoder.mall.promotion.api.bo.PromotionActivityBO.TimeLimitedDiscount.Item> itemListToItemList(List<Item> list) {
        if ( list == null ) {
            return null;
        }

        List<cn.iocoder.mall.promotion.api.bo.PromotionActivityBO.TimeLimitedDiscount.Item> list1 = new ArrayList<cn.iocoder.mall.promotion.api.bo.PromotionActivityBO.TimeLimitedDiscount.Item>( list.size() );
        for ( Item item : list ) {
            list1.add( itemToItem( item ) );
        }

        return list1;
    }

    protected TimeLimitedDiscount timeLimitedDiscountToTimeLimitedDiscount(cn.iocoder.mall.promotion.biz.dataobject.PromotionActivityDO.TimeLimitedDiscount timeLimitedDiscount) {
        if ( timeLimitedDiscount == null ) {
            return null;
        }

        TimeLimitedDiscount timeLimitedDiscount1 = new TimeLimitedDiscount();

        timeLimitedDiscount1.setQuota( timeLimitedDiscount.getQuota() );
        timeLimitedDiscount1.setItems( itemListToItemList( timeLimitedDiscount.getItems() ) );

        return timeLimitedDiscount1;
    }

    protected Privilege privilegeToPrivilege(cn.iocoder.mall.promotion.biz.dataobject.PromotionActivityDO.FullPrivilege.Privilege privilege) {
        if ( privilege == null ) {
            return null;
        }

        Privilege privilege1 = new Privilege();

        privilege1.setMeetType( privilege.getMeetType() );
        privilege1.setMeetValue( privilege.getMeetValue() );
        privilege1.setPreferentialType( privilege.getPreferentialType() );
        privilege1.setPreferentialValue( privilege.getPreferentialValue() );

        return privilege1;
    }

    protected List<Privilege> privilegeListToPrivilegeList(List<cn.iocoder.mall.promotion.biz.dataobject.PromotionActivityDO.FullPrivilege.Privilege> list) {
        if ( list == null ) {
            return null;
        }

        List<Privilege> list1 = new ArrayList<Privilege>( list.size() );
        for ( cn.iocoder.mall.promotion.biz.dataobject.PromotionActivityDO.FullPrivilege.Privilege privilege : list ) {
            list1.add( privilegeToPrivilege( privilege ) );
        }

        return list1;
    }

    protected cn.iocoder.mall.promotion.api.bo.PromotionActivityBO.FullPrivilege fullPrivilegeToFullPrivilege(FullPrivilege fullPrivilege) {
        if ( fullPrivilege == null ) {
            return null;
        }

        cn.iocoder.mall.promotion.api.bo.PromotionActivityBO.FullPrivilege fullPrivilege1 = new cn.iocoder.mall.promotion.api.bo.PromotionActivityBO.FullPrivilege();

        fullPrivilege1.setRangeType( fullPrivilege.getRangeType() );
        List<Integer> list = fullPrivilege.getRangeValues();
        if ( list != null ) {
            fullPrivilege1.setRangeValues( new ArrayList<Integer>( list ) );
        }
        fullPrivilege1.setCycled( fullPrivilege.getCycled() );
        fullPrivilege1.setPrivileges( privilegeListToPrivilegeList( fullPrivilege.getPrivileges() ) );

        return fullPrivilege1;
    }
}
