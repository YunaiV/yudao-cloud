package cn.iocoder.mall.product.application.convert;

import cn.iocoder.mall.product.api.bo.ProductCategoryBO;
import cn.iocoder.mall.product.application.convert.ProductCategoryConvert.Users;
import cn.iocoder.mall.product.application.vo.users.UsersProductCategoryVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-05-24T11:47:10+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_121 (Oracle Corporation)"
)
public class ProductCategoryConvert$UsersImpl implements Users {

    @Override
    public UsersProductCategoryVO convertToVO(ProductCategoryBO category) {
        if ( category == null ) {
            return null;
        }

        UsersProductCategoryVO usersProductCategoryVO = new UsersProductCategoryVO();

        usersProductCategoryVO.setId( category.getId() );
        usersProductCategoryVO.setName( category.getName() );
        usersProductCategoryVO.setPicUrl( category.getPicUrl() );

        return usersProductCategoryVO;
    }

    @Override
    public List<UsersProductCategoryVO> convertToVO(List<ProductCategoryBO> categoryList) {
        if ( categoryList == null ) {
            return null;
        }

        List<UsersProductCategoryVO> list = new ArrayList<UsersProductCategoryVO>( categoryList.size() );
        for ( ProductCategoryBO productCategoryBO : categoryList ) {
            list.add( convertToVO( productCategoryBO ) );
        }

        return list;
    }
}
