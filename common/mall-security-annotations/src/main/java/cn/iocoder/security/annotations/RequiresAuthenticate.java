package cn.iocoder.security.annotations;

import java.lang.annotation.*;

/**
 * 要求用户认证（登陆）注解。通过将该注解添加到 Controller 上，会自动校验用户是否登陆。
 *
 * 默认请求下，用户访问的 API 接口，无需登陆。主要的考虑是，
 * 1. 需要用户登陆的接口，本身会获取在线用户的编号。如果不添加 @RequiresLogin 注解就会报错。
 * 2. 大多数情况下，用户的 API 接口无需登陆。
 *
 * ps：同样适用于管理员 Admin
 */
@Documented
@Target({ElementType.METHOD}) // 暂时不支持 ElementType.TYPE ，因为没有场景
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresAuthenticate {
}
