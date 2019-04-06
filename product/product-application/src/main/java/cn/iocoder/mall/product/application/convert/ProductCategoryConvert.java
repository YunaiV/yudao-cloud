package cn.iocoder.mall.product.application.convert;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.product.api.bo.ProductCategoryBO;
import cn.iocoder.mall.product.application.vo.users.UsersProductCategoryVO;
import cn.iocoder.mall.product.application.vo.admins.AdminsProductCategoryTreeNodeVO;
import cn.iocoder.mall.product.application.vo.admins.AdminsProductCategoryVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductCategoryConvert {

    ProductCategoryConvert INSTANCE = Mappers.getMapper(ProductCategoryConvert.class);

    @Mappings({})
    UsersProductCategoryVO convertToVO(ProductCategoryBO category);

    @Mappings({})
    List<UsersProductCategoryVO> convertToVO(List<ProductCategoryBO> categoryList);

    @Mappings({})
    AdminsProductCategoryTreeNodeVO convert(ProductCategoryBO category);

    @Mappings({})
    CommonResult<AdminsProductCategoryVO> convert2(CommonResult<ProductCategoryBO> result);

}
