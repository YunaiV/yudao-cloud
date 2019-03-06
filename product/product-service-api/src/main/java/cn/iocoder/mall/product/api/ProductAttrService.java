package cn.iocoder.mall.product.api;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.product.api.bo.ProductAttrBO;
import cn.iocoder.mall.product.api.bo.ProductAttrPageBO;
import cn.iocoder.mall.product.api.bo.ProductAttrSimpleBO;
import cn.iocoder.mall.product.api.bo.ProductAttrValueBO;
import cn.iocoder.mall.product.api.dto.*;

import java.util.List;

public interface ProductAttrService {

    CommonResult<ProductAttrPageBO> getProductAttrPage(ProductAttrPageDTO productAttrPageDTO);

    /**
     * 获得规格属性数组
     *
     * 注意，该方法过滤了禁用的规格
     *
     * @return 规格属性数组
     */
    CommonResult<List<ProductAttrSimpleBO>> getProductAttrList();

    CommonResult<ProductAttrBO> addProductAttr(Integer adminId, ProductAttrAddDTO productAttrAddDTO);

    CommonResult<Boolean> updateProductAttr(Integer adminId, ProductAttrUpdateDTO productAttrUpdateDTO);

    CommonResult<Boolean> updateProductAttrStatus(Integer adminId, Integer productAttrId, Integer status);


    CommonResult<ProductAttrValueBO> addProductAttrValue(Integer adminId, ProductAttrValueAddDTO productAttrValueAddDTO);

    CommonResult<Boolean> updateProductAttrValue(Integer adminId, ProductAttrValueUpdateDTO productAttrValueUpdateDTO);

    CommonResult<Boolean> updateProductAttrValueStatus(Integer adminId, Integer productAttrValueId, Integer status);

}