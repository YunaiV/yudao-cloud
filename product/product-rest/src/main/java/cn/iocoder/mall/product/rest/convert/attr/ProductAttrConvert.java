package cn.iocoder.mall.product.rest.convert.attr;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.product.biz.bo.attr.ProductAttrBO;
import cn.iocoder.mall.product.biz.dto.attr.AdminProductAttrPageDTO;
import cn.iocoder.mall.product.rest.request.attr.AdminProductAttrPageRequest;
import cn.iocoder.mall.product.rest.response.attr.AdminsProductAttrPageResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductAttrConvert {

    ProductAttrConvert INSTANCE = Mappers.getMapper(ProductAttrConvert.class);

    AdminProductAttrPageDTO convert(AdminProductAttrPageRequest bean);

    PageResult<AdminsProductAttrPageResponse> convertPage(PageResult<ProductAttrBO> productAttrPage);
}
