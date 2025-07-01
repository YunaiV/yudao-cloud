package cn.iocoder.yudao.framework.sentinel.core.annotation;

import java.lang.annotation.*;

/**
 * Sentinel 流控注解
 * 
 * 用于标注需要进行流量控制的方法
 *
 * @author 芋道源码
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SentinelFlow {

    /**
     * 资源名称，默认为类名+方法名
     */
    String value() default "";

    /**
     * 限流阈值，默认为10
     */
    double count() default 10.0;

    /**
     * 限流阈值类型，0-线程数，1-QPS，默认QPS
     */
    int grade() default 1;

    /**
     * 流控针对的调用来源，默认不区分调用来源
     */
    String limitApp() default "default";

    /**
     * 调用关系限流策略：直接、链路、关联
     */
    int strategy() default 0;

    /**
     * 流控效果（直接拒绝 / 排队等待 / 慢启动模式），默认直接拒绝
     */
    int controlBehavior() default 0;

    /**
     * 是否开启集群流控
     */
    boolean clusterMode() default false;

    /**
     * 被限流时的处理逻辑
     */
    String blockHandler() default "";

    /**
     * blockHandler 函数访问范围需要是 public
     * 返回类型需要与原方法相匹配
     * 参数类型需要和原方法相匹配并且最后加一个额外的参数，类型为BlockException
     * blockHandler 函数默认需要和原方法在同一个类中
     * 若希望使用其他类的函数，则可以指定 blockHandlerClass 为对应的类的 Class 对象
     * 注意对应的函数必需为 static 函数，否则无法解析
     */
    Class<?> blockHandlerClass() default void.class;

    /**
     * 用于在抛出异常的时候提供 fallback 处理逻辑
     * fallback 函数可以针对所有类型的异常（除了 exceptionsToIgnore 里面排除掉的异常类型）进行处理
     */
    String fallback() default "";

    /**
     * fallbackClass 用于通用的 fallback 逻辑
     */
    Class<?> fallbackClass() default void.class;

    /**
     * 用于指定哪些异常被排除掉，不会计入异常统计中，也不会进入 fallback 逻辑中
     * 而是会原样抛出
     */
    Class<? extends Throwable>[] exceptionsToIgnore() default {};
}