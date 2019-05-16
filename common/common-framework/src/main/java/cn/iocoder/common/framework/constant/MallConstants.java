package cn.iocoder.common.framework.constant;

/**
 * Mall 全局枚举
 */
public interface MallConstants {

    // 全局请求路径枚举类，用于定义不同用户类型的根请求路径
    /**
     * 根路径 - 用户
     */
    String ROOT_PATH_USER = "/users";
    /**
     * 根路径 - 管理员
     */
    String ROOT_PATH_ADMIN = "/admins";

    // 用户类型
    /**
     * 用户类型 - 用户
     */
    @Deprecated
    Integer USER_TYPE_USER = 1;
    /**
     * 用户类型 - 管理员
     */
    @Deprecated
    Integer USER_TYPE_ADMIN = 2;

    // HTTP Request Attr
    /**
     * HTTP Request Attr - 用户编号
     */
    String REQUEST_ATTR_USER_ID_KEY = "mall_user_id";
    /**
     * HTTP Request Attr - 用户类型
     */
    String REQUEST_ATTR_USER_TYPE_KEY = "mall_user_type";
    /**
     * HTTP Request Attr - Controller 执行返回
     */
    String REQUEST_ATTR_COMMON_RESULT = "mall_common_result";

}
