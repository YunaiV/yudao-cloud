package cn.iocoder.mall.searchservice.rpc.product.dto;

import cn.iocoder.mall.searchservice.enums.product.SearchProductConditionFieldEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Collection;

/**
 * 商品搜索条件 Request DTO
 */
@Data
@Accessors(chain = true)
public class SearchProductConditionReqDTO implements Serializable {

    /**
     * 关键字
     */
    private String keyword;
    /**
     * 需要返回的搜索条件
     *
     * 可选择的条件，见 {@link SearchProductConditionFieldEnum} 枚举类
     */
    private Collection<String> fields;

}
