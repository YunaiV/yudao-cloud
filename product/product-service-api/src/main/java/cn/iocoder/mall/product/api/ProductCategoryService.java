package cn.iocoder.mall.product.api;

import cn.iocoder.mall.product.api.bo.ProductCategoryBO;

import java.util.List;

public interface ProductCategoryService {

    List<ProductCategoryBO> getListByPid(Integer pid);

}