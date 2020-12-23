package cn.iocoder.security.annotations;

import java.lang.annotation.*;

/**
 * 通过将该注解添加到 Controller 的方法上，声明无需进行登陆
 */
@Documented
@Target({ElementType.METHOD}) // 暂时不支持 ElementType.TYPE ，因为没有场景
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresNone {
}
