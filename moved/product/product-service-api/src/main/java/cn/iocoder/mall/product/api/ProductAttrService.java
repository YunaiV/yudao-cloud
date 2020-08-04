package cn.iocoder.mall.product.api;

import cn.iocoder.common.framework.enums.CommonStatusEnum;
import cn.iocoder.common.framework.validator.InEnum;
import cn.iocoder.mall.product.api.bo.ProductAttrBO;
import cn.iocoder.mall.product.api.bo.ProductAttrPageBO;
import cn.iocoder.mall.product.api.bo.ProductAttrSimpleBO;
import cn.iocoder.mall.product.api.bo.ProductAttrValueBO;
import cn.iocoder.mall.product.api.dto.*;

import java.util.List;

public interface ProductAttrService {


    /**
     * 获得规格属性数组
     *
     * 注意，该方法过滤了禁用的规格
     *
     * @return 规格属性数组
     */
    List<ProductAttrSimpleBO> getProductAttrList();

}
