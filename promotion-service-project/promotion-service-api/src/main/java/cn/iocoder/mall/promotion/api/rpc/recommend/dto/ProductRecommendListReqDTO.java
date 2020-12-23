package cn.iocoder.mall.promotion.api.rpc.recommend.dto;

import cn.iocoder.common.framework.enums.CommonStatusEnum;
import cn.iocoder.common.framework.validator.InEnum;
import cn.iocoder.mall.promotion.api.enums.recommend.ProductRecommendTypeEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 商品推荐列表 Req DTO
 */
@Data
@Accessors(chain = true)
public class ProductRecommendListReqDTO implements Serializable {

    /**
     * 类型
     */
    @InEnum(value = ProductRecommendTypeEnum.class, message = "推荐类型必须是 {value}")
    private Integer type;
    /**
     * 状态
     */
    @InEnum(value = CommonStatusEnum.class, message = "修改状态必须是 {value}")
    private Integer status;

}
