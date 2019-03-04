package cn.iocoder.mall.product.api;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.product.api.bo.ProductCategoryBO;
import cn.iocoder.mall.product.api.dto.ProductCategoryAddDTO;
import cn.iocoder.mall.product.api.dto.ProductCategoryUpdateDTO;

import java.util.List;

public interface ProductCategoryService {

    /**
     * @param pid 指定父分类编号
     * @return 返回指定分类编号的子产品分类们
     */
    List<ProductCategoryBO> getListByPid(Integer pid);

    /**
     * @return 返回所有产品分类们
     */
    CommonResult<List<ProductCategoryBO>> getAll();

    CommonResult<ProductCategoryBO> addProductCategory(Integer adminId, ProductCategoryAddDTO productCategoryAddDTO);

    CommonResult<Boolean> updateProductCategory(Integer adminId, ProductCategoryUpdateDTO productCategoryUpdateDTO);

    CommonResult<Boolean> updateProductCategoryStatus(Integer adminId, Integer productCategoryId, Integer status);

    CommonResult<Boolean> deleteProductCategory(Integer admin, Integer productCategoryId);

}