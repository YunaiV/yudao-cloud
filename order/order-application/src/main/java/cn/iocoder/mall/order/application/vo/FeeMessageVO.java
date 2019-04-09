package cn.iocoder.mall.order.application.vo;

public class FeeMessageVO {

    /**
     * 总价
     */
    private Integer originalTotal;
    /**
     * 优惠总价
     *
     * 注意，满多少元包邮，不算在优惠中。
     */
    private Integer discountTotal;
    /**
     * 邮费
     */
    private Integer postageTotal;
    /**
     * 最终价格
     *
     * 计算公式 = 总价 - 优惠总价 + 邮费
     */
    private Integer presentTotal;

}
