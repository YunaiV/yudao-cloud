package cn.iocoder.mall.promotion.api.rpc.coupon.dto.card;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 优惠劵可用信息列表 Request DTO
 */
@Data
@Accessors(chain = true)
public class CouponCardAvailableListReqDTO implements Serializable {

    /**
     * 用户编号
     */
    @NotNull(message = "用户编号不能为空")
    private Integer userId;
    /**
     * 商品数组
     */
    @NotEmpty(message = "商品数组不能为空")
    private List<Item> items;

    /**
     * 商品
     */
    @Data
    @Accessors(chain = true)
    public static class Item implements Serializable {

        /**
         * 商品 SPU 编号
         */
        @NotNull(message = "商品 SPU 编号不能为空")
        private Integer spuId;
        /**
         * 商品 SKU 编号
         */
        @NotNull(message = "商品 SKU 编号不能为空")
        private Integer skuId;
        /**
         * 商品 SKU 编号
         */
        @NotNull(message = "商品 Category 编号不能为空")
        private Integer cid;
        /**
         * 商品数量
         */
        @NotNull(message = "商品数量不能为空")
        @Min(value = 1L, message = "最小商品数量 1")
        private Integer quantity;
        /**
         * 商品价格，单位：分
         *
         * 为什么需要传递价格？因为商品的价格是经过计算，部署商品原始价格
         */
        @NotNull(message = "商品价格不能为空")
        private Integer price;

    }

}
