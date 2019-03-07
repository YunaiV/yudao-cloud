package cn.iocoder.mall.product.application.convert;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.product.api.bo.ProductSpuDetailBO;
import cn.iocoder.mall.product.api.bo.ProductSpuPageBO;
import cn.iocoder.mall.product.application.vo.admins.AdminsProductSpuDetailVO;
import cn.iocoder.mall.product.application.vo.admins.AdminsProductSpuPageVO;
import cn.iocoder.mall.product.application.vo.users.UsersProductSpuDetailVO;
import cn.iocoder.mall.product.application.vo.users.UsersProductSpuPageVO;
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

    @Mappings({})
    CommonResult<AdminsProductSpuPageVO> convert2(CommonResult<ProductSpuPageBO> result);

    @Mappings({})
    CommonResult<UsersProductSpuPageVO> convert3(CommonResult<ProductSpuPageBO> result);

    @Mappings({})
    CommonResult<UsersProductSpuDetailVO> convert4(CommonResult<ProductSpuDetailBO> result);

}