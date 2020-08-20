package cn.iocoder.mall.shopweb.enums;

import cn.iocoder.common.framework.exception.ErrorCode;

/**
 * 错误码枚举类
 *
 * shop web 系统，使用 1-007-000-000 段
 */
public interface ShopWebErrorCodeConstants {

    // ========== ORDER 模块 ==========
    ErrorCode ORDER_PRODUCT_SKU_NOT_EXISTS = new ErrorCode(1007000001, "商品 SKU 不存在！");
    ErrorCode ORDER_PRODUCT_SKU_QUANTITY_NOT_ENOUGH = new ErrorCode(1007000001, "商品 SKU 库存不足！");

}
