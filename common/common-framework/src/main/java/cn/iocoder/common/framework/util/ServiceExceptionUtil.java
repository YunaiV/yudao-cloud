package cn.iocoder.common.framework.util;

import cn.iocoder.common.framework.exception.ServiceException;
import cn.iocoder.common.framework.vo.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * {@link ServiceException} 工具类
 *
 * 目的在于，格式化异常信息提示。
 * 考虑到 String.format 在参数不正确时会报错，因此使用 {} 作为占位符，并使用 {@link #doFormat(int, String, Object...)} 方法来格式化
 *
 * 因为 {@link #messages} 里面默认是没有异常信息提示的模板的，所以需要使用方自己初始化进去。目前想到的有几种方式：
 *
 * 1. 异常提示信息，写在枚举类中，例如说，cn.iocoder.oceans.user.api.constants.ErrorCodeEnum 类 + ServiceExceptionConfiguration
 * 2. 异常提示信息，写在 .properties 等等配置文件
 * 3. 异常提示信息，写在 Apollo 等等配置中心中，从而实现可动态刷新
 * 4. 异常提示信息，存储在 db 等等数据库中，从而实现可动态刷新
 */
public class ServiceExceptionUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceExceptionUtil.class);

    /**
     * 错误码提示模板
     */
    private static ConcurrentMap<Integer, String> messages = new ConcurrentHashMap<>();

    public static void putAll(Map<Integer, String> messages) {
        ServiceExceptionUtil.messages.putAll(messages);
    }

    public static void put(Integer code, String message) {
        ServiceExceptionUtil.messages.put(code, message);
    }

    // TODO 芋艿，可能不是目前最优解，目前暂时这样 枚举实现接口
    public static <T> CommonResult<T> error(Integer code) {
        return CommonResult.error(code, messages.get(code));
    }

    public static CommonResult error(Integer code, Object... params) {
        String message = doFormat(code, messages.get(code), params);
        return CommonResult.error(code, message);
    }

    /**
     * 创建指定编号的 ServiceException 的异常
     *
     * @param code 编号
     * @return 异常
     */
    public static ServiceException exception(Integer code) {
        return new ServiceException(code, messages.get(code));
    }

    /**
     * 创建指定编号的 ServiceException 的异常
     *
     * @param code 编号
     * @param params 消息提示的占位符对应的参数
     * @return 异常
     */
    public static ServiceException exception(Integer code, Object... params) {
        String message = doFormat(code, messages.get(code), params);
        return new ServiceException(code, message);
    }

    public static ServiceException exception(Integer code, String messagePattern, Object... params) {
        String message = doFormat(code, messagePattern, params);
        return new ServiceException(code, message);
    }

    /**
     * 将错误编号对应的消息使用 params 进行格式化。
     *
     * @param code           错误编号
     * @param messagePattern 消息模版
     * @param params         参数
     * @return 格式化后的提示
     */
    private static String doFormat(int code, String messagePattern, Object... params) {
        StringBuilder sbuf = new StringBuilder(messagePattern.length() + 50);
        int i = 0;
        int j;
        int l;
        for (l = 0; l < params.length; l++) {
            j = messagePattern.indexOf("{}", i);
            if (j == -1) {
                LOGGER.error("[doFormat][参数过多：错误码({})|错误内容({})|参数({})", code, messagePattern, params);
                if (i == 0) {
                    return messagePattern;
                } else {
                    sbuf.append(messagePattern.substring(i, messagePattern.length()));
                    return sbuf.toString();
                }
            } else {
                sbuf.append(messagePattern.substring(i, j));
                sbuf.append(params[l]);
                i = j + 2;
            }
        }
        if (messagePattern.indexOf("{}", i) != -1) {
            LOGGER.error("[doFormat][参数过少：错误码({})|错误内容({})|参数({})", code, messagePattern, params);
        }
        sbuf.append(messagePattern.substring(i, messagePattern.length()));
        return sbuf.toString();
    }

}
