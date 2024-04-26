package cn.iocoder.yudao.module.pay.util;

import cn.hutool.core.util.NumberUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 金额工具类
 *
 * @author 芋道源码
 */
public class MoneyUtils {

    /**
     * 计算百分比金额，四舍五入
     *
     * @param price 金额
     * @param rate 百分比，例如说 56.77% 则传入 56.77
     * @return 百分比金额
     */
    public static Integer calculateRatePrice(Integer price, Double rate) {
        return calculateRatePrice(price, rate, 0, RoundingMode.HALF_UP).intValue();
    }

    /**
     * 计算百分比金额
     *
     * @param price 金额
     * @param rate 百分比，例如说 56.77% 则传入 56.77
     * @param scale 保留小数位数
     * @param roundingMode 舍入模式
     */
    public static BigDecimal calculateRatePrice(Number price, Number rate, int scale, RoundingMode roundingMode) {
        return NumberUtil.toBigDecimal(price).multiply(NumberUtil.toBigDecimal(rate)) // 乘以
                .divide(BigDecimal.valueOf(100), scale, roundingMode); // 除以 100
    }

}
