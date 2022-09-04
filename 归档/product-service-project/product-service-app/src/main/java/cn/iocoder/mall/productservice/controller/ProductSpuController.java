package cn.iocoder.mall.productservice.controller;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.productservice.manager.spu.ProductSpuManager;
import cn.iocoder.mall.productservice.rpc.spu.dto.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.Collection;
import java.util.List;

import static cn.iocoder.common.framework.vo.CommonResult.success;

@RestController
@RequestMapping("/product/spu")
@Api("商品spu")
public class ProductSpuController {
    @Autowired
    private ProductSpuManager productSpuManager;

    @GetMapping("/get")
    @ApiOperation("获得商品 SPU")
    @ApiImplicitParam(name = "productSpuId", value = "商品 SPU 编号", required = true)
    public CommonResult<ProductSpuRespDTO> getProductSpu(@RequestParam(value="productSpuId") Integer productSpuId) {
        return success(productSpuManager.getProductSpu(productSpuId));
    }

    @GetMapping("/list")
    @ApiOperation("获得商品 SPU 列表")
    @ApiImplicitParam(name = "productSpuIds", value = "商品 SPU 编号列表", required = true)
    public CommonResult<List<ProductSpuRespDTO>> listProductSpus(@RequestParam("productSpuIds") List<Integer> productSpuIds) {
        return success(productSpuManager.listProductSpus(productSpuIds));
    }

    @PostMapping("/page")
    @ApiOperation("获得商品 SPU 分页")
    public CommonResult<PageResult<ProductSpuRespDTO>> pageProductSpu(@RequestBody ProductSpuPageReqDTO pageVO) {
        // 全部：无搜索条件
        // 在售中：visible = true && hasQuantity = true
        // 已售罄：visible  = true && hasQuantity = false
        // 仓库中：visible = false
        return success(productSpuManager.pageProductSpu(pageVO));
    }


    /**
            * 顺序获得商品 SPU 编号数组
     *
             * @param lastSpuId 最后一个商品 SPU 编号
     * @param limit 数量
     * @return 商品 SPU 编号数组
     */
    @GetMapping("/lislistProductSpuIdst")
    public CommonResult<List<Integer>> lislistProductSpuIdst(@RequestParam("lastSpuId")Integer lastSpuId, @RequestParam("limit")Integer limit) {
        // 全部：无搜索条件
        // 在售中：visible = true && hasQuantity = true
        // 已售罄：visible  = true && hasQuantity = false
        // 仓库中：visible = false
        return success(productSpuManager.listProductSpuIds(lastSpuId, limit));
    }


    @GetMapping("/getProductSpuDetail")
    public CommonResult<ProductSpuDetailRespDTO> getProductSpuDetail(@RequestParam("productSpuId") Integer productSpuId,@RequestParam("fields") Collection<String> fields) {
        return success(productSpuManager.getProductSpuDetail(productSpuId,fields));
    }
}
