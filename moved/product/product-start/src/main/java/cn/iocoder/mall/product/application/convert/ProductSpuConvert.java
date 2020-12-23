package cn.iocoder.mall.product.application.convert;

import cn.iocoder.mall.product.api.bo.ProductSpuBO;
import cn.iocoder.mall.product.api.bo.ProductSpuDetailBO;
import cn.iocoder.mall.product.api.bo.ProductSpuPageBO;
import cn.iocoder.mall.product.application.vo.admins.AdminsProductSpuDetailVO;
import cn.iocoder.mall.product.application.vo.admins.AdminsProductSpuPageVO;
import cn.iocoder.mall.product.application.vo.admins.AdminsProductSpuVO;
import cn.iocoder.mall.product.application.vo.users.UsersProductSpuDetailVO;
import cn.iocoder.mall.product.application.vo.users.UsersProductSpuPageVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductSpuConvert {

    ProductSpuConvert INSTANCE = Mappers.getMapper(ProductSpuConvert.class);

    @Mappings({})
    AdminsProductSpuDetailVO convert(ProductSpuDetailBO productSpuDetailBO);

//    @Mappings({})
//    CommonResult<AdminsProductSpuDetailVO> convert(CommonResult<ProductSpuDetailBO> result);

    @Mappings({})
    AdminsProductSpuPageVO convert2(ProductSpuPageBO result);

    @Mappings({})
    List<AdminsProductSpuVO> convert3(List<ProductSpuBO> result);

    @Mappings({})
    UsersProductSpuPageVO convert3(ProductSpuPageBO result);

    @Mappings({})
    UsersProductSpuDetailVO convert4(ProductSpuDetailBO result);

}
