package cn.iocoder.mall.tradeservice.enums.aftersale;

import lombok.Getter;

/**
 * 售后订单的状态枚举
 *
 * 整体流程，见 https://www.processon.com/view/link/5fbdf47f07912946156305d7
 *
 * 1. 在退款的情况下，需要 1 个来回
 * 2. 在退货退款的情况下，需要 2 个来回，额外增加一个买家退货的过程
 * 3. 在换货的情况下，需要 3 个来回，额外增加一个买家退货 + 卖家发货的过程
 */
@Getter
public enum AfterSaleOrderStatusEnum {

    WAIT_SELLER_AGREE(10, "售后申请待商家处理"),
    WAIT_BUYER_RETURN_GOODS(20, "商家同意售后申请，待买家处理"),
    SELLER_REFUSE_BUYER(30, "商家不同意售后申请，待买家处理"),
    WAIT_SELLER_CONFIRM_GOODS(40, "买家已退货，待商家确认收货"),
    WAIT_BUYER_CONFIRM_GOODS(50, "商家已发货，待买家确认收货"),
    SELLER_REFUSE_RETURN_GOODS(60, "商家拒绝收货，待买家处理"),
    SUCCESS(70, "售后成功"),
    CLOSED(80, "售后失败"),
    ;

    /**
     * 类型
     */
    private final Integer status;
    /**
     * 描述
     */
    private final String desc;

    AfterSaleOrderStatusEnum(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }

}
