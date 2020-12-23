package cn.iocoder.mall.web.core.constant;

public interface CommonMallConstants {

    // 全局请求路径枚举类，用于定义不同用户类型的根请求路径
    /**
     * 根路径 - 用户
     */
    @Deprecated
    String ROOT_PATH_USER = "/users";
    /**
     * 根路径 - 管理员
     */
    @Deprecated
    String ROOT_PATH_ADMIN = "/admins";

    // HTTP Request Attr
    /**
     * HTTP Request Attr - 用户编号
     *
     * 考虑到 mall-spring-boot-starter-web 不依赖 mall-spring-boot-starter-security，但是又希望拿到认证过的用户编号，
     * 因此通过 Request 的 Attribute 进行共享
     */
    String REQUEST_ATTR_USER_ID_KEY = "mall_user_id";
    /**
     * HTTP Request Attr - 用户类型
     *
     * 作用同 {@link #REQUEST_ATTR_USER_ID_KEY}
     */
    String REQUEST_ATTR_USER_TYPE_KEY = "mall_user_type";

    /**
     * HTTP Request Attr - Controller 执行返回
     *
     * 通过该 Request 的 Attribute，获取到请求执行结果，从而在访问日志中，记录是否成功。
     */
    String REQUEST_ATTR_COMMON_RESULT = "mall_common_result";
    /**
     * HTTP Request Attr - 访问开始时间
     */
    String REQUEST_ATTR_ACCESS_START_TIME = "mall_access_start_time";


}
