package cn.iocoder.mall.promotion.api.rpc.activity.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 促销活动分页 BO
 */
@Data
@Accessors(chain = true)
public class PromotionActivityPageReqDTO {

    /**
     * PromotionActivityBO 数组
     */
    private List<PromotionActivityRespDTO> list;
    /**
     * 总量
     */
    private Integer total;

}
