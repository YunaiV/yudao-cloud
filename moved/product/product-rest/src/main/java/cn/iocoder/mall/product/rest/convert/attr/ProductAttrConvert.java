package cn.iocoder.mall.product.rest.convert.attr;

import cn.iocoder.mall.product.biz.bo.attr.ProductAttrSimpleWithValueBO;
import cn.iocoder.mall.product.rest.response.attr.AdminsProductAttrSimpleResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductAttrConvert {

    ProductAttrConvert INSTANCE = Mappers.getMapper(ProductAttrConvert.class);

    @Mappings({})
    List<AdminsProductAttrSimpleResponse> convertSimple(List<ProductAttrSimpleWithValueBO> simpleList);

}

