package cn.iocoder.mall.product.api;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.product.api.bo.*;
import cn.iocoder.mall.product.api.dto.ProductSpuAddDTO;
import cn.iocoder.mall.product.api.dto.ProductSpuPageDTO;
import cn.iocoder.mall.product.api.dto.ProductSpuUpdateDTO;

import java.util.Collection;
import java.util.List;

public interface ProductSpuService {

    CommonResult<ProductSpuDetailBO> getProductSpuDetail(Integer id);

    CommonResult<ProductSpuPageBO> getProductSpuPage(ProductSpuPageDTO productSpuPageDTO);

    CommonResult<List<ProductSpuBO>> getProductSpuList(Collection<Integer> ids);

    CommonResult<List<ProductSkuDetailBO>> getProductSkuDetailList(Collection<Integer> ids);

    CommonResult<ProductSpuDetailBO> addProductSpu(Integer adminId, ProductSpuAddDTO productSpuAddDTO);

    CommonResult<Boolean> updateProductSpu(Integer adminId, ProductSpuUpdateDTO productSpuUpdateDTO);

    CommonResult<Boolean> updateProductSpuSort(Integer adminId, Integer spuId, Integer sort);

}
