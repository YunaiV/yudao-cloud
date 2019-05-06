package cn.iocoder.mall.product.api;

import cn.iocoder.common.framework.constant.CommonStatusEnum;
import cn.iocoder.common.framework.validator.InEnum;
import cn.iocoder.mall.product.api.bo.ProductCategoryBO;
import cn.iocoder.mall.product.api.dto.ProductCategoryAddDTO;
import cn.iocoder.mall.product.api.dto.ProductCategoryUpdateDTO;

import java.util.Collection;
import java.util.List;

public interface ProductCategoryService {

    /**
     * @param pid 指定父分类编号
     * @return 返回指定分类编号的子产品分类们
     */
    List<ProductCategoryBO> getListByPid(Integer pid);

    /**
     * 获得商品分类数组
     *
     * @param ids 商品分类编号
     * @return 数组
     */
    List<ProductCategoryBO> getListByIds(Collection<Integer> ids);

    /**
     * @return 返回所有产品分类们
     */
    List<ProductCategoryBO> getAll();

    ProductCategoryBO addProductCategory(Integer adminId, ProductCategoryAddDTO productCategoryAddDTO);

    Boolean updateProductCategory(Integer adminId, ProductCategoryUpdateDTO productCategoryUpdateDTO);

    Boolean updateProductCategoryStatus(Integer adminId, Integer productCategoryId,
                                        @InEnum(value = CommonStatusEnum.class, message = "修改状态必须是 {value}") Integer status);

    Boolean deleteProductCategory(Integer admin, Integer productCategoryId);

}
