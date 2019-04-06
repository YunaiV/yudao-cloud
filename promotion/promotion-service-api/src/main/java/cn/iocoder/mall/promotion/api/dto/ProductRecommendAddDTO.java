package cn.iocoder.mall.promotion.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 商品推荐添加 DTO
 */
@Data
@Accessors(chain = true)
public class ProductRecommendAddDTO {

    @NotNull(message = "推荐类型不能为空")
    private Integer type;
    @NotNull(message = "商品编号不能为空")
    private Integer productSpuId;
    @NotNull(message = "排序不能为空")
    private Integer sort;
    @Length(max = 255, message = "备注最大长度为 255 位")
    private String memo;

}
