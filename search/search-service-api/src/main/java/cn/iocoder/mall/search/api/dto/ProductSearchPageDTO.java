package cn.iocoder.mall.search.api.dto;

import cn.iocoder.common.framework.util.CollectionUtil;
import cn.iocoder.common.framework.vo.SortingField;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Set;

/**
 * 商品检索分页 DTO
 */
@Data
@Accessors(chain = true)
public class ProductSearchPageDTO {

    public static final Set<String> SORT_FIELDS = CollectionUtil.asSet("buyPrice");

    /**
     * 分类编号
     */
    private Integer cid;
    /**
     * 关键字
     */
    private String keyword;

    /**
     * 页码
     */
    private Integer pageNo;
    /**
     * 分页大小
     */
    private Integer pageSize;

    /**
     * 排序字段数组
     */
    private List<SortingField> sorts;

}
