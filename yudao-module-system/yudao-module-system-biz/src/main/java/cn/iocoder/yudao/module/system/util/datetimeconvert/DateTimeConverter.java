package cn.iocoder.yudao.module.system.util.datetimeconvert;

import java.time.LocalDateTime;

/**
 * 时间工具类, 用于将 int[] 转换成 LocalDateTime
 * @author BrucePang
 */
public class DateTimeConverter {

    public static LocalDateTime convertToLocalDateTime(int[] dateTimeArray) {
        if (dateTimeArray == null || dateTimeArray.length != 7) {
            throw new IllegalArgumentException("Invalid date time array");
        }
        return LocalDateTime.of(
                dateTimeArray[0], // year
                dateTimeArray[1], // month
                dateTimeArray[2], // day
                dateTimeArray[3], // hour
                dateTimeArray[4], // minute
                dateTimeArray[5], // second
                dateTimeArray[6]  // nanosecond
        );
    }
}