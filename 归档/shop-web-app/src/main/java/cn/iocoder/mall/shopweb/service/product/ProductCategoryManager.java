package cn.iocoder.mall.shopweb.service.product;

import cn.iocoder.common.framework.enums.CommonStatusEnum;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.productservice.rpc.category.ProductCategoryFeign;
import cn.iocoder.mall.productservice.rpc.category.dto.ProductCategoryListQueryReqDTO;
import cn.iocoder.mall.productservice.rpc.category.dto.ProductCategoryRespDTO;
import cn.iocoder.mall.shopweb.controller.product.vo.category.ProductCategoryRespVO;
import cn.iocoder.mall.shopweb.convert.product.ProductCategoryConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Product 分类 Manager
 */
@Service
@Validated
public class ProductCategoryManager {


    @Autowired
    private ProductCategoryFeign productCategoryFeign;

    public List<ProductCategoryRespVO> listProductCategories(Integer pid) {
        CommonResult<List<ProductCategoryRespDTO>> listProductCategoriesResult = productCategoryFeign.listProductCategories(
                new ProductCategoryListQueryReqDTO().setPid(pid).setStatus(CommonStatusEnum.ENABLE.getValue()));
        listProductCategoriesResult.checkError();
        return ProductCategoryConvert.INSTANCE.convertList(listProductCategoriesResult.getData());
    }

}
