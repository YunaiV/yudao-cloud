package cn.iocoder.mall.product.biz.service.attr;

import cn.iocoder.mall.product.biz.bo.attr.ProductAttrSimpleWithValueBO;

import java.util.List;

public interface ProductAttrService {

    /**
     * 获得规格属性数组
     * <p>
     * 注意，该方法过滤了禁用的规格
     *
     * @return 规格属性数组
     */
    List<ProductAttrSimpleWithValueBO> getProductAttrList();

}
