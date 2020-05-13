package cn.iocoder.mall.product.biz.service.category;

import cn.iocoder.mall.product.biz.bo.category.ProductCategoryBO;
import cn.iocoder.mall.product.biz.dataobject.category.ProductCategoryDO;
import cn.iocoder.mall.product.biz.dto.category.ProductCategoryAddDTO;
import cn.iocoder.mall.product.biz.dto.category.ProductCategoryDeleteDTO;
import cn.iocoder.mall.product.biz.dto.category.ProductCategoryUpdateDTO;
import cn.iocoder.mall.product.biz.dto.category.ProductCategoryUpdateStatusDTO;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;


/**
 * @Author: jiangweifan
 * @Date: 2020/5/6
 * @Description: 商品分类 - 服务层
 */
@Validated
public interface ProductCategoryService {

    /**
     * 获取所有商品分类
     *
     * @return
     */
    List<ProductCategoryBO> getAllProductCategory();

    /**
     * 新增商品分类
     *
     * @param productCategoryAddDTO
     * @return
     */
    ProductCategoryBO addProductCategory(@Valid ProductCategoryAddDTO productCategoryAddDTO);

    /**
     * 更新商品分类
     *
     * @param productCategoryUpdateDTO
     * @return
     */
    Boolean updateProductCategory(@Valid ProductCategoryUpdateDTO productCategoryUpdateDTO);

    /**
     * 更新商品分类状态
     *
     * @param productCategoryUpdateStatusDTO
     * @return
     */
    Boolean updateProductCategoryStatus(@Valid ProductCategoryUpdateStatusDTO productCategoryUpdateStatusDTO);

    /**
     * 删除商品分类
     *
     * @param productCategoryDeleteDTO
     * @return
     */
    Boolean deleteProductCategory(@Valid ProductCategoryDeleteDTO productCategoryDeleteDTO);

    /**
     * 校验分类是否可用
     *
     * @param productCategoryId 分类ID
     * @return 商品分类
     */
    ProductCategoryDO validProductCategory(Integer productCategoryId);
}
