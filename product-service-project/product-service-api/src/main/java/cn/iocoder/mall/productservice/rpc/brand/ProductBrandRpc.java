package cn.iocoder.mall.productservice.rpc.brand;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.productservice.rpc.brand.dto.ProductBrandCreateReqDTO;
import cn.iocoder.mall.productservice.rpc.brand.dto.ProductBrandPageReqDTO;
import cn.iocoder.mall.productservice.rpc.brand.dto.ProductBrandRespDTO;
import cn.iocoder.mall.productservice.rpc.brand.dto.ProductBrandUpdateReqDTO;

import java.util.List;

/**
* 商品品牌 Rpc 接口
*/
public interface ProductBrandRpc {

    /**
    * 创建商品品牌
    *
    * @param createDTO 创建商品品牌 DTO
    * @return 商品品牌编号
    */
    CommonResult<Integer> createProductBrand(ProductBrandCreateReqDTO createDTO);

    /**
    * 更新商品品牌
    *
    * @param updateDTO 更新商品品牌 DTO
    */
    CommonResult<Boolean> updateProductBrand(ProductBrandUpdateReqDTO updateDTO);

    /**
    * 删除商品品牌
    *
    * @param productBrandId 商品品牌编号
    */
    CommonResult<Boolean> deleteProductBrand(Integer productBrandId);

    /**
    * 获得商品品牌
    *
    * @param productBrandId 商品品牌编号
    * @return 商品品牌
    */
    CommonResult<ProductBrandRespDTO> getProductBrand(Integer productBrandId);

    /**
    * 获得商品品牌列表
    *
    * @param productBrandIds 商品品牌编号列表
    * @return 商品品牌列表
    */
    CommonResult<List<ProductBrandRespDTO>> listProductBrands(List<Integer> productBrandIds);

    /**
    * 获得商品品牌分页
    *
    * @param pageDTO 商品品牌分页查询
    * @return 商品品牌分页结果
    */
    CommonResult<PageResult<ProductBrandRespDTO>> pageProductBrand(ProductBrandPageReqDTO pageDTO);

}
