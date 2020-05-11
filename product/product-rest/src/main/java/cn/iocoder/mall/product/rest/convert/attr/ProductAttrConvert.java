package cn.iocoder.mall.product.rest.convert.attr;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.product.biz.bo.attr.ProductAttrBO;
import cn.iocoder.mall.product.biz.bo.attr.ProductAttrSimpleWithValueBO;
import cn.iocoder.mall.product.biz.bo.attr.ProductAttrValueBO;
import cn.iocoder.mall.product.biz.bo.attr.ProductAttrWithValueBO;
import cn.iocoder.mall.product.biz.dto.attr.ProductAttrPageDTO;
import cn.iocoder.mall.product.biz.dto.attr.ProductAttrUpdateDTO;
import cn.iocoder.mall.product.rest.request.attr.ProductAttrPageRequest;
import cn.iocoder.mall.product.rest.request.attr.ProductAttrUpdateRequest;
import cn.iocoder.mall.product.rest.response.attr.AdminsProductAttrPageResponse;
import cn.iocoder.mall.product.rest.response.attr.AdminsProductAttrSimpleResponse;
import cn.iocoder.mall.product.rest.response.attr.AdminsProdutAttrResponse;
import cn.iocoder.mall.product.rest.response.attr.AdminsProductAttrValueResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductAttrConvert {

    ProductAttrConvert INSTANCE = Mappers.getMapper(ProductAttrConvert.class);

    @Mappings({})
    ProductAttrPageDTO convert(ProductAttrPageRequest bean);

    @Mappings({})
    PageResult<AdminsProductAttrPageResponse> convertPage(PageResult<ProductAttrWithValueBO> productAttrPage);

    @Mappings({})
    List<AdminsProductAttrSimpleResponse> convertSimple(List<ProductAttrSimpleWithValueBO> simpleList);

    @Mappings({})
    AdminsProdutAttrResponse convertAttr(ProductAttrBO attrBO);

    @Mappings({})
    ProductAttrUpdateDTO convertUpdate(ProductAttrUpdateRequest updateRequest);

    @Mappings({})
    AdminsProductAttrValueResponse convertAddResponse(ProductAttrValueBO result);
}

