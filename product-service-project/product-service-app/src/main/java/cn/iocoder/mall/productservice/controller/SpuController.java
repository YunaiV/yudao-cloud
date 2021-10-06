package cn.iocoder.mall.productservice.controller;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.productservice.manager.spu.ProductSpuManager;
import cn.iocoder.mall.productservice.rpc.spu.dto.ProductSpuRespDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static cn.iocoder.common.framework.vo.CommonResult.success;

@RestController
@RequestMapping("/product/spu")
@Api("商品spu")
public class SpuController {
    @Autowired
    private ProductSpuManager productSpuManager;

    @GetMapping("/get")
    @ApiOperation("获得商品 SPU")
    @ApiImplicitParam(name = "productSpuId", value = "商品 SPU 编号", required = true)
    public CommonResult<ProductSpuRespDTO> getProductSpu(@RequestParam(value="productSpuId") Integer productSpuId) {
        return success(productSpuManager.getProductSpu(productSpuId));
    }

}
