package cn.iocoder.mall.product.application.convert;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.product.api.bo.ProductCategoryBO;
import cn.iocoder.mall.product.application.vo.admins.AdminsProductCategoryTreeNodeVO;
import cn.iocoder.mall.product.application.vo.admins.AdminsProductCategoryVO;
import cn.iocoder.mall.product.application.vo.users.UsersProductCategoryVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

public interface ProductCategoryConvert {

    @Mapper
    interface Users {

        Users INSTANCE = Mappers.getMapper(Users.class);

        @Mappings({})
        UsersProductCategoryVO convertToVO(ProductCategoryBO category);

        @Mappings({})
        List<UsersProductCategoryVO> convertToVO(List<ProductCategoryBO> categoryList);

    }

    @Mapper
    interface Admins {

        Admins INSTANCE = Mappers.getMapper(Admins.class);

        @Mappings({})
        AdminsProductCategoryTreeNodeVO convert(ProductCategoryBO category);

        @Mappings({})
        CommonResult<AdminsProductCategoryVO> convert2(CommonResult<ProductCategoryBO> result);

    }

}
