package cn.iocoder.mall.product.biz.service.product;

import cn.iocoder.mall.product.biz.bo.category.ProductCategoryAddBO;
import cn.iocoder.mall.product.biz.bo.category.ProductCategoryAllListBO;
import cn.iocoder.mall.product.biz.dto.category.ProductCategoryAddDTO;
import cn.iocoder.mall.product.biz.dto.category.ProductCategoryDeleteDTO;
import cn.iocoder.mall.product.biz.dto.category.ProductCategoryUpdateDTO;
import cn.iocoder.mall.product.biz.dto.category.ProductCategoryUpdateStatusDTO;

import java.util.List;


/**
 * @Author: jiangweifan
 * @Date: 2020/5/6
 * @Description: 商品分类 - 服务层
 */
public interface ProductCategoryService {

    /**
     * 获取所有商品分类
     * @return
     */
    List<ProductCategoryAllListBO> getAllProductCategory();

    /**
     * 新增商品分类
     * @param productCategoryAddDTO
     * @return
     */
    ProductCategoryAddBO addProductCategory(ProductCategoryAddDTO productCategoryAddDTO);

    /**
     * 更新商品分类
     * @param productCategoryUpdateDTO
     * @return
     */
    Boolean updateProductCategory(ProductCategoryUpdateDTO productCategoryUpdateDTO);

    /**
     * 更新商品分类状态
     * @param productCategoryUpdateStatusDTO
     * @return
     */
    Boolean updateProductCategoryStatus(ProductCategoryUpdateStatusDTO productCategoryUpdateStatusDTO);

    /**
     * 删除商品分类
     * @param productCategoryDeleteDTO
     * @return
     */
    Boolean deleteProductCategory(ProductCategoryDeleteDTO productCategoryDeleteDTO);
}
