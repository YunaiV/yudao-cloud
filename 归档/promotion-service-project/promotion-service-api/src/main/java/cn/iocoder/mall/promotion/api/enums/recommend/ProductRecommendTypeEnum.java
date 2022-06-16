package cn.iocoder.mall.promotion.api.enums.recommend;

import cn.iocoder.common.framework.core.IntArrayValuable;

import java.util.Arrays;

/**
 * 商品推荐类型
 */
public enum ProductRecommendTypeEnum implements IntArrayValuable {

    HOT(1, "热卖推荐"),
    NEW(2, "新品推荐"),
    ;

    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(ProductRecommendTypeEnum::getValue).toArray();

    /**
     * 状态值
     */
    private final Integer value;
    /**
     * 状态名
     */
    private final String name;

    ProductRecommendTypeEnum(Integer value, String name) {
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

    @Override
    public int[] array() {
        return ARRAYS;
    }

}
