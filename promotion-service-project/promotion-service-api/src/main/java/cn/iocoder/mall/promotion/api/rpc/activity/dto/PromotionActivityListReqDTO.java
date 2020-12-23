package cn.iocoder.mall.promotion.api.rpc.activity.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Collection;

/**
 * 促销活动列表查询的 Request DTO
 */
@Data
@Accessors(chain = true)
public class PromotionActivityListReqDTO implements Serializable {

    /**
     * 活动编号数组
     */
    private Collection<Integer> activeIds;

}
