package cn.iocoder.mall.promotionservice.service.activity.bo;

import cn.iocoder.mall.promotion.api.rpc.activity.dto.PromotionActivityRespDTO;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 促销活动分页 BO
 */
@Data
@Accessors(chain = true)
public class PromotionActivityPageBO {

    /**
     * PromotionActivityBO 数组
     */
    private List<PromotionActivityRespDTO> list;
    /**
     * 总量
     */
    private Integer total;

}
