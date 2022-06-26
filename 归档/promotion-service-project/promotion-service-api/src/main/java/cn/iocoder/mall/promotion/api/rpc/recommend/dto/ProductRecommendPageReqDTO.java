package cn.iocoder.mall.promotion.api.rpc.recommend.dto;

import cn.iocoder.common.framework.validator.InEnum;
import cn.iocoder.common.framework.vo.PageParam;
import cn.iocoder.mall.promotion.api.enums.recommend.ProductRecommendTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 商品推荐分页 Request DTO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ProductRecommendPageReqDTO extends PageParam {

    /**
     * 推荐类型
     */
    @InEnum(value = ProductRecommendTypeEnum.class, message = "推荐类型必须是 {value}")
    private Integer type;

}
