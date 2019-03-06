package cn.iocoder.mall.product.api;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.product.api.bo.ProductAttrPageBO;
import cn.iocoder.mall.product.api.bo.ProductAttrSimpleBO;
import cn.iocoder.mall.product.api.dto.ProductAttrPageDTO;

import java.util.List;

public interface ProductAttrService {

    CommonResult<ProductAttrPageBO> getProductAttrPage(ProductAttrPageDTO productAttrPageDTO);

    /**
     * 获得规格属性数组
     *
     * 注意，该方法过滤了禁用的规格
     *
     * @return 规格属性数组
     */
    CommonResult<List<ProductAttrSimpleBO>> getProductAttrList();

}