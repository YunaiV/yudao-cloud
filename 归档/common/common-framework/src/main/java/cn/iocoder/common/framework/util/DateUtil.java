package cn.iocoder.common.framework.util;

import org.springframework.util.Assert;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    /**
     * 计算当期时间相差的日期
     *
     * @param field  日历字段.<br/>eg:Calendar.MONTH,Calendar.DAY_OF_MONTH,<br/>Calendar.HOUR_OF_DAY等.
     * @param amount 相差的数值
     * @return 计算后的日志
     */
    public static Date addDate(int field, int amount) {
        return addDate(null, field, amount);
    }

    /**
     * 计算当期时间相差的日期
     *
     * @param date   设置时间
     * @param field  日历字段.<br/>eg:Calendar.MONTH,Calendar.DAY_OF_MONTH,<br/>Calendar.HOUR_OF_DAY等.
     * @param amount 相差的数值
     * @return 计算后的日志
     */
    public static Date addDate(Date date, int field, int amount) {
        if (amount == 0) {
            return date;
        }
        Calendar c = Calendar.getInstance();
        if (date != null) {
            c.setTime(date);
        }
        c.add(field, amount);
        return c.getTime();
    }

    /**
     * @param date    时间。若为空，则返回空串
     * @param pattern 时间格式化
     * @return 格式化后的时间字符串.
     */
    public static String format(Date date, String pattern) {
        if (date == null) {
            return "";
        }
        // TODO 芋艿，后面改成缓存
        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * 获取指定天结束时间
     *
     * @param date 日期
     * @return 获得该日期的开始
     */
    public static Date getDayBegin(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        setCalender(calendar, 0, 0, 0, 0);
        return calendar.getTime();
    }

    /**
     * 获取当天开始时间
     *
     * @return 获得该日期的开始
     */
    public static Date getDayBegin() {
        return getDayBegin(new Date());
    }

    /**
     * 获取指定天结束时间
     *
     * @param date 日期
     * @return 获得该日期的结束
     */
    public static Date getDayEnd(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        setCalender(calendar, 23, 59, 59, 999);
        return calendar.getTime();
    }

    /**
     * 获取当天结束时间
     *
     * @return 获得该日期的开始
     */
    public static Date getDayEnd() {
        return getDayEnd(new Date());
    }

    /**
     * 设置Calendar的小时、分钟、秒、毫秒
     *
     * @param calendar    日历
     * @param hour        小时
     * @param minute      分钟
     * @param second      秒
     * @param milliSecond 毫秒
     */
    public static void setCalender(Calendar calendar, int hour, int minute, int second, int milliSecond) {
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MILLISECOND, milliSecond);
    }

    /**
     * 判断当前时间，是否在该时间范围内
     *
     * @param beginTime 开始时间
     * @param endTime 结束时间
     * @return 是否在
     */
    public static boolean isBetween(Date beginTime, Date endTime) {
        Assert.notNull(beginTime, "开始时间不能为空");
        Assert.notNull(endTime, "结束时间不能为空");
        Date now = new Date();
        return beginTime.getTime() <= now.getTime()
                && now.getTime() <= endTime.getTime();
    }

    public static Date max(Date a, Date b) {
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }
        return a.compareTo(b) > 0 ? a : b;
    }

}
