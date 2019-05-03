package cn.iocoder.mall.admin.sdk.dict;

import java.lang.annotation.*;

/**
 * 字典转换
 *
 * @author Sin
 * @time 2019-04-16 20:22
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DictVal {

    /**
     * 字典的 key
     *
     * @return
     */
    String dicKey();
    /**
     * 字典 value
     *
     * @return
     */
    String dicValue();
    /**
     * - 暂时只有 dictDisplayName 字典值转换为 dictDisplayName 给用户界面
     *
     * @return
     */
    String mode() default "dictDisplayName";
}
