package cn.iocoder.mall.search.biz.api;

import cn.iocoder.common.framework.util.CollectionUtil;
import cn.iocoder.common.framework.util.StringUtil;
import cn.iocoder.common.framework.vo.SortingField;
import cn.iocoder.mall.order.api.CartService;
import cn.iocoder.mall.order.api.bo.CalcSkuPriceBO;
import cn.iocoder.mall.product.api.ProductCategoryService;
import cn.iocoder.mall.product.api.ProductSpuService;
import cn.iocoder.mall.product.api.bo.ProductCategoryBO;
import cn.iocoder.mall.product.api.bo.ProductSpuDetailBO;
import cn.iocoder.mall.search.biz.ProductSearchService;
import cn.iocoder.mall.search.biz.bo.ProductConditionBO;
import cn.iocoder.mall.search.biz.bo.ProductPageBO;
import cn.iocoder.mall.search.biz.dto.ProductConditionDTO;
import cn.iocoder.mall.search.biz.dto.ProductSearchPageDTO;
import cn.iocoder.mall.search.biz.convert.ProductSearchConvert;
import cn.iocoder.mall.search.biz.dao.ProductRepository;
import cn.iocoder.mall.search.biz.dataobject.ESProductDO;
import org.apache.dubbo.config.annotation.Reference;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.LongTerms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@org.apache.dubbo.config.annotation.Service(validation = "true", version = "${dubbo.provider.ProductSearchService.version}")
public class ProductSearchServiceImpl implements ProductSearchService {

    private static final Integer REBUILD_FETCH_PER_SIZE = 100;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate; // 因为需要使用到聚合操作，只好引入 ElasticsearchTemplate 。

    @Override
    public Integer rebuild() {
        // TODO 芋艿，因为目前商品比较少，所以写的很粗暴。等未来重构
        Integer lastId = null;
        int rebuildCounts = 0;
        while (true) {
            List<ProductSpuDetailBO> spus = productSpuService.getProductSpuDetailListForSync(lastId, REBUILD_FETCH_PER_SIZE);
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
        // 返回成功
        return rebuildCounts;
    }

    @Override
    public ProductConditionBO getSearchCondition(ProductConditionDTO conditionDTO) {
        // 创建 ES 搜索条件
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        // 筛选
        if (StringUtil.hasText(conditionDTO.getKeyword())) { // 如果有 keyword ，就去匹配
            nativeSearchQueryBuilder.withQuery(QueryBuilders.multiMatchQuery(conditionDTO.getKeyword(),
                    "name", "sellPoint", "categoryName"));
        } else {
            nativeSearchQueryBuilder.withQuery(QueryBuilders.matchAllQuery());
        }
        // 聚合
        if (conditionDTO.getFields().contains(ProductConditionDTO.FIELD_CATEGORY)) { // 商品分类
            nativeSearchQueryBuilder.addAggregation(AggregationBuilders.terms("cids").field("cid"));
        }
        // 执行查询
        ProductConditionBO condition = elasticsearchTemplate.query(nativeSearchQueryBuilder.build(), response -> {
            ProductConditionBO result = new ProductConditionBO();
            // categoryIds 聚合
            Aggregation categoryIdsAggregation = response.getAggregations().get("cids");
            if (categoryIdsAggregation != null) {
                result.setCategories(new ArrayList<>());
                for (LongTerms.Bucket bucket  : (((LongTerms) categoryIdsAggregation).getBuckets())) {
                    result.getCategories().add(new ProductConditionBO.Category().setId(bucket.getKeyAsNumber().intValue()));
                }
            }
            // 返回结果
            return result;
        });
        // 聚合其它数据源
        if (!CollectionUtil.isEmpty(condition.getCategories())) {
            // 查询指定的 ProductCategoryBO 数组，并转换成 ProductCategoryBO Map
            Map<Integer, ProductCategoryBO> categoryMap = productCategoryService.getListByIds(
                    condition.getCategories().stream().map(ProductConditionBO.Category::getId).collect(Collectors.toList()))
                    .stream().collect(Collectors.toMap(ProductCategoryBO::getId, category -> category));
            // 设置分类名
            condition.getCategories().forEach(category -> category.setName(categoryMap.get(category.getId()).getName()));
        }
        // 返回结果
        return condition;
    }

}
