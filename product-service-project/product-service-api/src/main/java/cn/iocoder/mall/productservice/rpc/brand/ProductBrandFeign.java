package cn.iocoder.mall.productservice.rpc.brand;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.productservice.rpc.brand.dto.ProductBrandCreateReqDTO;
import cn.iocoder.mall.productservice.rpc.brand.dto.ProductBrandPageReqDTO;
import cn.iocoder.mall.productservice.rpc.brand.dto.ProductBrandRespDTO;
import cn.iocoder.mall.productservice.rpc.brand.dto.ProductBrandUpdateReqDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "product-service")
public interface ProductBrandFeign {
    /**
     * 创建商品品牌
     *
     * @param createDTO 创建商品品牌 DTO
     * @return 商品品牌编号
     */
    @PostMapping("/product/brand/createProductBrand")
    CommonResult<Integer> createProductBrand(@RequestBody ProductBrandCreateReqDTO createDTO);

    /**
     * 更新商品品牌
     *
     * @param updateDTO 更新商品品牌 DTO
     */
    @PostMapping("/product/brand/updateProductBrand")
    CommonResult<Boolean> updateProductBrand(@RequestBody ProductBrandUpdateReqDTO updateDTO);

    /**
     * 删除商品品牌
     *
     * @param productBrandId 商品品牌编号
     */
    @GetMapping("/product/brand/deleteProductBrand")
    CommonResult<Boolean> deleteProductBrand(@RequestParam("productBrandId") Integer productBrandId);
    /**
     * 获得商品品牌
     *
     * @param productBrandId 商品品牌编号
     * @return 商品品牌
     */
    @GetMapping("/product/brand/getProductBrand")
    CommonResult<ProductBrandRespDTO> getProductBrand(@RequestParam("productBrandId")Integer productBrandId);
    /**
     * 获得商品品牌列表
     *
     * @param productBrandIds 商品品牌编号列表
     * @return 商品品牌列表
     */
    @GetMapping("/product/brand/listProductBrands")
    CommonResult<List<ProductBrandRespDTO>> listProductBrands(@RequestParam("productBrandIds") List<Integer> productBrandIds);

    /**
     * 获得商品品牌分页
     *
     * @param pageDTO 商品品牌分页查询
     * @return 商品品牌分页结果
     */
    @PostMapping("/product/brand/pageProductBrand")
    CommonResult<PageResult<ProductBrandRespDTO>> pageProductBrand(@RequestBody ProductBrandPageReqDTO pageDTO);

}
