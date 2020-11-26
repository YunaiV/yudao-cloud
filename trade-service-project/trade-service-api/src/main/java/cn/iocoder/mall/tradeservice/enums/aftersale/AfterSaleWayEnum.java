package cn.iocoder.mall.tradeservice.enums.aftersale;

import lombok.Getter;

/**
 * 售后单的方式枚举
 */
@Getter
public enum AfterSaleWayEnum {

    REFUND(10, "退款"),
    RETURN_AND_REFUND(20, "退货退款"),
    EXCHANGE(30, "换货");

    /**
     * 方式
     */
    private final Integer way;
    /**
     * 描述
     */
    private final String desc;

    AfterSaleWayEnum(Integer way, String desc) {
        this.way = way;
        this.desc = desc;
    }

}
