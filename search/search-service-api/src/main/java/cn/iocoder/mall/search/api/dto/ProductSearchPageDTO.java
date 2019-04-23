package cn.iocoder.mall.search.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 商品检索分页 DTO
 */
@Data
@Accessors(chain = true)
public class ProductSearchPageDTO {

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
    private List<SortFieldDTO> sorts;

}
