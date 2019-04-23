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

    /**
     * 增量获得商品列表，按照 lastId 递增获得
     *
     * @param lastId 最后查询的编号
     * @param limit 大小
     * @return 商品列表
     */
    CommonResult<List<ProductSpuDetailBO>> getProductSpuDetailListForSync(Integer lastId, Integer limit);

    CommonResult<ProductSpuPageBO> getProductSpuPage(ProductSpuPageDTO productSpuPageDTO);

    CommonResult<List<ProductSpuBO>> getProductSpuList(Collection<Integer> ids);

    CommonResult<ProductSkuBO> getProductSku(Integer id);

    CommonResult<List<ProductSkuDetailBO>> getProductSkuDetailList(Collection<Integer> ids);

    CommonResult<ProductSpuDetailBO> addProductSpu(Integer adminId, ProductSpuAddDTO productSpuAddDTO);

    CommonResult<Boolean> updateProductSpu(Integer adminId, ProductSpuUpdateDTO productSpuUpdateDTO);

    CommonResult<Boolean> updateProductSpuSort(Integer adminId, Integer spuId, Integer sort);

}
