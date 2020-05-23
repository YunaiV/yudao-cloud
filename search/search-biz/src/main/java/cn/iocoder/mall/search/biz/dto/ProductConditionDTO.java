package cn.iocoder.mall.search.biz.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Collection;

/**
 * 获得商品检索条件 DTO
 */
@Data
@Accessors(chain = true)
public class ProductConditionDTO {

    /**
     * Field - 商品分类
     */
    public static final String FIELD_CATEGORY = "category";

    /**
     * 关键字
     */
    private String keyword;
    /**
     * 需要返回的搜索条件的 fields 名
     */
    private Collection<String> fields;

}
