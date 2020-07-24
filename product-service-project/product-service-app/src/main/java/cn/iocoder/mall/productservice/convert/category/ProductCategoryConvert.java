package cn.iocoder.mall.productservice.convert.category;

import cn.iocoder.mall.productservice.dal.mysql.dataobject.category.ProductCategoryDO;
import cn.iocoder.mall.productservice.service.category.bo.ProductCategoryBO;
import cn.iocoder.mall.productservice.service.category.bo.ProductCategoryCreateBO;
import cn.iocoder.mall.productservice.service.category.bo.ProductCategoryUpdateBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductCategoryConvert {

    ProductCategoryConvert INSTANCE = Mappers.getMapper(ProductCategoryConvert.class);

    ProductCategoryDO convert(ProductCategoryCreateBO bean);

    ProductCategoryBO convert(ProductCategoryDO bean);

    List<ProductCategoryBO> convertList(List<ProductCategoryDO> list);

    ProductCategoryDO convert(ProductCategoryUpdateBO bean);

}
