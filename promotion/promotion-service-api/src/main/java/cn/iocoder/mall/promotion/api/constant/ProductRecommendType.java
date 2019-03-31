package cn.iocoder.mall.promotion.api.constant;

/**
 * 商品推荐类型
 */
public enum ProductRecommendType {

    HOT(1, "热卖推荐"),
    NEW(2, "新品推荐"),

    ;

    /**
     * 状态值
     */
    private final Integer value;
    /**
     * 状态名
     */
    private final String name;

    ProductRecommendType(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static boolean isValid(Integer status) {
        if (status == null) {
            return false;
        }
        return HOT.value.equals(status)
                || NEW.value.equals(status);
    }

}