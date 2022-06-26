package cn.iocoder.mall.searchservice.enums.product;

/**
 * 搜索商品条件的字段枚举
 */
public enum  SearchProductConditionFieldEnum {

    CATEGORY("category");

    /**
     * 字段
     */
    private final String field;

    SearchProductConditionFieldEnum(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }

}
