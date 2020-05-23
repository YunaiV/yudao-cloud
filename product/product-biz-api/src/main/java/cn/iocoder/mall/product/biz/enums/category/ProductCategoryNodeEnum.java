package cn.iocoder.mall.product.biz.enums.category;

import cn.iocoder.common.framework.core.IntArrayValuable;

import java.util.Arrays;

/**
 * @Author: jiangweifan
 * @Date: 2020/5/12
 * @Description: 商品分类节点枚举
 */
public enum ProductCategoryNodeEnum{

    /**
     * 根节点
     */
    ROOT(0);

    private final Integer id;

    ProductCategoryNodeEnum(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
