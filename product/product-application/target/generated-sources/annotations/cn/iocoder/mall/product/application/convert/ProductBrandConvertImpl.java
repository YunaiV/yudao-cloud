package cn.iocoder.mall.product.application.convert;

import cn.iocoder.mall.product.api.bo.ProductBrandBO;
import cn.iocoder.mall.product.api.bo.ProductBrangPageBO;
import cn.iocoder.mall.product.application.vo.admins.AdminsProductBrandVO;
import cn.iocoder.mall.product.application.vo.admins.AdminsProductBrangPageVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-05-31T18:12:33+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_121 (Oracle Corporation)"
)
public class ProductBrandConvertImpl implements ProductBrandConvert {

    @Override
    public AdminsProductBrandVO convert(ProductBrandBO result) {
        if ( result == null ) {
            return null;
        }

        AdminsProductBrandVO adminsProductBrandVO = new AdminsProductBrandVO();

        adminsProductBrandVO.setId( result.getId() );
        adminsProductBrandVO.setName( result.getName() );
        adminsProductBrandVO.setDescription( result.getDescription() );
        adminsProductBrandVO.setPicUrl( result.getPicUrl() );
        adminsProductBrandVO.setStatus( result.getStatus() );

        return adminsProductBrandVO;
    }

    @Override
    public AdminsProductBrangPageVO convert(ProductBrangPageBO result) {
        if ( result == null ) {
            return null;
        }

        AdminsProductBrangPageVO adminsProductBrangPageVO = new AdminsProductBrangPageVO();

        adminsProductBrangPageVO.setBrands( productBrandBOListToAdminsProductBrandVOList( result.getBrands() ) );
        adminsProductBrangPageVO.setCount( result.getCount() );

        return adminsProductBrangPageVO;
    }

    protected List<AdminsProductBrandVO> productBrandBOListToAdminsProductBrandVOList(List<ProductBrandBO> list) {
        if ( list == null ) {
            return null;
        }

        List<AdminsProductBrandVO> list1 = new ArrayList<AdminsProductBrandVO>( list.size() );
        for ( ProductBrandBO productBrandBO : list ) {
            list1.add( convert( productBrandBO ) );
        }

        return list1;
    }
}
