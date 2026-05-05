package cn.iocoder.yudao.framework.common.util.http;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HttpUtilsTest {

    @Test
    public void testReplaceUrlQuery() {
        // 定义测试用例：{原始URL, Key, Value, 期望结果}
        String[][] testCases = {
                // 场景1: 替换已存在的参数 (注意参数顺序可能会变，因为 UrlQuery 内部是 List)
                {"https://example.com/path?a=1&b=2", "a", "3", "https://example.com/path?b=2&a=3"},
                // 场景2: 添加不存在的参数
                {"https://example.com/path?a=1", "b", "2", "https://example.com/path?a=1&b=2"},
                // 场景3: URL 本身没有查询参数
                {"https://example.com/path", "a", "1", "https://example.com/path?a=1"},
                // 场景4: 值为空 (根据原逻辑，空值通常会被移除或不添加，这里假设是移除)
                // 注意：你需要根据 HttpUtils 实际对 null/empty 的处理来调整 expected
                {"https://example.com/path?a=1", "a", "", "https://example.com/path?a="},
        };

        System.out.println("开始运行 HttpUtils.replaceUrlQuery 测试...");

        for (int i = 0; i < testCases.length; i++) {
            String[] currentCase = testCases[i]; // 必须先取出当前这一行的数组

            String url = currentCase[0];
            String key = currentCase[1];
            String value = currentCase[2];
            String expected = currentCase[3];

            // 调用你优化后的方法
            String actual = HttpUtils.replaceUrlQuery(url, key, value);

            // 核心验证：断言实际结果必须等于期望结果
            // 如果不相等，测试会直接报错，并打印出是哪一行错了
            try {
                assertEquals(expected, actual, "测试用例 " + (i + 1) + " 失败: " + url);
                System.out.println("✅ 用例 " + (i + 1) + " 通过: " + actual);
            } catch (AssertionError e) {
                System.err.println("❌ 用例 " + (i + 1) + " 失败!");
                System.err.println("   输入: " + url + " | " + key + "=" + value);
                System.err.println("   期望: " + expected);
                System.err.println("   实际: " + actual);
                throw e; // 抛出错误，让测试标记为失败
            }
        }
    }
}