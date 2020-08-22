package cn.iocoder.mall.promotion.api.rpc.activity.dto;

import cn.iocoder.common.framework.vo.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Collection;

/**
 * 促销活动分页 Request DTO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class PromotionActivityPageReqDTO extends PageParam {

    /**
     * 标题
     *
     * 模糊匹配
     */
    private String title;
    /**
     * 活动类型
     */
    private Integer activityType;
    /**
     * 状态
     */
    private Collection<Integer> statuses;

}
