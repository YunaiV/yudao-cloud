package cn.iocoder.mall.shopweb.service.product;

import cn.iocoder.common.framework.util.CollectionUtils;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.productservice.enums.spu.ProductSpuDetailFieldEnum;
import cn.iocoder.mall.productservice.rpc.category.ProductCategoryRpc;
import cn.iocoder.mall.productservice.rpc.category.dto.ProductCategoryRespDTO;
import cn.iocoder.mall.productservice.rpc.spu.ProductSpuRpc;
import cn.iocoder.mall.productservice.rpc.spu.dto.ProductSpuDetailRespDTO;
import cn.iocoder.mall.searchservice.enums.product.SearchProductConditionFieldEnum;
import cn.iocoder.mall.searchservice.rpc.product.SearchProductRpc;
import cn.iocoder.mall.searchservice.rpc.product.dto.SearchProductConditionReqDTO;
import cn.iocoder.mall.searchservice.rpc.product.dto.SearchProductConditionRespDTO;
import cn.iocoder.mall.searchservice.rpc.product.dto.SearchProductRespDTO;
import cn.iocoder.mall.shopweb.controller.product.vo.product.ProductSpuDetailRespVO;
import cn.iocoder.mall.shopweb.controller.product.vo.product.ProductSpuPageReqVO;
import cn.iocoder.mall.shopweb.controller.product.vo.product.ProductSpuRespVO;
import cn.iocoder.mall.shopweb.controller.product.vo.product.ProductSpuSearchConditionRespVO;
import cn.iocoder.mall.shopweb.convert.product.ProductSpuConvert;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 商品 SPU Manager
 */
@Service
@Validated
public class ProductSpuManager {

    @DubboReference(version = "${dubbo.consumer.SearchProductRpc.version}")
    private SearchProductRpc searchProductRpc;

    @DubboReference(version = "${dubbo.consumer.ProductCategoryRpc.version}")
    private ProductCategoryRpc productCategoryRpc;

    @DubboReference(version = "${dubbo.consumer.ProductSpuRpc.version}")
    private ProductSpuRpc productSpuRpc;

    public PageResult<ProductSpuRespVO> pageProductSpu(ProductSpuPageReqVO pageReqVO) {
        CommonResult<PageResult<SearchProductRespDTO>> pageResult =
                searchProductRpc.pageSearchProduct(ProductSpuConvert.INSTANCE.convert(pageReqVO));
        pageResult.checkError();
        return ProductSpuConvert.INSTANCE.convertPage(pageResult.getData());
    }

    public ProductSpuSearchConditionRespVO getProductSpuSearchCondition(String keyword) {
        // 获得搜索条件
        CommonResult<SearchProductConditionRespDTO> getSearchProductConditionResult =
                searchProductRpc.getSearchProductCondition(new SearchProductConditionReqDTO().setKeyword(keyword)
                    .setFields(Collections.singletonList(SearchProductConditionFieldEnum.CATEGORY.getField())));
        getSearchProductConditionResult.checkError();
        // 拼接结果
        ProductSpuSearchConditionRespVO conditionRespVO = new ProductSpuSearchConditionRespVO();
        if (CollectionUtils.isEmpty(getSearchProductConditionResult.getData().getCids())) {
            conditionRespVO.setCategories(Collections.emptyList());
        } else {
            CommonResult<List<ProductCategoryRespDTO>> listProductCategoriesResult =
                    productCategoryRpc.listProductCategories(getSearchProductConditionResult.getData().getCids());
            listProductCategoriesResult.checkError();
            conditionRespVO.setCategories(ProductSpuConvert.INSTANCE.convertList(listProductCategoriesResult.getData()));
        }
        return conditionRespVO;
    }

    public ProductSpuDetailRespVO getProductSpuDetail(Integer id) {
        CommonResult<ProductSpuDetailRespDTO> getProductSpuDetailResult = productSpuRpc.getProductSpuDetail(id,
                Arrays.asList(ProductSpuDetailFieldEnum.SKU.getField(), ProductSpuDetailFieldEnum.ATTR.getField()));
        getProductSpuDetailResult.checkError();
        return ProductSpuConvert.INSTANCE.convert(getProductSpuDetailResult.getData());
    }

}
