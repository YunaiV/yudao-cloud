package cn.iocoder.mall.productservice.controller;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.productservice.manager.brand.ProductBrandManager;
import cn.iocoder.mall.productservice.manager.category.ProductCategoryManager;
import cn.iocoder.mall.productservice.rpc.brand.dto.ProductBrandCreateReqDTO;
import cn.iocoder.mall.productservice.rpc.brand.dto.ProductBrandPageReqDTO;
import cn.iocoder.mall.productservice.rpc.brand.dto.ProductBrandRespDTO;
import cn.iocoder.mall.productservice.rpc.brand.dto.ProductBrandUpdateReqDTO;
import cn.iocoder.mall.productservice.rpc.category.dto.ProductCategoryCreateReqDTO;
import cn.iocoder.mall.productservice.rpc.category.dto.ProductCategoryListQueryReqDTO;
import cn.iocoder.mall.productservice.rpc.category.dto.ProductCategoryRespDTO;
import cn.iocoder.mall.productservice.rpc.category.dto.ProductCategoryUpdateReqDTO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
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
@RequestMapping("/product/category")
@Api("商品品牌")
public class ProductCategoryController {
    @Autowired
    private ProductCategoryManager productCategoryManager;


    /**
     * 创建商品分类
     *
     * @param createDTO 创建商品分类 DTO
     * @return 商品分类编号
     */
    @PostMapping("createProductCategory")
    CommonResult<Integer> createProductCategory(@RequestBody ProductCategoryCreateReqDTO createDTO){
        return success(productCategoryManager.createProductCategory(createDTO));
    }

    /**
     * 更新商品分类
     *
     * @param updateDTO 更新商品分类 DTO
     */
    @PostMapping("updateProductCategory")
    CommonResult<Boolean> updateProductCategory(@RequestBody ProductCategoryUpdateReqDTO updateDTO){
        productCategoryManager.updateProductCategory(updateDTO);
        return success(true);

    }

    /**
     * 删除商品分类
     *
     * @param productCategoryId 商品分类编号
     */
    @GetMapping("deleteProductCategory")
    CommonResult<Boolean> deleteProductCategory(@RequestParam("productCategoryId") Integer productCategoryId){
        productCategoryManager.deleteProductCategory(productCategoryId);
        return success(true);
    }

    /**
     * 获得商品分类
     *
     * @param productCategoryId 商品分类编号
     * @return 商品分类
     */
    @GetMapping("getProductCategory")
    CommonResult<ProductCategoryRespDTO> getProductCategory(@RequestParam("productCategoryId")Integer productCategoryId){
        return success(productCategoryManager.getProductCategory(productCategoryId));
    }

    /**
     * 获得商品分类列表
     *
     * @param productCategoryIds 商品分类编号列表
     * @return 商品分类列表
     */
    @GetMapping("listProductCategoriesByIds")
    CommonResult<List<ProductCategoryRespDTO>> listProductCategoriesByIds(@RequestParam("productCategoryIds")Collection<Integer> productCategoryIds){
        return success(productCategoryManager.listProductCategories(productCategoryIds));
    }

    /**
     * 获得符合条件的商品分类列表
     *
     * @return 商品分类列表
     */
    @PostMapping("listProductCategories")
    CommonResult<List<ProductCategoryRespDTO>> listProductCategories(@RequestBody ProductCategoryListQueryReqDTO listQueryReqDTO){
        return success(productCategoryManager.listProductCategories(listQueryReqDTO));

    }


}
