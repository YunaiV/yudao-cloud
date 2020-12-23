package cn.iocoder.mall.tradeservice.enums.order;

import lombok.Getter;

/**
 * 交易订单的售后状态的枚举
 */
@Getter
public enum TradeOrderAfterSaleStatusEnum {

    NULL(0, "无"),
    IN_PROCESS(10, "售后中"),
    END(10, "售后结束");

    /**
     * 状态
     */
    private final Integer status;
    /**
     * 描述
     */
    private final String desc;

    TradeOrderAfterSaleStatusEnum(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }

}
