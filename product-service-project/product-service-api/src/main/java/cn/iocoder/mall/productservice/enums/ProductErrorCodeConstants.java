package cn.iocoder.mall.productservice.enums;

import cn.iocoder.common.framework.exception.ErrorCode;

/**
 * 错误码枚举类
 *
 * 商品系统，使用 1-003-000-000 段
 */
public interface ProductErrorCodeConstants {

    // ========== PRODUCT CATEGORY 模块 ==========
    ErrorCode PRODUCT_CATEGORY_PARENT_NOT_EXISTS = new ErrorCode(1003001000, "父分类不存在");
    ErrorCode PRODUCT_CATEGORY_NOT_EXISTS = new ErrorCode(1003001001, "商品分类不存在");
    ErrorCode PRODUCT_CATEGORY_STATUS_NOT_EXISTS = new ErrorCode(1003001001, "商品分类状态不存在");
    ErrorCode PRODUCT_CATEGORY_PARENT_NOT_SELF = new ErrorCode(1003001002, "不能设置自己为父分类");
    ErrorCode PRODUCT_CATEGORY_STATUS_EQUALS = new ErrorCode(1002001003, "商品分类已经是该状态");
    ErrorCode PRODUCT_CATEGORY_DELETE_ONLY_DISABLE = new ErrorCode(1002001004, "只有关闭的商品分类才可以删除");
    ErrorCode PRODUCT_CATEGORY_DELETE_ONLY_NO_CHILD = new ErrorCode(1002001004, "只有无子分类的商品分类才可以删除");
    ErrorCode PRODUCT_CATEGORY_MUST_ENABLE = new ErrorCode(1002001005, "只有开启的商品分类，才可以使用");
    ErrorCode PRODUCT_CATEGORY_PARENT_CAN_NOT_BE_LEVEL2 = new ErrorCode(1002001005, "父分类必须是一级分类");

    // ========== PRODUCT SPU + SKU 模块 ==========
    ErrorCode PRODUCT_SKU_ATTR_CANT_NOT_DUPLICATE = new ErrorCode(1003002000, "一个 Sku 下，不能有重复的规格");
    ErrorCode PRODUCT_SPU_ATTR_NUMBERS_MUST_BE_EQUALS = new ErrorCode(1003002001, "一个 Spu 下的每个 Sku ，其规格数必须一致");
    ErrorCode PRODUCT_SPU_SKU__NOT_DUPLICATE = new ErrorCode(1003002002, "一个 Spu 下的每个 Sku ，必须不重复");
    ErrorCode PRODUCT_SPU_NOT_EXISTS = new ErrorCode(1003002003, "Spu 不存在");
    ErrorCode PRODUCT_SPU_CATEGORY_MUST_BE_LEVEL2 = new ErrorCode(1003002003, "Spu 只能添加在二级分类下");

    // ========== PRODUCT ATTR + ATTR_VALUE 模块 ==========
    ErrorCode  PRODUCT_ATTR_VALUE_NOT_EXIST = new ErrorCode(1003003000, "商品属性值不存在");
    ErrorCode PRODUCT_ATTR_NOT_EXIST = new ErrorCode(1003003001, "商品属性值不存在");
    ErrorCode PRODUCT_ATTR_EXISTS = new ErrorCode(1003003002, "商品规格已经存在");
    ErrorCode PRODUCT_ATTR_STATUS_EQUALS = new ErrorCode(1003003003, "商品规格已经是该状态");
    ErrorCode PRODUCT_ATTR_VALUE_EXISTS = new ErrorCode(1003003004, "商品规格值已经存在");
    ErrorCode PRODUCT_ATTR_VALUE_STATUS_EQUALS = new ErrorCode(1003003005, "商品规格值已经是该状态");

    // ========== PRODUCT BRAND模块 ==========
    ErrorCode PRODUCT_BRAND_EXIST = new ErrorCode(1003004000,"品牌值已经存在");

}
