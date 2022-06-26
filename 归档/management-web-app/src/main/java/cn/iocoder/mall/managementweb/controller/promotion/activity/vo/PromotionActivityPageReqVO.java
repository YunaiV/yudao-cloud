package cn.iocoder.mall.managementweb.controller.promotion.activity.vo;

import cn.iocoder.common.framework.vo.PageParam;
import io.swagger.annotations.ApiModelProperty;
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
public class PromotionActivityPageReqVO extends PageParam {

    @ApiModelProperty(value = "标题", example = "优惠劵牛逼")
    private String title;
    @ApiModelProperty(value = "活动类型", example = "1", notes = "参见 PromotionActivityTypeEnum 枚举")
    private Integer activityType;
    @ApiModelProperty(value = "状态数组", example = "1,2", notes = "参考 PromotionActivityStatusEnum 枚举")
    private Collection<Integer> statuses;

}
