package cn.iocoder.mall.searchservice.manager.product;

import cn.iocoder.common.framework.util.CollectionUtils;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.productservice.rpc.category.ProductCategoryRpc;
import cn.iocoder.mall.productservice.rpc.category.dto.ProductCategoryRespDTO;
import cn.iocoder.mall.productservice.rpc.sku.ProductSkuRpc;
import cn.iocoder.mall.productservice.rpc.sku.dto.ProductSkuListQueryReqDTO;
import cn.iocoder.mall.productservice.rpc.sku.dto.ProductSkuRespDTO;
import cn.iocoder.mall.productservice.rpc.spu.ProductSpuRpc;
import cn.iocoder.mall.productservice.rpc.spu.dto.ProductSpuRespDTO;
import cn.iocoder.mall.searchservice.convert.product.SearchProductConvert;
import cn.iocoder.mall.searchservice.rpc.product.dto.SearchProductConditionReqDTO;
import cn.iocoder.mall.searchservice.rpc.product.dto.SearchProductConditionRespDTO;
import cn.iocoder.mall.searchservice.rpc.product.dto.SearchProductPageReqDTO;
import cn.iocoder.mall.searchservice.rpc.product.dto.SearchProductRespDTO;
import cn.iocoder.mall.searchservice.service.product.SearchProductService;
import cn.iocoder.mall.searchservice.service.product.bo.SearchProductBO;
import cn.iocoder.mall.searchservice.service.product.bo.SearchProductConditionBO;
import cn.iocoder.mall.searchservice.service.product.bo.SearchProductSaveBO;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@Slf4j
public class SearchProductManager {

    private static final Integer REBUILD_FETCH_PER_SIZE = 100;

    @DubboReference(version = "${dubbo.consumer.ProductSpuRpc.version}")
    private ProductSpuRpc productSpuRpc;
    @DubboReference(version = "${dubbo.consumer.ProductSkuRpc.version}")
    private ProductSkuRpc productSkuRpc;
    @DubboReference(version = "${dubbo.consumer.ProductCategoryRpc.version}")
    private ProductCategoryRpc productCategoryRpc;

//    @DubboReference( version = "${dubbo.consumer.CartService.version}")
//    private CartService cartService;

    @Autowired
    private SearchProductService searchProductService;

    public PageResult<SearchProductRespDTO> pageSearchProduct(SearchProductPageReqDTO pageReqDTO) {
        PageResult<SearchProductBO> pageResult = searchProductService.pageSearchProduct(SearchProductConvert.INSTANCE.convert(pageReqDTO));
        return SearchProductConvert.INSTANCE.convertPage(pageResult);
    }

    public SearchProductConditionRespDTO getSearchProductCondition(SearchProductConditionReqDTO conditionReqDTO) {
        SearchProductConditionBO conditionBO =
                searchProductService.getSearchProductCondition(conditionReqDTO.getKeyword(), conditionReqDTO.getFields());
        return SearchProductConvert.INSTANCE.convert(conditionBO);
    }

    /**
     * 重建所有商品的 ES 索引
     *
     * @return 重建数量
     */
    public Integer rebuild() {
        // TODO 芋艿，因为目前商品比较少，所以写的很粗暴。等未来重构
        Integer lastId = null;
        int rebuildCounts = 0;
        while (true) {
            // 从商品服务，增量获取商品列表编号
            CommonResult<List<Integer>> listProductSpuIdsResult = productSpuRpc.listProductSpuIds(lastId, REBUILD_FETCH_PER_SIZE);
            listProductSpuIdsResult.checkError();
            List<Integer> spuIds = listProductSpuIdsResult.getData();
            // 逐个重建索引到 ES 中
            spuIds.forEach(this::saveProduct);
            // 设置新的 lastId ，或者结束
            rebuildCounts += listProductSpuIdsResult.getData().size();
            if (spuIds.size() < REBUILD_FETCH_PER_SIZE) {
                break;
            } else {
                lastId = spuIds.get(spuIds.size() - 1);
            }
        }
        // 返回成功
        return rebuildCounts;
    }

    /**
     * 重建指定商品的 ES 索引
     *
     * @param id 商品 SPU 编号
     * @return 是否重建成功
     */
    public Boolean saveProduct(Integer id) {
        // 获得商品 SPU
        CommonResult<ProductSpuRespDTO> productSpuResult = productSpuRpc.getProductSpu(id);
        productSpuResult.checkError();
        if (productSpuResult.getData() == null) {
            log.error("[saveProduct][商品 SPU({}) 不存在]", id);
            return false;
        }
        // 获得商品 SKU
        CommonResult<List<ProductSkuRespDTO>> listProductSkusResult =
                productSkuRpc.listProductSkus(new ProductSkuListQueryReqDTO().setProductSpuId(id));
        listProductSkusResult.checkError();
        if (CollectionUtils.isEmpty(listProductSkusResult.getData())) {
            log.error("[saveProduct][商品 SPU({}) 的 SKU 不存在]", id);
            return false;
        }
        // 获得商品分类
        CommonResult<ProductCategoryRespDTO> getProductCategoryResult =
                productCategoryRpc.getProductCategory(productSpuResult.getData().getCid());
        getProductCategoryResult.checkError();
        if (getProductCategoryResult.getData() == null) {
            log.error("[saveProduct][商品 SPU({}) 的分类({}) 不存在]", id, productSpuResult.getData().getCid());
            return false;
        }
        // 保存商品到 ES 中
        SearchProductSaveBO searchProductCreateBO = SearchProductConvert.INSTANCE.convert(
                productSpuResult.getData(), getProductCategoryResult.getData());
        ProductSkuRespDTO productSku = listProductSkusResult.getData().stream()
                .min(Comparator.comparing(ProductSkuRespDTO::getPrice)).orElse(null);
        assert productSku != null;
//        // 价格计算 TODO 芋艿：需要补充，暂时使用这个逻辑
//        CalcSkuPriceBO calSkuPriceResult  = cartService.calcSkuPrice(sku.getId());
        searchProductCreateBO.setOriginalPrice(productSku.getPrice());
        searchProductCreateBO.setBuyPrice(productSku.getPrice());
        searchProductCreateBO.setQuantity(productSku.getQuantity());
        searchProductService.saveSearchProduct(searchProductCreateBO);
        return true;
    }

}
