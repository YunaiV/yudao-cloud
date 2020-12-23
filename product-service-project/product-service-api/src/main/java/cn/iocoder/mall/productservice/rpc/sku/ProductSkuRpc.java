package cn.iocoder.mall.productservice.rpc.sku;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.productservice.rpc.sku.dto.ProductSkuListQueryReqDTO;
import cn.iocoder.mall.productservice.rpc.sku.dto.ProductSkuRespDTO;

import java.util.List;

/**
 * 商品 SKU Rpc 接口
 */
public interface ProductSkuRpc {

    /**
     * 获得商品 SKU
     *
     * @param productSkuId 商品 SKU 编号
     * @return 商品 SKU
     */
    CommonResult<ProductSkuRespDTO> getProductSku(Integer productSkuId);

    /**
     * 获得商品 SKU 列表
     *
     * @param queryReqDTO 商品 SKU 列表的查询请求 DTO
     * @return 商品 SKU 列表
     */
    CommonResult<List<ProductSkuRespDTO>> listProductSkus(ProductSkuListQueryReqDTO queryReqDTO);

}
