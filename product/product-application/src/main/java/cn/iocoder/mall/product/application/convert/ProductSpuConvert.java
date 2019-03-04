package cn.iocoder.mall.product.application.convert;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.product.api.bo.ProductSpuDetailBO;
import cn.iocoder.mall.product.application.vo.admins.AdminsProductSpuDetailVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductSpuConvert {

    ProductSpuConvert INSTANCE = Mappers.getMapper(ProductSpuConvert.class);

    @Mappings({})
    AdminsProductSpuDetailVO convert(ProductSpuDetailBO productSpuDetailBO);

    @Mappings({})
    CommonResult<AdminsProductSpuDetailVO> convert(CommonResult<ProductSpuDetailBO> result);

}