package cn.iocoder.mall.promotion.biz.dataobject;

import cn.iocoder.common.framework.dataobject.BaseDO;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 促销活动 DO
 */
@Data
@Accessors(chain = true)
public class PromotionActivityDO extends BaseDO {

    /**
     * 活动编号
     */
    private Integer id;
    /**
     * 活动标题
     */
    private String title;
    /**
     * 活动类型
     */
    private Integer type;

}
