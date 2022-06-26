package cn.iocoder.mall.tradeservice.enums.logistics;

import lombok.Getter;

/**
 * 物流的配送类型
 */
@Getter
public enum LogisticsDeliveryTypeEnum {

    /**
     * 无需快递
     */
    NULL(0),
    /**
     * 传统快递
     */
    EXPRESS(1);

    private final Integer deliveryType;

    LogisticsDeliveryTypeEnum(Integer deliveryType) {
        this.deliveryType = deliveryType;
    }

}
