package cn.iocoder.mall.searchservice.service.product.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 商品搜索条件返回 BO
 */
@Data
@Accessors(chain = true)
public class SearchProductConditionBO {

    /**
     * 商品分类数组
     */
    private List<Integer> cids;

}
