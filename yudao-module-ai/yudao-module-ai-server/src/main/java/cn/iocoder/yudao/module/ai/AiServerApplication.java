package cn.iocoder.yudao.module.ai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 项目的启动类
 * <p>
 * 如果你碰到启动的问题，请认真阅读 https://cloud.iocoder.cn/quick-start/ 文章
 * 如果你碰到启动的问题，请认真阅读 https://cloud.iocoder.cn/quick-start/ 文章
 * 如果你碰到启动的问题，请认真阅读 https://cloud.iocoder.cn/quick-start/ 文章
 *
 * @author 芋道源码
 */
@SpringBootApplication(exclude = {
        org.springframework.ai.vectorstore.qdrant.autoconfigure.QdrantVectorStoreAutoConfiguration.class,
        org.springframework.ai.vectorstore.milvus.autoconfigure.MilvusVectorStoreAutoConfiguration.class,
}) // 解决 application-${profile}.yaml 配置文件下，通过 spring.autoconfigure.exclude 无法排除的问题
public class AiServerApplication {

    public static void main(String[] args) {
        // 如果你碰到启动的问题，请认真阅读 https://cloud.iocoder.cn/quick-start/ 文章
        // 如果你碰到启动的问题，请认真阅读 https://cloud.iocoder.cn/quick-start/ 文章
        // 如果你碰到启动的问题，请认真阅读 https://cloud.iocoder.cn/quick-start/ 文章

        SpringApplication.run(AiServerApplication.class, args);

        // 如果你碰到启动的问题，请认真阅读 https://cloud.iocoder.cn/quick-start/ 文章
        // 如果你碰到启动的问题，请认真阅读 https://cloud.iocoder.cn/quick-start/ 文章
        // 如果你碰到启动的问题，请认真阅读 https://cloud.iocoder.cn/quick-start/ 文章
    }

}
