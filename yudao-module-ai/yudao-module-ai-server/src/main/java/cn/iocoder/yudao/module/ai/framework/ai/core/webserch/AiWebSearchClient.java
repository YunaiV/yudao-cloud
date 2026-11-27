
package cn.iocoder.yudao.module.ai.framework.ai.core.webserch;

/**
 * 网络搜索客户端接口：搜索、网页读取
 *
 * @author 芋道源码
 */
public interface AiWebSearchClient {

    /**
     * 网页搜索
     *
     * @param request 搜索请求
     * @return 搜索结果
     */
    AiWebSearchResponse search(AiWebSearchRequest request);

    /**
     * 读取网页正文
     *
     * 只有支持网页读取的平台实现，例如 Parallel；其它平台（例如博查）不支持，抛出 {@link UnsupportedOperationException}
     *
     * @param url 网页地址
     * @return 网页内容
     */
    default AiWebFetchResponse fetch(String url) {
        throw new UnsupportedOperationException(getClass().getSimpleName() + " 不支持网页读取");
    }

}
