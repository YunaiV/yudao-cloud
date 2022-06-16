package cn.iocoder.mall.order.biz.dto.order;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 订单创建
 *
 * @author Sin
 * @time 2019-03-16 14:42
 */
@Data
@Accessors(chain = true)
// TODO FROM 芋艿 to xiaofeng：辛苦后续补充下 Validation 注解哈
public class OrderCreateDTO implements Serializable {

    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 用户地址
     */
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
     * ip信息
     */
    private String ip;

    ///
    /// order item

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
