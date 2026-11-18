package cn.iocoder.yudao.module.ai.enums.model;

import cn.iocoder.yudao.framework.common.core.ArrayValuable;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * AI 模型平台
 *
 * 平台按提供方区分；同一提供方的对话、绘图、语音合成、视频等能力，按模型类型（AiModelTypeEnum）区分。
 * platform 值与枚举名一致（全大写），持久化到数据库的 platform 字段
 *
 * @author fansili
 */
@Getter
@AllArgsConstructor
public enum AiPlatformEnum implements ArrayValuable<String> {

    // ========== 国内平台 ==========

    ALIBABA("ALIBABA", "阿里巴巴"), // 阿里：通义千问、通义万相、语音合成
    BAIDU("BAIDU", "百度"), // 百度：文心一言（千帆）、语音合成
    DEEPSEEK("DEEPSEEK", "DeepSeek"), // DeepSeek
    ZHIPU("ZHIPU", "智谱"), // 智谱 AI
    IFLYTEK("IFLYTEK", "科大讯飞"), // 讯飞：星火
    BYTEDANCE("BYTEDANCE", "字节跳动"), // 字节：豆包（火山方舟）、Seedream、豆包语音
    TENCENT("TENCENT", "腾讯"), // 腾讯：混元、语音合成；语音合成的密钥为 SecretId|SecretKey
    SILICONFLOW("SILICONFLOW", "硅基流动"), // 硅基流动
    MINIMAX("MINIMAX", "MiniMax"), // 稀宇科技
    MOONSHOT("MOONSHOT", "月之暗面"), // KIMI
    BAICHUAN("BAICHUAN", "百川智能"), // 百川智能
    STEPFUN("STEPFUN", "阶跃星辰"), // 阶跃星辰
    XIAOMI("XIAOMI", "小米"), // 小米：MiMo
    KLING("KLING", "可灵"), // 快手：可灵视频；密钥为 API Key，或 AccessKey|SecretKey

    // ========== 国外平台 ==========

    OPENAI("OPENAI", "OpenAI"), // OpenAI 官方
    AZURE("AZURE", "AzureOpenAI"), // 微软 Azure：Azure OpenAI
    ANTHROPIC("ANTHROPIC", "Anthropic"), // Anthropic Claude
    GEMINI("GEMINI", "Gemini"), // 谷歌 Gemini
    OLLAMA("OLLAMA", "Ollama"),

    STABILITYAI("STABILITYAI", "Stability AI"), // Stability AI：Stable Diffusion
    MIDJOURNEY("MIDJOURNEY", "Midjourney"), // Midjourney
    SUNO("SUNO", "Suno"), // Suno AI
    GROK("GROK", "Grok"), // Grok

    ;

    /**
     * 平台
     */
    private final String platform;
    /**
     * 平台名
     */
    private final String name;

    public static final String[] ARRAYS = Arrays.stream(values()).map(AiPlatformEnum::getPlatform).toArray(String[]::new);

    public static AiPlatformEnum validatePlatform(String platform) {
        for (AiPlatformEnum platformEnum : AiPlatformEnum.values()) {
            if (platformEnum.getPlatform().equals(platform)) {
                return platformEnum;
            }
        }
        throw new IllegalArgumentException("非法平台： " + platform);
    }

    @Override
    public String[] array() {
        return ARRAYS;
    }

}
