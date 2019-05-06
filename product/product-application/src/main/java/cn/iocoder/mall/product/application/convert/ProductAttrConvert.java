package cn.iocoder.mall.product.application.convert;

import cn.iocoder.mall.product.api.bo.ProductAttrBO;
import cn.iocoder.mall.product.api.bo.ProductAttrPageBO;
import cn.iocoder.mall.product.api.bo.ProductAttrSimpleBO;
import cn.iocoder.mall.product.api.bo.ProductAttrValueBO;
import cn.iocoder.mall.product.application.vo.admins.AdminsProductAttrPageVO;
import cn.iocoder.mall.product.application.vo.admins.AdminsProductAttrSimpleVO;
import cn.iocoder.mall.product.application.vo.admins.AdminsProductAttrVO;
import cn.iocoder.mall.product.application.vo.admins.AdminsProductAttrValueVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductAttrConvert {

    ProductAttrConvert INSTANCE = Mappers.getMapper(ProductAttrConvert.class);

    @Mappings({})
    AdminsProductAttrPageVO convert2(ProductAttrPageBO result);

    @Mappings({})
    List<AdminsProductAttrSimpleVO> convert(List<ProductAttrSimpleBO> result);

    @Mappings({})
    AdminsProductAttrVO convert3(ProductAttrBO productAttrBO);

    @Mappings({})
    AdminsProductAttrValueVO convert4(ProductAttrValueBO productAttrValueBO);

}
