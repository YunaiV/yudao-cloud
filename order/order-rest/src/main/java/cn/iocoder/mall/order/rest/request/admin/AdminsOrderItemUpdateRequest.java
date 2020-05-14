package cn.iocoder.mall.order.rest.request.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 订单 item 更新
 *
 * @author Sin
 * @time 2019-03-24 11:16
 */
@Data
@Accessors(chain = true)
@ApiModel("订单item更新")
public class AdminsOrderItemUpdateRequest implements Serializable {

    /**
     * 编号
     */
    @NotNull(message = "id不能为空!")
    @ApiModelProperty("编号(orderItemId)")
    private Integer id;
    /**
     * 商品编号
     */
    @NotNull(message = "商品id不能为空!")
    @ApiModelProperty("商品id")
    private Integer skuId;
    /**
     * 数量
     */
    @NotNull(message = "数量不能为空!")
    @Size(max = 9999, min = 1, message = "商品数量 1 ~ 9999")
    @ApiModelProperty("商品数量")
    private Integer quantity;
    /**
     * 金额(分)
     */
    @NotNull(message = "商品金额")
    @Size(max = 99999999, min = 0, message = "商品金额 0 ~ 99999999")
    @ApiModelProperty("商品金额")
    private Integer price;
}
