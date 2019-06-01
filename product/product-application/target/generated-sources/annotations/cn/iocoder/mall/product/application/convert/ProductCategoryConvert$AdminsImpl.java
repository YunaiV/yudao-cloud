package cn.iocoder.mall.product.application.convert;

import cn.iocoder.mall.product.api.bo.ProductCategoryBO;
import cn.iocoder.mall.product.application.convert.ProductCategoryConvert.Admins;
import cn.iocoder.mall.product.application.vo.admins.AdminsProductCategoryTreeNodeVO;
import cn.iocoder.mall.product.application.vo.admins.AdminsProductCategoryVO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-05-24T11:47:09+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_121 (Oracle Corporation)"
)
public class ProductCategoryConvert$AdminsImpl implements Admins {

    @Override
    public AdminsProductCategoryTreeNodeVO convert(ProductCategoryBO category) {
        if ( category == null ) {
            return null;
        }

        AdminsProductCategoryTreeNodeVO adminsProductCategoryTreeNodeVO = new AdminsProductCategoryTreeNodeVO();

        adminsProductCategoryTreeNodeVO.setId( category.getId() );
        adminsProductCategoryTreeNodeVO.setPid( category.getPid() );
        adminsProductCategoryTreeNodeVO.setName( category.getName() );
        adminsProductCategoryTreeNodeVO.setDescription( category.getDescription() );
        adminsProductCategoryTreeNodeVO.setPicUrl( category.getPicUrl() );
        adminsProductCategoryTreeNodeVO.setSort( category.getSort() );
        adminsProductCategoryTreeNodeVO.setStatus( category.getStatus() );
        adminsProductCategoryTreeNodeVO.setCreateTime( category.getCreateTime() );

        return adminsProductCategoryTreeNodeVO;
    }

    @Override
    public AdminsProductCategoryVO convert2(ProductCategoryBO result) {
        if ( result == null ) {
            return null;
        }

        AdminsProductCategoryVO adminsProductCategoryVO = new AdminsProductCategoryVO();

        adminsProductCategoryVO.setId( result.getId() );
        adminsProductCategoryVO.setPid( result.getPid() );
        adminsProductCategoryVO.setName( result.getName() );
        adminsProductCategoryVO.setDescription( result.getDescription() );
        adminsProductCategoryVO.setPicUrl( result.getPicUrl() );
        adminsProductCategoryVO.setSort( result.getSort() );
        adminsProductCategoryVO.setStatus( result.getStatus() );
        adminsProductCategoryVO.setCreateTime( result.getCreateTime() );

        return adminsProductCategoryVO;
    }
}
