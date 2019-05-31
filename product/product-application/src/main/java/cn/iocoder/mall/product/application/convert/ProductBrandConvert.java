package cn.iocoder.mall.product.application.convert;

import cn.iocoder.mall.product.api.bo.ProductBrandBO;
import cn.iocoder.mall.product.api.bo.ProductBrangPageBO;
import cn.iocoder.mall.product.application.vo.admins.AdminsProductBrandVO;
import cn.iocoder.mall.product.application.vo.admins.AdminsProductBrangPageVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductBrandConvert {

    ProductBrandConvert INSTANCE = Mappers.getMapper(ProductBrandConvert.class);

    @Mappings({})
    AdminsProductBrandVO convert(ProductBrandBO result);

    @Mappings({})
    AdminsProductBrangPageVO convert(ProductBrangPageBO result);

}
