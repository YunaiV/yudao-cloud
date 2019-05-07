package cn.iocoder.mall.promotion.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;

/**
 * 商品推荐分页 DTO
 */
@Data
@Accessors(chain = true)
public class PromotionActivityPageDTO implements Serializable {

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

    @NotNull(message = "页码不能为空")
    private Integer pageNo;
    @NotNull(message = "每页条数不能为空")
    private Integer pageSize;

}
