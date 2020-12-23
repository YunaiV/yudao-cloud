package cn.iocoder.security.annotations;

import java.lang.annotation.*;

/**
 * 参考 Shiro @RequiresPermissions 设计 http://shiro.apache.org/static/1.3.2/apidocs/org/apache/shiro/authz/annotation/RequiresPermissions.html
 *
 * 通过将该注解添加到 Controller 的方法上，进行授权鉴定
 *
 * ps：目前暂时只有管理员 Admin 使用到
 */
@Documented
@Target({ElementType.METHOD}) // 暂时不支持 ElementType.TYPE ，因为没有场景
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresPermissions {

    /**
     * 当有多个标识时，必须全部拥有权限，才可以操作
     *
     * @return 权限标识数组
     */
    String[] value() default {};

}
