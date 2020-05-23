package cn.iocoder.mall.system.biz.log.operation.annotation;

import java.lang.annotation.*;

/**
 * @author Hccake
 * @version 1.0
 * @date 2019/10/15 18:09
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperationLogging {

    /**
     * 日志信息
     * @return
     */
    String value();
}
