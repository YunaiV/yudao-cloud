package cn.iocoder.mall.tradeservice.enums.order;

import lombok.Getter;

/**
 * 交易订单 - 状态蜜桔
 *
 * @author Sin
 * @time 2019-03-16 14:06
 */
@Getter
public enum TradeOrderStatusEnum {

    WAITING_PAYMENT(10, "等待付款"),
    WAIT_SHIPMENT(20, "等待发货"),
    ALREADY_SHIPMENT(30, "已发货"),
    COMPLETED(40, "已完成"),
    CLOSED(50, "已关闭");

    /**
     * 状态值
     */
    private final Integer value;
    /**
     * 状态名
     */
    private final String name;

    TradeOrderStatusEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

}

