package cn.iocoder.mall.tradeservice.enums.aftersale;

import lombok.Getter;

/**
 * 售后单的类型枚举
 */
@Getter
public enum AfterSaleTypeEnum {

    IN_SALE(10, "售中退款"),
    AFTER_SALE(20, "售后退款");

    /**
     * 类型
     */
    private final Integer type;
    /**
     * 描述
     */
    private final String desc;

    AfterSaleTypeEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

}
