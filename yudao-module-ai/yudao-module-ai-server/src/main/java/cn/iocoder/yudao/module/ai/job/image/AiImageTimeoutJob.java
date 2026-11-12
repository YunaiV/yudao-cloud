package cn.iocoder.yudao.module.ai.job.image;

import cn.iocoder.yudao.framework.tenant.core.job.TenantJob;
import cn.iocoder.yudao.module.ai.service.image.AiImageService;
import com.xxl.job.core.handler.annotation.XxlJob;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * AI 绘画超时 Job：进行中、且超过 10 分钟未完成的绘画，置为失败
 *
 * 例如说：服务重启中断了异步绘制、中转一直没有回调；否则一直是进行中，前端会持续轮询
 *
 * @author 芋道源码
 */
@Component
@Slf4j
public class AiImageTimeoutJob {

    @Resource
    private AiImageService imageService;

    @XxlJob("aiImageTimeoutJob")
    @TenantJob
    public String execute() {
        int count = imageService.timeoutImage();
        log.info("[execute][超时 AI 绘画 ({}) 个]", count);
        return String.format("超时 AI 绘画 %s 个", count);
    }

}
