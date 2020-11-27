package cn.iocoder.mall.tradeservice.enums.order;

import cn.iocoder.mall.tradeservice.rpc.order.dto.TradeOrderRespDTO;

/**
 * 交易订单的明细的字段枚举
 *
 * @see TradeOrderRespDTO
 */
public enum TradeOrderDetailFieldEnum {

    /**
     * 交易订单项
     */
    ITEM("item");

    /**
     * 字段
     */
    private final String field;

    TradeOrderDetailFieldEnum(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }

}
