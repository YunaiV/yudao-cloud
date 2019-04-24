package cn.iocoder.mall.search.biz.service;

import cn.iocoder.common.framework.util.CollectionUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.SortingField;
import cn.iocoder.mall.order.api.CartService;
import cn.iocoder.mall.order.api.bo.CalcSkuPriceBO;
import cn.iocoder.mall.product.api.ProductSpuService;
import cn.iocoder.mall.product.api.bo.ProductSpuDetailBO;
import cn.iocoder.mall.search.api.ProductSearchService;
import cn.iocoder.mall.search.api.bo.ESProductPageBO;
import cn.iocoder.mall.search.api.dto.ProductSearchPageDTO;
import cn.iocoder.mall.search.biz.convert.ProductSearchConvert;
import cn.iocoder.mall.search.biz.dao.ProductRepository;
import cn.iocoder.mall.search.biz.dataobject.ESProductDO;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@com.alibaba.dubbo.config.annotation.Service(validation = "true")
public class ProductSearchServiceImpl implements ProductSearchService {

    private static final Integer REBUILD_FETCH_PER_SIZE = 100;

    @Autowired
    private ProductRepository productRepository;

    @Reference(validation = "true")
    private ProductSpuService productSpuService;
    @Reference(validation = "true")
    private CartService cartService;

    @Override
    public CommonResult<Integer> rebuild() {
        // TODO 芋艿，因为目前商品比较少，所以写的很粗暴。等未来重构
        Integer lastId = null;
        int rebuildCounts = 0;
        while (true) {
            CommonResult<List<ProductSpuDetailBO>> result = productSpuService.getProductSpuDetailListForSync(lastId, REBUILD_FETCH_PER_SIZE);
            Assert.isTrue(result.isSuccess(), "获得商品列表必然成功");
            List<ProductSpuDetailBO> spus = result.getData();
            rebuildCounts += spus.size();
            // 存储到 ES 中
            List<ESProductDO> products = spus.stream().map(this::convert).collect(Collectors.toList());
            productRepository.saveAll(products);
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
        Assert.isTrue(calSkuPriceResult.isSuccess(), String.format("SKU(%d) 价格计算不会出错", sku.getId()));
        // 拼装结果
        return ProductSearchConvert.INSTANCE.convert(spu, calSkuPriceResult.getData());
    }

    @Override
    public CommonResult<ESProductPageBO> searchPage(ProductSearchPageDTO searchPageDTO) {
        checkSortFieldInvalid(searchPageDTO.getSorts());
        // 执行查询
        Page<ESProductDO> searchPage = productRepository.search(searchPageDTO.getCid(), searchPageDTO.getKeyword(),
                searchPageDTO.getPageNo(), searchPageDTO.getPageSize(), searchPageDTO.getSorts());
        // 转换结果
        ESProductPageBO resultPage = new ESProductPageBO()
                .setList(ProductSearchConvert.INSTANCE.convert(searchPage.getContent()))
                .setTotal((int) searchPage.getTotalElements());
        return CommonResult.success(resultPage);
    }

    private void checkSortFieldInvalid(List<SortingField> sorts) {
        if (CollectionUtil.isEmpty(sorts)) {
            return;
        }
        sorts.forEach(sortingField -> Assert.isTrue(ProductSearchPageDTO.SORT_FIELDS.contains(sortingField.getField()),
                String.format("排序字段(%s) 不在允许范围内", sortingField.getField())));
    }

}
