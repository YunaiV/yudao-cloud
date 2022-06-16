package cn.iocoder.mall.productservice.controller;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.productservice.manager.attr.ProductAttrManager;
import cn.iocoder.mall.productservice.manager.sku.ProductSkuManager;
import cn.iocoder.mall.productservice.rpc.attr.dto.*;
import cn.iocoder.mall.productservice.rpc.sku.dto.ProductSkuListQueryReqDTO;
import cn.iocoder.mall.productservice.rpc.sku.dto.ProductSkuRespDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cn.iocoder.common.framework.vo.CommonResult.success;

@RestController
@RequestMapping("/product/sku")
@Api("商品sku")
public class ProductSkuController {
    @Autowired
    private ProductSkuManager productSkuManager;
    /**
     * 获得商品 SKU
     *
     * @param productSkuId 商品 SKU 编号
     * @return 商品 SKU
     */
    @GetMapping("getProductSku")
    CommonResult<ProductSkuRespDTO> getProductSku(@RequestParam("productSkuId") Integer productSkuId){
        return success(productSkuManager.getProductSku(productSkuId));
    }

    /**
     * 获得商品 SKU 列表
     *
     * @param queryReqDTO 商品 SKU 列表的查询请求 DTO
     * @return 商品 SKU 列表
     */
    @PostMapping("listProductSkus")
    CommonResult<List<ProductSkuRespDTO>> listProductSkus(@RequestBody ProductSkuListQueryReqDTO queryReqDTO){
        return success(productSkuManager.listProductSkus(queryReqDTO));
    }

}
