package cn.iocoder.mall.promotion.application.convert;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.product.api.bo.ProductSpuBO;
import cn.iocoder.mall.promotion.api.bo.ProductRecommendBO;
import cn.iocoder.mall.promotion.api.bo.ProductRecommendPageBO;
import cn.iocoder.mall.promotion.application.vo.admins.AdminsProductRecommendPageVO;
import cn.iocoder.mall.promotion.application.vo.admins.AdminsProductRecommendVO;
import cn.iocoder.mall.promotion.application.vo.users.UsersProductRecommendVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-04-05T22:26:04+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 11.0.1 (Oracle Corporation)"
)
public class ProductRecommendConvertImpl implements ProductRecommendConvert {

    @Override
    public AdminsProductRecommendVO convert(ProductRecommendBO bannerBO) {
        if ( bannerBO == null ) {
            return null;
        }

        AdminsProductRecommendVO adminsProductRecommendVO = new AdminsProductRecommendVO();

        adminsProductRecommendVO.setId( bannerBO.getId() );
        adminsProductRecommendVO.setType( bannerBO.getType() );
        adminsProductRecommendVO.setProductSpuId( bannerBO.getProductSpuId() );
        adminsProductRecommendVO.setSort( bannerBO.getSort() );
        adminsProductRecommendVO.setStatus( bannerBO.getStatus() );
        adminsProductRecommendVO.setMemo( bannerBO.getMemo() );
        adminsProductRecommendVO.setCreateTime( bannerBO.getCreateTime() );

        return adminsProductRecommendVO;
    }

    @Override
    public CommonResult<AdminsProductRecommendVO> convert2(CommonResult<ProductRecommendBO> result) {
        if ( result == null ) {
            return null;
        }

        CommonResult<AdminsProductRecommendVO> commonResult = new CommonResult<AdminsProductRecommendVO>();

        commonResult.setCode( result.getCode() );
        commonResult.setMessage( result.getMessage() );
        commonResult.setData( convert( result.getData() ) );

        return commonResult;
    }

    @Override
    public CommonResult<AdminsProductRecommendPageVO> convert(CommonResult<ProductRecommendPageBO> result) {
        if ( result == null ) {
            return null;
        }

        CommonResult<AdminsProductRecommendPageVO> commonResult = new CommonResult<AdminsProductRecommendPageVO>();

        commonResult.setCode( result.getCode() );
        commonResult.setMessage( result.getMessage() );
        commonResult.setData( productRecommendPageBOToAdminsProductRecommendPageVO( result.getData() ) );

        return commonResult;
    }

    @Override
    public UsersProductRecommendVO convert(ProductSpuBO productSpu) {
        if ( productSpu == null ) {
            return null;
        }

        UsersProductRecommendVO usersProductRecommendVO = new UsersProductRecommendVO();

        usersProductRecommendVO.setId( productSpu.getId() );
        usersProductRecommendVO.setName( productSpu.getName() );
        usersProductRecommendVO.setSellPoint( productSpu.getSellPoint() );
        List<String> list = productSpu.getPicUrls();
        if ( list != null ) {
            usersProductRecommendVO.setPicUrls( new ArrayList<String>( list ) );
        }
        usersProductRecommendVO.setPrice( productSpu.getPrice() );

        return usersProductRecommendVO;
    }

    protected List<AdminsProductRecommendVO> productRecommendBOListToAdminsProductRecommendVOList(List<ProductRecommendBO> list) {
        if ( list == null ) {
            return null;
        }

        List<AdminsProductRecommendVO> list1 = new ArrayList<AdminsProductRecommendVO>( list.size() );
        for ( ProductRecommendBO productRecommendBO : list ) {
            list1.add( convert( productRecommendBO ) );
        }

        return list1;
    }

    protected AdminsProductRecommendPageVO productRecommendPageBOToAdminsProductRecommendPageVO(ProductRecommendPageBO productRecommendPageBO) {
        if ( productRecommendPageBO == null ) {
            return null;
        }

        AdminsProductRecommendPageVO adminsProductRecommendPageVO = new AdminsProductRecommendPageVO();

        adminsProductRecommendPageVO.setList( productRecommendBOListToAdminsProductRecommendVOList( productRecommendPageBO.getList() ) );
        adminsProductRecommendPageVO.setTotal( productRecommendPageBO.getTotal() );

        return adminsProductRecommendPageVO;
    }
}
