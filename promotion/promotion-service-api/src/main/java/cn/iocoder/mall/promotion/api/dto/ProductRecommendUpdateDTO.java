package cn.iocoder.mall.promotion.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 商品推荐更新 DTO
 */
@Data
@Accessors(chain = true)
public class ProductRecommendUpdateDTO {

    @NotNull(message = "编号不能为空")
    private Integer id;
    @NotNull(message = "类型不能为空")
    private Integer type;
    @NotNull(message = "商品编号不能为空")
    private Integer productSpuId;
    @NotNull(message = "排序不能为空")
    private Integer sort;
    @Length(max = 255, message = "备注最大长度为 255 位")
    private String memo;

}
