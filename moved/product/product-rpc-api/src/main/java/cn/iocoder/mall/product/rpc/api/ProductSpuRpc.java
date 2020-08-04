package cn.iocoder.mall.product.rpc.api;

import cn.iocoder.mall.product.rpc.response.ProductSpuDetailResponse;

/**
 * @author Rai
 */
public interface ProductSpuRpc {

    ProductSpuDetailResponse getProductSpuDetail(Integer spuId);

}
