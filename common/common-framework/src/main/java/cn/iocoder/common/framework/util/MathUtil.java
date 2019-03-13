package cn.iocoder.common.framework.util;

import java.util.Random;

public class MathUtil {

    /**
     * 随机对象
     */
    private static final Random RANDOM = new Random(); // TODO 后续优化

    /**
     * 随机[min, max]范围内的数字
     *
     * @param min 随机开始
     * @param max 随机结束
     * @return 数字
     */
    public static int random(int min, int max) {
        if (min == max) {
            return min;
        }
        if (min > max) {
            int temp = min;
            min = max;
            max = temp;
        }
        // 随即开始
        int diff = max - min + 1;
        return RANDOM.nextInt(diff) + min;
    }

}
