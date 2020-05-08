package cn.iocoder.mall.order.application;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.config.ConfigFileApplicationListener;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;

/**
 * @author xiaofeng
 */
@SpringBootApplication(scanBasePackages = {"cn.iocoder.mall.order"})
public class OrderApplication {

    /**
     * 设置需要读取的配置文件的名字。 基于 {@link org.springframework.boot.context.config.ConfigFileApplicationListener#CONFIG_NAME_PROPERTY}
     * 实现。
     */
    private static final String CONFIG_NAME_VALUE = "biz,rest,rpc,application";

    public static void main(String[] args) {
        // 设置环境变量
        System.setProperty(ConfigFileApplicationListener.CONFIG_NAME_PROPERTY, CONFIG_NAME_VALUE);
        SpringApplication.run(OrderApplication.class, args);
    }


    /**
     * 解决异常信息： java.lang.IllegalArgumentException: Invalid character found in the request target.
     * The valid characters are defined in RFC 7230 and RFC 3986
     *
     * @return
     */
    @Bean
    public ConfigurableServletWebServerFactory webServerFactory() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.addConnectorCustomizers(new TomcatConnectorCustomizer() {
            @Override
            public void customize(Connector connector) {
                connector.setProperty("relaxedQueryChars", "|{}[]");
            }
        });
        return factory;
    }


}
