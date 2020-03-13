package cn.iocoder.mall.demo.business.bo.product;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Demo 商品库存减少 BO
 */
@Data
@Accessors(chain = true)
public class DemoProductQuantityReduceBO {

    /**
     * 商品编号
     */
    @NotNull(message = "商品编号不能为空")
    private Integer id;

    /**
     * 减少数量
     */
    @NotNull(message = "减少数量不能为空")
    @Min(value = 1, message = "减少数量最小为 1")
    private Integer quantity;

}
