package cn.iocoder.yudao.framework.desensitize.core.base.annotation;

import cn.iocoder.yudao.framework.desensitize.core.base.handler.DesensitizationHandler;
import cn.iocoder.yudao.framework.desensitize.core.base.serializer.StringDesensitizeSerializer;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.lang.annotation.*;

/**
 * 顶级脱敏注解，自定义注解需要使用此注解
 *
 * @author gaibu
 */
@Documented
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside // 此注解是其他所有 jackson 注解的元注解，打上了此注解的注解表明是 jackson 注解的一部分
@JsonSerialize(using = StringDesensitizeSerializer.class) // 指定序列化器
public @interface DesensitizeBy {

    /**
     * 脱敏处理器
     */
    @SuppressWarnings("rawtypes")
    Class<? extends DesensitizationHandler> handler();

}
