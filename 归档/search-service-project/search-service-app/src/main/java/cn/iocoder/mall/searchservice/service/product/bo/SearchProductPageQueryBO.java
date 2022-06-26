package cn.iocoder.mall.searchservice.service.product.bo;

import cn.iocoder.common.framework.vo.PageParam;
import cn.iocoder.common.framework.vo.SortingField;
import cn.iocoder.mall.searchservice.enums.product.SearchProductPageQuerySortFieldEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 商品检索分查询 BO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SearchProductPageQueryBO extends PageParam {

    /**
     * 分类编号
     */
    private Integer cid;
    /**
     * 关键字
     */
    private String keyword;
    /**
     * 排序字段数组
     *
     * 可支持排序的字段，见 {@link SearchProductPageQuerySortFieldEnum}
     */
    private List<SortingField> sorts;

}
