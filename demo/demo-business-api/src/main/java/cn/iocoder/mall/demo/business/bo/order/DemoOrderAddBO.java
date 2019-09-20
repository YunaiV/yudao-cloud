package cn.iocoder.mall.demo.business.bo.order;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * Demo 订单添加 BO
 */
@Data
@Accessors(chain = true)
public class DemoOrderAddBO {

    @NotNull(message = "用户编号不能为空")
    private Integer userId;

    @NotNull(message = "用户编号不能为空")
    private Integer productId;

}
