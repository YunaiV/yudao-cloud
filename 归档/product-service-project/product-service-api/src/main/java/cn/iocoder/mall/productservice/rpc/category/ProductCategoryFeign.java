package cn.iocoder.mall.productservice.rpc.category;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.productservice.rpc.category.dto.ProductCategoryCreateReqDTO;
import cn.iocoder.mall.productservice.rpc.category.dto.ProductCategoryListQueryReqDTO;
import cn.iocoder.mall.productservice.rpc.category.dto.ProductCategoryRespDTO;
import cn.iocoder.mall.productservice.rpc.category.dto.ProductCategoryUpdateReqDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;

@FeignClient(value = "product-service")
public interface ProductCategoryFeign {

    /**
     * 创建商品分类
     *
     * @param createDTO 创建商品分类 DTO
     * @return 商品分类编号
     */
    @PostMapping("/product/category/createProductCategory")
    CommonResult<Integer> createProductCategory(@RequestBody ProductCategoryCreateReqDTO createDTO);
    /**
     * 更新商品分类
     *
     * @param updateDTO 更新商品分类 DTO
     */
    @PostMapping("/product/category/updateProductCategory")
    CommonResult<Boolean> updateProductCategory(@RequestBody ProductCategoryUpdateReqDTO updateDTO);

    /**
     * 删除商品分类
     *
     * @param productCategoryId 商品分类编号
     */
    @GetMapping("/product/category/deleteProductCategory")
    CommonResult<Boolean> deleteProductCategory(@RequestParam("productCategoryId") Integer productCategoryId);
    /**
     * 获得商品分类
     *
     * @param productCategoryId 商品分类编号
     * @return 商品分类
     */
    @GetMapping("/product/category/getProductCategory")
    CommonResult<ProductCategoryRespDTO> getProductCategory(@RequestParam("productCategoryId")Integer productCategoryId);
    /**
     * 获得商品分类列表
     *
     * @param productCategoryIds 商品分类编号列表
     * @return 商品分类列表
     */
    @GetMapping("/product/category/listProductCategoriesByIds")
    CommonResult<List<ProductCategoryRespDTO>> listProductCategoriesByIds(@RequestParam("productCategoryIds")Collection<Integer> productCategoryIds);

    /**
     * 获得符合条件的商品分类列表
     *
     * @return 商品分类列表
     */
    @PostMapping("/product/category/listProductCategories")
    CommonResult<List<ProductCategoryRespDTO>> listProductCategories(@RequestBody ProductCategoryListQueryReqDTO listQueryReqDTO);

}
