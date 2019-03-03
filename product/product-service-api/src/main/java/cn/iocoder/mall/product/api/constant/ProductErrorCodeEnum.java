package cn.iocoder.mall.product.api.constant;

/**
 * 错误码枚举类
 *
 * 商品系统，使用 1-003-000-000 段
 */
public enum ProductErrorCodeEnum {

    // ========== PRODUCT CATEGORY 模块 ==========
    PRODUCT_CATEGORY_PARENT_NOT_EXISTS(1003001000, "父分类不存在"),
    PRODUCT_CATEGORY_NOT_EXISTS(1003001001, "商品分类不存在"),
    PRODUCT_CATEGORY_PARENT_NOT_SELF(1003001002, "不能设置自己为父分类"),
    PRODUCT_CATEGORY_STATUS_EQUALS(1002002003, "商品分类已经是该状态"),
    PRODUCT_CATEGORY_DELETE_ONLY_DISABLE(1002002004, "只有关闭的商品分类才可以删除"),

    ;

    private final int code;
    private final String message;

    ProductErrorCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}