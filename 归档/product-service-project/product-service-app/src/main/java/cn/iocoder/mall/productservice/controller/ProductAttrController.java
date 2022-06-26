package cn.iocoder.mall.productservice.controller;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.productservice.manager.attr.ProductAttrManager;
import cn.iocoder.mall.productservice.rpc.attr.dto.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cn.iocoder.common.framework.vo.CommonResult.success;

@RestController
@RequestMapping("/product/attr")
@Api("商品属性")
public class ProductAttrController {
    @Autowired
    private ProductAttrManager productAttrManager;

    @PostMapping("/createProductAttrKey")
    @ApiOperation("创建商品规格键")
    CommonResult<Integer> createProductAttrKey(@RequestBody ProductAttrKeyCreateReqDTO createDTO){
        return success(productAttrManager.createProductAttrKey(createDTO));
    }

    /**
     * 更新商品规格键
     *
     * @param updateDTO 更新商品规格键 DTO
     */
    @PostMapping("/updateProductAttrKey")
    @ApiOperation("更新商品规格键")
    CommonResult<Boolean> updateProductAttrKey(@RequestBody ProductAttrKeyUpdateReqDTO updateDTO){
        productAttrManager.updateProductAttrKey(updateDTO);
        return success(true);
    }

    /**
     * 获得商品规格键
     *
     * @param productAttrKeyId 商品规格键编号
     * @return 商品规格键
     */
    @GetMapping("/getProductAttrKey")
    @ApiOperation("获得商品规格键")
    CommonResult<ProductAttrKeyRespDTO> getProductAttrKey(@RequestParam("productAttrKeyId") Integer productAttrKeyId){
        return success(productAttrManager.getProductAttrKey(productAttrKeyId));
    }


    /**
     * 获得商品规格键列表
     *
     * @param productAttrKeyIds 商品规格键编号列表
     * @return 商品规格键列表
     */
    @GetMapping("/listProductAttrKeys")
    @ApiOperation("获得商品规格键列表")
    CommonResult<List<ProductAttrKeyRespDTO>> listProductAttrKeys(@RequestParam("productAttrKeyIds") List<Integer> productAttrKeyIds){
        return success(productAttrManager.listProductAttrKeys(productAttrKeyIds));
    }

    /**
     * 获得商品规格键分页
     *
     * @param pageDTO 商品规格键分页查询
     * @return 商品规格键分页结果
     */
    @PostMapping("/pageProductAttrKey")
    @ApiOperation("获得商品规格键分页")
    CommonResult<PageResult<ProductAttrKeyRespDTO>> pageProductAttrKey(@RequestBody ProductAttrKeyPageReqDTO pageDTO){
        return success(productAttrManager.pageProductAttrKey(pageDTO));
    }

    /**
     * 创建商品规格值
     *
     * @param createDTO 创建商品规格值 DTO
     * @return 商品规格值编号
     */
    @PostMapping("/createProductAttrValue")
    @ApiOperation("创建商品规格值")
    CommonResult<Integer> createProductAttrValue(@RequestBody ProductAttrValueCreateReqDTO createDTO){
        return success(productAttrManager.createProductAttrValue(createDTO));
    }



    /**
     * 更新商品规格值
     *
     * @param updateDTO 更新商品规格值 DTO
     */
    @PostMapping("/updateProductAttrValue")
    @ApiOperation("更新商品规格值")
    CommonResult<Boolean> updateProductAttrValue(@RequestBody ProductAttrValueUpdateReqDTO updateDTO){
        productAttrManager.updateProductAttrValue(updateDTO);
        return success(true);
    }

    /**
     * 获得商品规格值
     *
     * @param productAttrValueId 商品规格值编号
     * @return 商品规格值
     */
    @GetMapping("/getProductAttrValue")
    @ApiOperation("获得商品规格值")
    CommonResult<ProductAttrValueRespDTO> getProductAttrValue(@RequestParam("productAttrValueId") Integer productAttrValueId){
        return success(productAttrManager.getProductAttrValue(productAttrValueId));
    }

    /**
     * 获得商品规格值列表
     *
     * @param queryDTO 商品规格值的列表查询条件 DTO
     * @return 商品规格值列表
     */
    @PostMapping("/listProductAttrValues")
    @ApiOperation("获得商品规格值列表")
    CommonResult<List<ProductAttrValueRespDTO>> listProductAttrValues(@RequestBody ProductAttrValueListQueryReqDTO queryDTO){
        return success(productAttrManager.listProductAttrValues(queryDTO));
    }

}
