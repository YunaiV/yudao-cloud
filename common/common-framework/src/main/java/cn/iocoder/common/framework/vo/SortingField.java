package cn.iocoder.common.framework.vo;

/**
 * 排序字段 DTO
 *
 * 类名加了 ing 的原因是，避免和 ES SortField 重名。
 */
public class SortingField {

    /**
     * 字段
     */
    private String field;
    /**
     * 排序
     */
    private String order;

    public SortingField(String field, String order) {
        this.field = field;
        this.order = order;
    }

    public String getField() {
        return field;
    }

    public SortingField setField(String field) {
        this.field = field;
        return this;
    }

    public String getOrder() {
        return order;
    }

    public SortingField setOrder(String order) {
        this.order = order;
        return this;
    }
}
