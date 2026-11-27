package cn.iocoder.yudao.module.ai.framework.ai.core.webserch;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 联网搜索平台的枚举
 *
 * 配置项 yudao.ai.web-search.platform 填小写即可，例如 bocha
 *
 * @author 芋道源码
 */
@Getter
@RequiredArgsConstructor
public enum AiWebSearchPlatformEnum {

    BOCHA("博查"), // 只有搜索，默认
    PARALLEL("Parallel"), // 搜索 + 网页读取，匿名访问 MCP，无需 API Key
    KEENABLE("Keenable"); // 搜索 + 网页读取，未配置 API Key 时匿名访问

    /**
     * 名字
     */
    private final String name;

}
