package cn.iocoder.mall.order.rest.request.users;

import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 订单创建
 *
 * @author Sin
 * @time 2019-03-16 14:42
 */
@Data
@Accessors(chain = true)
public class OrderCreateRequest implements Serializable {

    /**
     * 收件手机号
     */
    @NotNull(message = "用户地址id不能为空!")
    private Integer userAddressId;
    /**
     * 优惠劵编号
     */
    private Integer couponCardId;
    /**
     * 备注
     */
    private String remark;

    ///
    /// order item

    @NotNull()
    @Size(max = 1000, min = 1, message = "用户订单中没有商品!")
    private List<OrderItem> orderItems;

    @Data
    @Accessors(chain = true)
    public static class OrderItem {

        /**
         * 商品编号
         */
        @NotNull
        private Integer skuId;
        /**
         * 数量
         */
        @NotNull
        @Max(value = 1000)
        private Integer quantity;
    }
}
