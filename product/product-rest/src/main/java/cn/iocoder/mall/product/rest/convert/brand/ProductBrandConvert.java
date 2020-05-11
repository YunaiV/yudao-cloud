package cn.iocoder.mall.product.rest.convert.brand;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.product.biz.bo.brand.ProductBrandBO;
import cn.iocoder.mall.product.biz.dto.brand.ProductBrandAddDTO;
import cn.iocoder.mall.product.biz.dto.brand.ProductBrandPageDTO;
import cn.iocoder.mall.product.biz.dto.brand.ProductBrandUpdateDTO;
import cn.iocoder.mall.product.rest.request.brand.ProductBrandAddRequest;
import cn.iocoder.mall.product.rest.request.brand.ProductBrandPageRequest;
import cn.iocoder.mall.product.rest.request.brand.ProductBrandUpdateRequest;
import cn.iocoder.mall.product.rest.response.brand.AdminsProductBrandResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductBrandConvert {

    ProductBrandConvert INSTANCE = Mappers.getMapper(ProductBrandConvert.class);

    @Mappings({})
    AdminsProductBrandResponse convert(ProductBrandBO brand);

    @Mappings({})
    ProductBrandAddDTO convertAdd(ProductBrandAddRequest addRequest);

    @Mappings({})
    ProductBrandUpdateDTO convertUpdate(ProductBrandUpdateRequest updateRequest);

    @Mappings({})
    ProductBrandPageDTO convertPageRequest(ProductBrandPageRequest pageRequest);

    @Mappings({})
    PageResult<AdminsProductBrandResponse> convertPage(PageResult<ProductBrandBO> productBrandPage);
}