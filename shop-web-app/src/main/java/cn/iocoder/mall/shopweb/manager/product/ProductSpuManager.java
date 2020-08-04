package cn.iocoder.mall.shopweb.manager.product;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.searchservice.rpc.product.SearchProductRpc;
import cn.iocoder.mall.searchservice.rpc.product.dto.SearchProductRespDTO;
import cn.iocoder.mall.shopweb.controller.product.vo.product.ProductSpuPageReqVO;
import cn.iocoder.mall.shopweb.controller.product.vo.product.ProductSpuRespVO;
import cn.iocoder.mall.shopweb.convert.product.ProductSpuConvert;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * Product SPU Manager
 */
@Service
@Validated
public class ProductSpuManager {

    @DubboReference(version = "${dubbo.consumer.SearchProductRpc.version}")
    private SearchProductRpc searchProductRpc;

    public PageResult<ProductSpuRespVO> pageProductSpu(ProductSpuPageReqVO pageReqVO) {
        CommonResult<PageResult<SearchProductRespDTO>> pageResult =
                searchProductRpc.pageSearchProduct(ProductSpuConvert.INSTANCE.convert(pageReqVO));
        pageResult.checkError();
        return ProductSpuConvert.INSTANCE.convertPage(pageResult.getData());
    }

}
