package cn.iocoder.mall.product.api;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.product.api.bo.ProductSpuDetailBO;
import cn.iocoder.mall.product.api.bo.ProductSpuPageBO;
import cn.iocoder.mall.product.api.dto.ProductSpuAddDTO;
import cn.iocoder.mall.product.api.dto.ProductSpuPageDTO;
import cn.iocoder.mall.product.api.dto.ProductSpuUpdateDTO;

public interface ProductSpuService {

    CommonResult<ProductSpuDetailBO> getProductSpu(Integer id);

    CommonResult<ProductSpuDetailBO> addProductSpu(Integer adminId, ProductSpuAddDTO productSpuAddDTO);

    CommonResult<Boolean> updateProductSpu(Integer adminId, ProductSpuUpdateDTO productSpuUpdateDTO);

    CommonResult<Boolean> updateProductSpuSort(Integer adminId, Integer spuId, Integer sort);

    CommonResult<ProductSpuPageBO> getProductSpuPage(ProductSpuPageDTO productSpuPageDTO);

}