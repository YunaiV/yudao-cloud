package cn.iocoder.yudao.gateway.util;

import cn.hutool.core.thread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 项目启动成功后，提供文档相关的地址
 *
 * @author 芋道源码
 */
@Component
@Slf4j
public class BannerApplicationRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) {
        ThreadUtil.execute(() -> {
            ThreadUtil.sleep(1, TimeUnit.SECONDS); // 延迟 1 秒，保证输出到结尾
            log.info("\n----------------------------------------------------------\n\t" +
                            "项目启动成功！\n\t" +
                            "接口文档: \t{} \n\t" +
                            "开发文档: \t{} \n\t" +
                            "视频教程: \t{} \n" +
                            "----------------------------------------------------------",
                    "https://cloud.iocoder.cn/api-doc/",
                    "https://cloud.iocoder.cn",
                    "https://t.zsxq.com/02Yf6M7Qn");

            // 数据报表
            System.out.println("[报表模块 yudao-module-report 教程][参考 https://cloud.iocoder.cn/report/ 开启]");
            // 工作流
            System.out.println("[工作流模块 yudao-module-bpm 教程][参考 https://cloud.iocoder.cn/bpm/ 开启]");
            // 商城系统
            System.out.println("[商城系统 yudao-module-mall 教程][参考 https://cloud.iocoder.cn/mall/build/ 开启]");
            // ERP 系统
            System.out.println("[ERP 系统 yudao-module-erp - 教程][参考 https://cloud.iocoder.cn/erp/build/ 开启]");
            // CRM 系统
            System.out.println("[CRM 系统 yudao-module-crm - 教程][参考 https://cloud.iocoder.cn/crm/build/ 开启]");
            // 微信公众号
            System.out.println("[微信公众号 yudao-module-mp 教程][参考 https://cloud.iocoder.cn/mp/build/ 开启]");
            // 支付平台
            System.out.println("[支付系统 yudao-module-pay - 教程][参考 https://doc.iocoder.cn/pay/build/ 开启]");
            // AI 大模型
            System.out.println("[AI 大模型 yudao-module-ai - 教程][参考 https://cloud.iocoder.cn/ai/build/ 开启]");
            // IOT 物联网
            System.out.println("[IOT 物联网 yudao-module-iot - 教程][参考 https://doc.iocoder.cn/iot/build/ 开启]");
        });
    }

}
