package cn.iocoder.common.framework.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    /**
     * @param date    时间。若为空，则返回空串
     * @param pattern 时间格式化
     * @return 格式化后的时间字符串.
     */
    public static String format(Date date, String pattern) {
        if (date == null) {
            return "";
        }
        return new SimpleDateFormat(pattern).format(date);
    }

}