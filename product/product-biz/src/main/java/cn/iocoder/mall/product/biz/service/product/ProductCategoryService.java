package cn.iocoder.mall.product.biz.service.product;

import cn.iocoder.mall.product.biz.bo.category.ProductCategoryAddBO;
import cn.iocoder.mall.product.biz.bo.category.ProductCategoryAllListBO;
import cn.iocoder.mall.product.biz.dto.category.ProductCategoryAddDTO;
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
}
