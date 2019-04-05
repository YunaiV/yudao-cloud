package cn.iocoder.mall.promotion.application.convert;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.promotion.api.bo.CouponTemplateBO;
import cn.iocoder.mall.promotion.api.bo.CouponTemplatePageBO;
import cn.iocoder.mall.promotion.application.vo.admins.AdminsCouponTemplatePageVO;
import cn.iocoder.mall.promotion.application.vo.admins.AdminsCouponTemplateVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-04-05T22:26:04+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 11.0.1 (Oracle Corporation)"
)
public class CouponTemplateConvertImpl implements CouponTemplateConvert {

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
        adminsCouponTemplateVO.setTotal( template.getTotal() );

        return adminsCouponTemplateVO;
    }

    @Override
    public CommonResult<AdminsCouponTemplateVO> convert2(CommonResult<CouponTemplateBO> result) {
        if ( result == null ) {
            return null;
        }

        CommonResult<AdminsCouponTemplateVO> commonResult = new CommonResult<AdminsCouponTemplateVO>();

        commonResult.setCode( result.getCode() );
        commonResult.setMessage( result.getMessage() );
        commonResult.setData( convert( result.getData() ) );

        return commonResult;
    }

    @Override
    public CommonResult<AdminsCouponTemplatePageVO> convert(CommonResult<CouponTemplatePageBO> result) {
        if ( result == null ) {
            return null;
        }

        CommonResult<AdminsCouponTemplatePageVO> commonResult = new CommonResult<AdminsCouponTemplatePageVO>();

        commonResult.setCode( result.getCode() );
        commonResult.setMessage( result.getMessage() );
        commonResult.setData( couponTemplatePageBOToAdminsCouponTemplatePageVO( result.getData() ) );

        return commonResult;
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

    protected AdminsCouponTemplatePageVO couponTemplatePageBOToAdminsCouponTemplatePageVO(CouponTemplatePageBO couponTemplatePageBO) {
        if ( couponTemplatePageBO == null ) {
            return null;
        }

        AdminsCouponTemplatePageVO adminsCouponTemplatePageVO = new AdminsCouponTemplatePageVO();

        adminsCouponTemplatePageVO.setList( convertList( couponTemplatePageBO.getList() ) );
        adminsCouponTemplatePageVO.setTotal( couponTemplatePageBO.getTotal() );

        return adminsCouponTemplatePageVO;
    }
}
