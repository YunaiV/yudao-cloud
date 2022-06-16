package cn.iocoder.mall.productservice.controller;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.productservice.manager.brand.ProductBrandManager;
import cn.iocoder.mall.productservice.rpc.brand.dto.ProductBrandCreateReqDTO;
import cn.iocoder.mall.productservice.rpc.brand.dto.ProductBrandPageReqDTO;
import cn.iocoder.mall.productservice.rpc.brand.dto.ProductBrandRespDTO;
import cn.iocoder.mall.productservice.rpc.brand.dto.ProductBrandUpdateReqDTO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cn.iocoder.common.framework.vo.CommonResult.success;

/**
 * Title:
 * Description:
 *
 * @author zhuyang
 * @version 1.0 2021/10/7
 */
@RestController
@RequestMapping("/product/brand")
@Api("商品品牌")
public class ProductBrandController {
    @Autowired
    private ProductBrandManager productBrandManager;

    /**
     * 创建商品品牌
     *
     * @param createDTO 创建商品品牌 DTO
     * @return 商品品牌编号
     */
    @PostMapping("createProductBrand")
    CommonResult<Integer> createProductBrand(@RequestBody ProductBrandCreateReqDTO createDTO){
        return success(productBrandManager.createProductBrand(createDTO));
    }

    /**
     * 更新商品品牌
     *
     * @param updateDTO 更新商品品牌 DTO
     */
    @PostMapping("updateProductBrand")
    CommonResult<Boolean> updateProductBrand(@RequestBody ProductBrandUpdateReqDTO updateDTO){
        productBrandManager.updateProductBrand(updateDTO);
        return success(true);
    }

    /**
     * 删除商品品牌
     *
     * @param productBrandId 商品品牌编号
     */
    @GetMapping("deleteProductBrand")
    CommonResult<Boolean> deleteProductBrand(@RequestParam("productBrandId") Integer productBrandId){
        productBrandManager.deleteProductBrand(productBrandId);
        return success(true);
    }

    /**
     * 获得商品品牌
     *
     * @param productBrandId 商品品牌编号
     * @return 商品品牌
     */
    @GetMapping("getProductBrand")
    CommonResult<ProductBrandRespDTO> getProductBrand(@RequestParam("productBrandId")Integer productBrandId){
        return success(productBrandManager.getProductBrand(productBrandId));
    }

    /**
     * 获得商品品牌列表
     *
     * @param productBrandIds 商品品牌编号列表
     * @return 商品品牌列表
     */
    @GetMapping("listProductBrands")
    CommonResult<List<ProductBrandRespDTO>> listProductBrands(@RequestParam("productBrandIds") List<Integer> productBrandIds){
        return success(productBrandManager.listProductBrands(productBrandIds));
    }

    /**
     * 获得商品品牌分页
     *
     * @param pageDTO 商品品牌分页查询
     * @return 商品品牌分页结果
     */
    @PostMapping("pageProductBrand")
    CommonResult<PageResult<ProductBrandRespDTO>> pageProductBrand(@RequestBody ProductBrandPageReqDTO pageDTO){
        return success(productBrandManager.pageProductBrand(pageDTO));
    }

}
