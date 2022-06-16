package cn.iocoder.mall.searchservice.rpc.product.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 商品搜索条件 Response DTO
 */
@Data
@Accessors(chain = true)
public class SearchProductConditionRespDTO implements Serializable {

    /**
     * 商品分类数组
     */
    private List<Integer> cids;

}
