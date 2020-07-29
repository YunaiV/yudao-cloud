package cn.iocoder.mall.promotionservice.service.recommend.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 商品推荐分页 DTO
 */
@Data
@Accessors(chain = true)
public class ProductRecommendPageBO implements Serializable {

    /**
     * 推荐类型
     */
    private Integer type;

    @NotNull(message = "页码不能为空")
    private Integer pageNo;
    @NotNull(message = "每页条数不能为空")
    private Integer pageSize;

}
