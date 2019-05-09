package cn.iocoder.mall.spring.boot.constant;

/**
 * 全局请求路径枚举类，用于定义不同用户类型的根请求路径
 */
public interface RootRequestPath {

    /**
     * 管理员
     */
    String ADMIN = "/admins";
    /**
     * 用户
     */
    String USER = "/users";

}
