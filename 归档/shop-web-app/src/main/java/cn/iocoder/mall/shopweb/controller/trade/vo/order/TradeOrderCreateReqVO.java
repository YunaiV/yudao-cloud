package cn.iocoder.mall.shopweb.controller.trade.vo.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@ApiModel(value = "创建交易订单 VO，基于商品")
@Data
@Accessors(chain = true)
public class TradeOrderCreateReqVO {

    @ApiModelProperty(name = "收件地址编号", required = true, example = "1")
    @NotNull(message = "收件地址不能为空")
    private Integer userAddressId;
    @ApiModelProperty(name = "优惠劵编号", example = "1024")
    private Integer couponCardId;
    @ApiModelProperty(name = "备注", example = "1024")
    private String remark;

    /**
     * 订单商品项列表
     */
    @NotNull(message = "必须选择购买的商品")
    private List<OrderItem> orderItems;

    @ApiModel(value = "订单商品项")
    @Data
    @Accessors(chain = true)
    public static class OrderItem {

        @ApiModelProperty(name = "商品 SKU 编号", required = true, example = "111")
        @NotNull(message = "商品 SKU 编号不能为空")
        private Integer skuId;
        @ApiModelProperty(name = "商品 SKU 购买数量", required = true, example = "1024")
        @NotNull(message = "商品 SKU 购买数量不能为空")
        @Min(value = 1, message = "商品 SKU 购买数量必须大于 0")
        private Integer quantity;
    }

}
