package cn.iocoder.mall.order.application.po.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 订单发货
 *
 * @author Sin
 * @time 2019-04-05 16:55
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "订单发货PO")
public class OrderDeliverPO implements Serializable {

    /**
     * 订单编号
     */
    @ApiModelProperty("订单id")
    @NotNull(message = "orderId不能为空")
    private Integer orderId;
    /**
     * 物流 (字典)
     */
    @ApiModelProperty("物流商家")
    @NotNull(message = "必须选择商家")
    private Integer logistics;
    /**
     * 物流编号
     */
    @ApiModelProperty("物流单号")
    @NotNull(message = "没有物流单号")
    private String logisticsNo;
    /**
     * 订单 items
     */
    @ApiModelProperty("订单items")
    @NotNull(message = "没有选择发货的商品")
    private List<Integer> orderItemIds;
}
