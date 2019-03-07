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
    PRODUCT_CATEGORY_STATUS_EQUALS(1002001003, "商品分类已经是该状态"),
    PRODUCT_CATEGORY_DELETE_ONLY_DISABLE(1002001004, "只有关闭的商品分类才可以删除"),
    PRODUCT_CATEGORY_MUST_ENABLE(1002001005, "只有开启的商品分类，才可以使用"),

    // ========== PRODUCT SPU + SKU 模块 ==========
    PRODUCT_SKU_ATTR_CANT_NOT_DUPLICATE(1003002000, "一个 Sku 下，不能有重复的规格"),
    PRODUCT_SPU_ATTR_NUMBERS_MUST_BE_EQUALS(1003002001, "一个 Spu 下的每个 Sku ，其规格数必须一致"),
    PRODUCT_SPU_SKU__NOT_DUPLICATE(1003002002, "一个 Spu 下的每个 Sku ，必须不重复"),
    PRODUCT_SPU_NOT_EXISTS(1003002003, "Spu 不存在"),

    // ========== PRODUCT ATTR + ATTR_VALUE 模块 ==========
    PRODUCT_ATTR_VALUE_NOT_EXIST(1003003000, "商品属性值不存在"),
    PRODUCT_ATTR_NOT_EXIST(1003003001, "商品属性值不存在"),
    PRODUCT_ATTR_EXISTS(1003003002, "商品规格已经存在"),
    PRODUCT_ATTR_STATUS_EQUALS(1003003003, "商品规格已经是该状态"),
    PRODUCT_ATTR_VALUE_EXISTS(1003003004, "商品规格值已经存在"),
    PRODUCT_ATTR_VALUE_STATUS_EQUALS(1003003005, "商品规格值已经是该状态"),
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