package cn.iocoder.mall.promotion.application.convert;

import cn.iocoder.mall.promotion.api.bo.CouponTemplateBO;
import cn.iocoder.mall.promotion.api.bo.CouponTemplatePageBO;
import cn.iocoder.mall.promotion.application.convert.CouponTemplateConvert.Admins;
import cn.iocoder.mall.promotion.application.vo.admins.AdminsCouponTemplatePageVO;
import cn.iocoder.mall.promotion.application.vo.admins.AdminsCouponTemplateVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-05-24T11:39:15+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_121 (Oracle Corporation)"
)
public class CouponTemplateConvert$AdminsImpl implements Admins {

    @Override
    public AdminsCouponTemplateVO convert(CouponTemplateBO template) {
        if ( template == null ) {
            return null;
        }

        AdminsCouponTemplateVO adminsCouponTemplateVO = new AdminsCouponTemplateVO();

        adminsCouponTemplateVO.setId( template.getId() );
        adminsCouponTemplateVO.setTitle( template.getTitle() );
        adminsCouponTemplateVO.setDescription( template.getDescription() );
        adminsCouponTemplateVO.setType( template.getType() );
        adminsCouponTemplateVO.setCodeType( template.getCodeType() );
        adminsCouponTemplateVO.setStatus( template.getStatus() );
        adminsCouponTemplateVO.setQuota( template.getQuota() );
        adminsCouponTemplateVO.setTotal( template.getTotal() );
        adminsCouponTemplateVO.setPriceAvailable( template.getPriceAvailable() );
        adminsCouponTemplateVO.setRangeType( template.getRangeType() );
        adminsCouponTemplateVO.setRangeValues( template.getRangeValues() );
        adminsCouponTemplateVO.setDateType( template.getDateType() );
        adminsCouponTemplateVO.setValidStartTime( template.getValidStartTime() );
        adminsCouponTemplateVO.setValidEndTime( template.getValidEndTime() );
        adminsCouponTemplateVO.setFixedStartTerm( template.getFixedStartTerm() );
        adminsCouponTemplateVO.setFixedEndTerm( template.getFixedEndTerm() );
        adminsCouponTemplateVO.setPreferentialType( template.getPreferentialType() );
        adminsCouponTemplateVO.setPercentOff( template.getPercentOff() );
        adminsCouponTemplateVO.setPriceOff( template.getPriceOff() );
        adminsCouponTemplateVO.setDiscountPriceLimit( template.getDiscountPriceLimit() );
        adminsCouponTemplateVO.setStatFetchNum( template.getStatFetchNum() );
        adminsCouponTemplateVO.setCreateTime( template.getCreateTime() );

        return adminsCouponTemplateVO;
    }

    @Override
    public AdminsCouponTemplatePageVO convertPage(CouponTemplatePageBO result) {
        if ( result == null ) {
            return null;
        }

        AdminsCouponTemplatePageVO adminsCouponTemplatePageVO = new AdminsCouponTemplatePageVO();

        adminsCouponTemplatePageVO.setList( convertList( result.getList() ) );
        adminsCouponTemplatePageVO.setTotal( result.getTotal() );

        return adminsCouponTemplatePageVO;
    }

    @Override
    public List<AdminsCouponTemplateVO> convertList(List<CouponTemplateBO> templates) {
        if ( templates == null ) {
            return null;
        }

        List<AdminsCouponTemplateVO> list = new ArrayList<AdminsCouponTemplateVO>( templates.size() );
        for ( CouponTemplateBO couponTemplateBO : templates ) {
            list.add( convert( couponTemplateBO ) );
        }

        return list;
    }
}
