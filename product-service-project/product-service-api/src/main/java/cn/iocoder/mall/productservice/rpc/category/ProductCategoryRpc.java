package cn.iocoder.mall.productservice.rpc.category;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.productservice.rpc.category.dto.ProductCategoryCreateReqDTO;
import cn.iocoder.mall.productservice.rpc.category.dto.ProductCategoryListQueryReqDTO;
import cn.iocoder.mall.productservice.rpc.category.dto.ProductCategoryRespDTO;
import cn.iocoder.mall.productservice.rpc.category.dto.ProductCategoryUpdateReqDTO;

import java.util.Collection;
import java.util.List;

/**
* 商品分类 Rpc 接口
*/
public interface ProductCategoryRpc {

    /**
    * 创建商品分类
    *
    * @param createDTO 创建商品分类 DTO
    * @return 商品分类编号
    */
    CommonResult<Integer> createProductCategory(ProductCategoryCreateReqDTO createDTO);

    /**
    * 更新商品分类
    *
    * @param updateDTO 更新商品分类 DTO
    */
    CommonResult<Boolean> updateProductCategory(ProductCategoryUpdateReqDTO updateDTO);

    /**
    * 删除商品分类
    *
    * @param productCategoryId 商品分类编号
    */
    CommonResult<Boolean> deleteProductCategory(Integer productCategoryId);

    /**
    * 获得商品分类
    *
    * @param productCategoryId 商品分类编号
    * @return 商品分类
    */
    CommonResult<ProductCategoryRespDTO> getProductCategory(Integer productCategoryId);

    /**
    * 获得商品分类列表
    *
    * @param productCategoryIds 商品分类编号列表
    * @return 商品分类列表
    */
    CommonResult<List<ProductCategoryRespDTO>> listProductCategories(Collection<Integer> productCategoryIds);

    /**
     * 获得符合条件的商品分类列表
     *
     * @return 商品分类列表
     */
    CommonResult<List<ProductCategoryRespDTO>> listProductCategories(ProductCategoryListQueryReqDTO listQueryReqDTO);

}
