package cn.iocoder.yudao.framework.common.util.http;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * {@link HttpUtils} 的单元测试
 */
public class HttpUtilsTest {

    @Test
    public void testReplaceUrlQuery_replace() {
        // 准备参数
        String url = "https://www.iocoder.cn/path?a=1&b=2";
        // 调用
        String result = HttpUtils.replaceUrlQuery(url, "a", "3");
        // 断言：被替换的 key 会移到末尾，原顺序的其它参数保留
        assertEquals("https://www.iocoder.cn/path?b=2&a=3", result);
    }

    @Test
    public void testReplaceUrlQuery_add() {
        // 准备参数
        String url = "https://www.iocoder.cn/path?a=1";
        // 调用
        String result = HttpUtils.replaceUrlQuery(url, "b", "2");
        // 断言
        assertEquals("https://www.iocoder.cn/path?a=1&b=2", result);
    }

    @Test
    public void testReplaceUrlQuery_noQuery() {
        // 准备参数：原 URL 没有 query
        String url = "https://www.iocoder.cn/path";
        // 调用
        String result = HttpUtils.replaceUrlQuery(url, "a", "1");
        // 断言
        assertEquals("https://www.iocoder.cn/path?a=1", result);
    }

    @Test
    public void testReplaceUrlQuery_emptyValue() {
        // 准备参数：value 为空字符串
        String url = "https://www.iocoder.cn/path?a=1";
        // 调用
        String result = HttpUtils.replaceUrlQuery(url, "a", "");
        // 断言：保留 key，value 为空
        assertEquals("https://www.iocoder.cn/path?a=", result);
    }

}
