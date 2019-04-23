package cn.iocoder.mall.search.biz.service;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.order.api.CartService;
import cn.iocoder.mall.order.api.bo.CalcSkuPriceBO;
import cn.iocoder.mall.product.api.ProductSpuService;
import cn.iocoder.mall.product.api.bo.ProductSpuDetailBO;
import cn.iocoder.mall.search.api.ProductSearchService;
import cn.iocoder.mall.search.api.dto.ProductSearchPageDTO;
import cn.iocoder.mall.search.biz.dao.ProductRepository;
import cn.iocoder.mall.search.biz.dataobject.ESProductDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@com.alibaba.dubbo.config.annotation.Service(validation = "true")
public class ProductSearchServiceImpl implements ProductSearchService {

    private static final Integer REBUILD_FETCH_PER_SIZE = 2;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductSpuService productSpuService;
    @Autowired
    private CartService cartService;

    @Override
    public CommonResult<Integer> rebuild() {
        // TODO 芋艿，因为目前商品比较少，所以写的很粗暴。等未来重构
        Integer lastId = null;
        int rebuildCounts = 0;
        while (true) {
            CommonResult<List<ProductSpuDetailBO>> result = productSpuService.getProductSpuDetailListForSync(lastId, REBUILD_FETCH_PER_SIZE);
            Assert.isTrue(result.isError(), "获得商品列表必然成功");
            List<ProductSpuDetailBO> spus = result.getData();
            rebuildCounts += spus.size();
            // 存储到 ES 中
            List<ESProductDO> products = spus.stream().map(new Function<ProductSpuDetailBO, ESProductDO>() {
                @Override
                public ESProductDO apply(ProductSpuDetailBO spu) {
                    return convert(spu);
                }
            }).collect(Collectors.toList());

            // 设置新的 lastId ，或者结束
            if (spus.size() < REBUILD_FETCH_PER_SIZE) {
                break;
            } else {
                lastId = spus.get(spus.size() - 1).getId();
            }
        }
        return CommonResult.success(rebuildCounts);
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    private ESProductDO convert(ProductSpuDetailBO spu) {
        // 获得最小价格的 SKU ，用于下面的价格计算
        ProductSpuDetailBO.Sku sku = spu.getSkus().stream().min(Comparator.comparing(ProductSpuDetailBO.Sku::getPrice)).get();
        // 价格计算
        CommonResult<CalcSkuPriceBO> calSkuPriceResult  = cartService.calcSkuPrice(sku.getId());
        Assert.isTrue(calSkuPriceResult.isError(), String.format("SKU(%d) 价格计算不会出错", sku.getId()));

        return new ESProductDO();
    }

    @Override
    public CommonResult searchPage(ProductSearchPageDTO searchPageDTO) {
        return null;
    }

}
