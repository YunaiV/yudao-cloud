package cn.iocoder.yudao.module.system.util.datetimeconvert;

import cn.iocoder.yudao.framework.test.core.ut.BaseMockitoUnitTest;
import org.junit.jupiter.api.Test;

/**
 * {@link DateTimeConverter} 的测试类
 * @author BrucePang
 */
public class DateTimeConverterTest extends BaseMockitoUnitTest {

    @Test
    public void testConvertToLocalDateTime() {
        // 准备参数
        int[] dateTimeArray = {2024, 8, 5, 15, 45, 14, 35};
        // 执行
        System.out.println(DateTimeConverter.convertToLocalDateTime(dateTimeArray));
    }
}
