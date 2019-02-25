package cn.iocoder.mall.user.sdk.annotation;

import java.lang.annotation.*;

/**
 * URL 是否允许所有都可访问。即用户不登陆，就可以访问指定 URL 。
 *
 * 例如说，注册接口，用户是不需要登陆，就可以访问的。
 */
@Documented
@Target({ElementType.METHOD}) // ElementType.TYPE 暂时不支持类级别。为了减少判断，略微提升性能。
@Retention(RetentionPolicy.RUNTIME)
public @interface PermitAll {
}