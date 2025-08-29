# AiBoChaWebSearchClient 使用指南

## 概述

`AiBoChaWebSearchClient` 是基于博查AI开放平台提供的网页搜索服务的Java客户端，实现了符合项目架构风格的HTTP客户端封装。

## 特性

- **统一的API调用风格**：参考 SunoApi 和 XunFeiPptApi 的实现方式
- **Record 类型数据结构**：使用 Record 类型定义请求和响应数据
- **简洁的响应数据模型**：包含网页搜索结果
- **灵活的搜索配置**：支持时间范围、域名过滤、结果数量等参数
- **错误处理机制**：统一的异常处理和日志记录

## 快速开始

### 1. 创建客户端实例

```java
// 使用默认base URL
AiBoChaWebSearchClient client = new AiBoChaWebSearchClient("your-api-key");

// 使用自定义base URL
AiBoChaWebSearchClient client = new AiBoChaWebSearchClient("https://custom.api.com", "your-api-key");
```

### 2. 基本搜索

```java
// 基本搜索
WebSearchRequest request = new WebSearchRequest(
    "Spring Boot 教程",
    null, null, null, null, null
);
AiWebSearchResponse result = client.search(request);
```

### 3. 高级搜索

```java
// 构建详细的搜索请求
WebSearchRequest request = new WebSearchRequest(
    "人工智能最新进展",
    FreshnessType.ONE_WEEK.getValue(),  // 搜索一周内的内容
    true,                               // 显示摘要
    "zhihu.com|csdn.net",              // 只搜索指定域名
    "spam.com",                         // 排除指定域名
    20                                  // 返回20条结果
);

AiWebSearchResponse result = client.search(request);
```

## API参数说明

### 请求参数

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| query | String | 是 | 用户的搜索词 |
| freshness | String | 否 | 搜索时间范围，默认为 noLimit |
| summary | Boolean | 否 | 是否显示文本摘要，默认为 false |
| include | String | 否 | 指定搜索的网站范围，多个域名使用\|或,分隔 |
| exclude | String | 否 | 排除搜索的网站范围，多个域名使用\|或,分隔 |
| count | Integer | 否 | 返回结果条数，范围1-50，默认为10 |

### 时间范围选项

使用 `FreshnessType` 枚举：

```java
FreshnessType.NO_LIMIT    // 不限（默认）
FreshnessType.ONE_DAY     // 一天内
FreshnessType.ONE_WEEK    // 一周内
FreshnessType.ONE_MONTH   // 一个月内
FreshnessType.ONE_YEAR    // 一年内
```

也可以使用自定义日期范围：
- 日期范围：`"2025-01-01..2025-04-06"`
- 指定日期：`"2025-04-06"`

### 响应数据结构

```java
// 主要响应数据
AiWebSearchResponse result = client.search(request);

// 网页搜索结果
List<AiWebSearchResponse.WebPage> webPages = result.webPages();
for (AiWebSearchResponse.WebPage page : webPages) {
    String title = page.title();             // 网页标题
    String url = page.url();                 // 网页URL
    String snippet = page.snippet();         // 内容描述
    String summary = page.summary();         // 文本摘要（如果请求了summary）
    String siteName = page.siteName();       // 网站名称
}
```

## 使用示例

### 示例1：搜索技术文档

```java
WebSearchRequest request = new WebSearchRequest(
    "Spring Boot 3.x 新特性",
    FreshnessType.ONE_MONTH.getValue(),
    true,
    "spring.io|baeldung.com|github.com",
    null,
    15
);

AiWebSearchResponse result = client.search(request);
```

### 示例2：搜索新闻资讯

```java
WebSearchRequest request = new WebSearchRequest(
    "AI大模型发展趋势",
    FreshnessType.ONE_WEEK.getValue(),
    null,
    null,
    "advertisement.com|spam.net",
    30
);

AiWebSearchResponse result = client.search(request);
```

## 注意事项

1. **API密钥**：需要先到博查AI开放平台（https://open.bochaai.com）获取API KEY
2. **请求频率**：注意遵守平台的API调用频率限制
3. **时间范围**：建议使用 `noLimit` 以获得更好的搜索效果
4. **域名过滤**：include和exclude参数最多支持20个域名
5. **结果数量**：单次搜索最多返回50条结果

## 集成建议

在Spring Boot项目中，建议将客户端配置为Bean：

```java
@Configuration
public class AiConfiguration {
    
    @Value("${ai.bocha.api-key}")
    private String apiKey;
    
    @Value("${ai.bocha.base-url:https://open.bochaai.com}")
    private String baseUrl;
    
    @Bean
    public AiBoChaWebSearchClient boChaWebSearchClient() {
        return new AiBoChaWebSearchClient(baseUrl, apiKey);
    }
}
```

## 故障排查

1. **网络连接问题**：检查网络连接和防火墙设置
2. **API密钥错误**：确认API KEY正确且有效
3. **请求参数错误**：检查必填参数是否正确填写
4. **服务器响应错误**：查看日志中的详细错误信息

## 更新日志

- v2.0.0：重大重构，统一 Record 类型，简化 API 调用，支持新的响应结构
- v1.3.0：统一使用 Record 类型，移除 Lombok 注解，保持代码风格一致性
- v1.2.0：进一步简化，移除视频搜索功能，专注于网页搜索
- v1.1.0：使用 Lombok 简化代码，移除图片搜索功能
- v1.0.0：初始版本，实现基本的网页搜索功能