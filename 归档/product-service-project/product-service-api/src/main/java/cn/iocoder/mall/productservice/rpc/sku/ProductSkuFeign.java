package cn.iocoder.mall.productservice.rpc.sku;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.productservice.rpc.sku.dto.ProductSkuListQueryReqDTO;
import cn.iocoder.mall.productservice.rpc.sku.dto.ProductSkuRespDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "product-service")
public interface ProductSkuFeign {
    @GetMapping("/product/sku/getProductSku")
    CommonResult<ProductSkuRespDTO> getProductSku(@RequestParam("productSkuId") Integer productSkuId);

    /**
     * 获得商品 SKU 列表
     *
     * @param queryReqDTO 商品 SKU 列表的查询请求 DTO
     * @return 商品 SKU 列表
     */
    @PostMapping("/product/sku/listProductSkus")
    CommonResult<List<ProductSkuRespDTO>> listProductSkus(@RequestBody ProductSkuListQueryReqDTO queryReqDTO);
}
