package cn.iocoder.mall.product.application.convert;

import cn.iocoder.common.framework.vo.CommonResult;
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
    CommonResult<AdminsProductAttrPageVO> convert2(CommonResult<ProductAttrPageBO> result);

    @Mappings({})
    CommonResult<List<AdminsProductAttrSimpleVO>> convert(CommonResult<List<ProductAttrSimpleBO>> result);

    @Mappings({})
    AdminsProductAttrVO convert3(ProductAttrBO productAttrBO);

    @Mappings({})
    CommonResult<AdminsProductAttrVO> convert3(CommonResult<ProductAttrBO> productAttrBO);

    @Mappings({})
    CommonResult<AdminsProductAttrValueVO> convert4(CommonResult<ProductAttrValueBO> productAttrValueBO);

}