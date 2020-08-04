package cn.iocoder.mall.searchservice.rpc.product.dto;

import cn.iocoder.common.framework.vo.PageParam;
import cn.iocoder.common.framework.vo.SortingField;
import cn.iocoder.mall.searchservice.enums.product.SearchProductPageQuerySortFieldEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 商品检索分页查询 Request DTO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SearchProductPageReqDTO extends PageParam {

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
