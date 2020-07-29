package cn.iocoder.mall.promotionservice.service.recommend.bo;

import cn.iocoder.common.framework.validator.InEnum;
import cn.iocoder.mall.promotion.api.enums.ProductRecommendTypeEnum;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 商品推荐更新 DTO
 */
@Data
@Accessors(chain = true)
public class ProductRecommendUpdateBO implements Serializable {

    @NotNull(message = "编号不能为空")
    private Integer id;
    @NotNull(message = "类型不能为空")
    @InEnum(value = ProductRecommendTypeEnum.class, message = "修改推荐类型必须是 {value}")
    private Integer type;
    @NotNull(message = "商品编号不能为空")
    private Integer productSpuId;
    @NotNull(message = "排序不能为空")
    private Integer sort;
    @Length(max = 255, message = "备注最大长度为 255 位")
    private String memo;

}
