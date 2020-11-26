package cn.iocoder.mall.tradeservice.rpc.order.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 交易订单创建 Request DTO
 *
 * @author Sin
 * @time 2019-03-16 14:42
 */
@Data
@Accessors(chain = true)
public class TradeOrderCreateReqDTO implements Serializable {

    /**
     * 用户编号
     */
    @NotNull(message = "用户编号不能为空")
    private Integer userId;
    /**
     * 用户 IP
     */
    @NotNull(message = "用户 IP 不能为空")
    private String ip;
    /**
     * 收件地址编号
     */
    @NotNull(message = "用户地址不能为空")
    private Integer userAddressId;
    /**
     * 优惠劵编号
     */
    private Integer couponCardId;
    /**
     * 备注
     */
    private String remark;

    /**
     * 订单商品项列表
     */
    @NotNull(message = "必须选择购买的商品")
    private List<OrderItem> orderItems;


    @Data
    @Accessors(chain = true)
    public static class OrderItem implements Serializable {

        /**
         * 商品编号
         */
        @NotNull(message = "商品 SKU 编号不能为空")
        private Integer skuId;
        /**
         * 数量
         */
        @NotNull(message = "商品 SKU 购买数量不能为空")
        @Min(value = 1, message = "商品 SKU 购买数量必须大于 0")
        private Integer quantity;
    }

}
