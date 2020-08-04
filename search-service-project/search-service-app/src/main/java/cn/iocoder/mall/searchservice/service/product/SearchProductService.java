package cn.iocoder.mall.searchservice.service.product;

import cn.iocoder.common.framework.util.CollectionUtils;
import cn.iocoder.common.framework.util.StringUtils;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.common.framework.vo.SortingField;
import cn.iocoder.mall.searchservice.convert.product.SearchProductConvert;
import cn.iocoder.mall.searchservice.dal.es.dataobject.ESProductDO;
import cn.iocoder.mall.searchservice.dal.es.repository.ESProductRepository;
import cn.iocoder.mall.searchservice.enums.product.SearchProductConditionFieldEnum;
import cn.iocoder.mall.searchservice.enums.product.SearchProductPageQuerySortFieldEnum;
import cn.iocoder.mall.searchservice.service.product.bo.SearchProductBO;
import cn.iocoder.mall.searchservice.service.product.bo.SearchProductConditionBO;
import cn.iocoder.mall.searchservice.service.product.bo.SearchProductPageQueryBO;
import cn.iocoder.mall.searchservice.service.product.bo.SearchProductSaveBO;
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
import java.util.Collection;
import java.util.List;

@Service
public class SearchProductService {

    @Autowired
    private ESProductRepository productRepository;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate; // 因为需要使用到聚合操作，只好引入 ElasticsearchTemplate 。

    /**
     * 搜索商品分页结果
     *
     * @param pageQueryBO 分页查询条件
     * @return 商品信息
     */
    public PageResult<SearchProductBO> pageSearchProduct(SearchProductPageQueryBO pageQueryBO) {
        checkSortFieldInvalid(pageQueryBO.getSorts());
        // 执行查询
        Page<ESProductDO> searchPage = productRepository.search(pageQueryBO.getCid(), pageQueryBO.getKeyword(),
                pageQueryBO.getPageNo(), pageQueryBO.getPageSize(), pageQueryBO.getSorts());
        // 转换结果
        return SearchProductConvert.INSTANCE.convertPage(searchPage);
    }

    private void checkSortFieldInvalid(List<SortingField> sorts) {
        if (CollectionUtils.isEmpty(sorts)) {
            return;
        }
        sorts.forEach(sortingField -> Assert.isTrue(SearchProductPageQuerySortFieldEnum.contains(sortingField.getField()),
                String.format("排序字段(%s) 不在允许范围内", sortingField.getField())));
    }

    /**
     * 保存商品信息到 ES 中
     *
     * @param saveBO 商品信息
     */
    public void saveSearchProduct(SearchProductSaveBO saveBO) {
        ESProductDO productDO = SearchProductConvert.INSTANCE.convert(saveBO);
        productRepository.save(productDO);
    }

    /**
     * 获得指定关键字对应的搜索条件
     *
     * 在我们搜索商品时，需要获得关键字可选择的分类、品牌等等搜索条件，方便用户进一步检索
     *
     * @param keyword 关键字
     * @param fields 需要返回的搜索条件{@link SearchProductConditionFieldEnum}。目前可传入的参数为
     *               1. category ：商品分类，会返回商品分类编号
     * @return 搜索条件
     */
    public SearchProductConditionBO getSearchProductCondition(String keyword, Collection<String> fields) {
        // 创建 ES 搜索条件
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        // 筛选
        if (StringUtils.hasText(keyword)) { // 如果有 keyword ，就去匹配
            nativeSearchQueryBuilder.withQuery(QueryBuilders.multiMatchQuery(keyword,
                    "name", "sellPoint", "categoryName"));
        } else {
            nativeSearchQueryBuilder.withQuery(QueryBuilders.matchAllQuery());
        }
        // 聚合
        if (fields.contains("category")) { // 商品分类
            nativeSearchQueryBuilder.addAggregation(AggregationBuilders.terms("cids").field("cid"));
        }
        // 执行查询，返回结果
        return elasticsearchTemplate.query(nativeSearchQueryBuilder.build(), response -> {
            SearchProductConditionBO result = new SearchProductConditionBO();
            // categoryIds 聚合
            Aggregation categoryIdsAggregation = response.getAggregations().get("cids");
            if (categoryIdsAggregation != null) {
                result.setCids(new ArrayList<>());
                for (LongTerms.Bucket bucket  : (((LongTerms) categoryIdsAggregation).getBuckets())) {
                    result.getCids().add(bucket.getKeyAsNumber().intValue());
                }
            }
            // 返回结果
            return result;
        });
    }

}
