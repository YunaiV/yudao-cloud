package cn.iocoder.mall.managementweb.feign;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.productservice.rpc.spu.dto.ProductSpuRespDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "product-service")
public interface ProductSpuFeign {
    /**
     * 获得商品 SPU
     *
     * @param productSpuId 商品 SPU 编号
     * @return 商品 SPU
     */
    @GetMapping(value = "/product/spu/get")
    CommonResult<ProductSpuRespDTO> getProductSpu(@RequestParam("productSpuId") Integer productSpuId);

}
