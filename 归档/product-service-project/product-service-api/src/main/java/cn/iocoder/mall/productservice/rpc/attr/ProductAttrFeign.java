package cn.iocoder.mall.productservice.rpc.attr;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.productservice.rpc.attr.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 商品规格 Rpc 接口
 */
@FeignClient(value = "product-service")
public interface ProductAttrFeign {

    /**
     * 创建商品规格键
     *
     * @param createDTO 创建商品规格键 DTO
     * @return 商品规格键编号
     */
    @PostMapping(value = "/product/attr/createProductAttrKey")
    CommonResult<Integer> createProductAttrKey(@RequestBody ProductAttrKeyCreateReqDTO createDTO);

    /**
     * 更新商品规格键
     *
     * @param updateDTO 更新商品规格键 DTO
     */
    @PostMapping(value = "/product/attr/updateProductAttrKey")
    CommonResult<Boolean> updateProductAttrKey(@RequestBody ProductAttrKeyUpdateReqDTO updateDTO);

    /**
     * 获得商品规格键
     *
     * @param productAttrKeyId 商品规格键编号
     * @return 商品规格键
     */
    @GetMapping("/product/attr/getProductAttrKey")
    CommonResult<ProductAttrKeyRespDTO> getProductAttrKey(@RequestParam("productAttrKeyId") Integer productAttrKeyId);

    /**
     * 获得商品规格键列表
     *
     * @param productAttrKeyIds 商品规格键编号列表
     * @return 商品规格键列表
     */
    @GetMapping("/product/attr/listProductAttrKeys")
    CommonResult<List<ProductAttrKeyRespDTO>> listProductAttrKeys(@RequestParam("productAttrKeyIds") List<Integer> productAttrKeyIds);

    /**
     * 获得商品规格键分页
     *
     * @param pageDTO 商品规格键分页查询
     * @return 商品规格键分页结果
     */
    @PostMapping("/product/attr/pageProductAttrKey")
    CommonResult<PageResult<ProductAttrKeyRespDTO>> pageProductAttrKey(@RequestBody ProductAttrKeyPageReqDTO pageDTO);

    /**
     * 创建商品规格值
     *
     * @param createDTO 创建商品规格值 DTO
     * @return 商品规格值编号
     */
    @PostMapping("/product/attr/createProductAttrValue")
    CommonResult<Integer> createProductAttrValue(@RequestBody ProductAttrValueCreateReqDTO createDTO);


    /**
     * 更新商品规格值
     *
     * @param updateDTO 更新商品规格值 DTO
     */
    @PostMapping("/product/attr/updateProductAttrValue")
    CommonResult<Boolean> updateProductAttrValue(@RequestBody ProductAttrValueUpdateReqDTO updateDTO);

    /**
     * 获得商品规格值
     *
     * @param productAttrValueId 商品规格值编号
     * @return 商品规格值
     */
    @GetMapping("/product/attr/getProductAttrValue")
    CommonResult<ProductAttrValueRespDTO> getProductAttrValue(@RequestParam("productAttrValueId") Integer productAttrValueId);

    @PostMapping("/product/attr/listProductAttrValues")
    CommonResult<List<ProductAttrValueRespDTO>> listProductAttrValues(@RequestBody ProductAttrValueListQueryReqDTO queryDTO);

}