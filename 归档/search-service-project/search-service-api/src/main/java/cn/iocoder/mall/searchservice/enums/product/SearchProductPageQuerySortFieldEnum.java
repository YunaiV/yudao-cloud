package cn.iocoder.mall.searchservice.enums.product;

/**
 * 搜索商品分页查询的排序字段枚举
 */
public enum SearchProductPageQuerySortFieldEnum {

    /**
     * 购买价格
     */
    BUY_PRICE("buyPrice");

    /**
     * 字段
     */
    private final String field;

    SearchProductPageQuerySortFieldEnum(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }

    public static boolean contains(String field) {
        for (SearchProductPageQuerySortFieldEnum fieldEnum : values()) {
            if (field.equals(fieldEnum.getField())) {
                return true;
            }
        }
        return false;
    }

}
