package cn.iocoder.mall.product.application.convert;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.product.api.bo.ProductCategoryBO;
import cn.iocoder.mall.product.application.vo.ProductCategorySimpleVO;
import cn.iocoder.mall.product.application.vo.ProductCategoryTreeNodeVO;
import cn.iocoder.mall.product.application.vo.ProductCategoryVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductCategoryConvert {

    ProductCategoryConvert INSTANCE = Mappers.getMapper(ProductCategoryConvert.class);

    @Mappings({})
    ProductCategorySimpleVO convertToVO(ProductCategoryBO category);

    List<ProductCategorySimpleVO> convertToVO(List<ProductCategoryBO> categoryList);

    ProductCategoryTreeNodeVO convert(ProductCategoryBO category);

    CommonResult<ProductCategoryVO> convert(CommonResult<ProductCategoryBO> result);

}