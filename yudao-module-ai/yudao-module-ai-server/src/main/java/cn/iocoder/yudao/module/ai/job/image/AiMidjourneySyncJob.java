package cn.iocoder.yudao.module.ai.job.image;

import cn.iocoder.yudao.module.ai.service.image.AiImageService;
import com.xxl.job.core.handler.annotation.XxlJob;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Midjourney 同步 Job：定时拉去 midjourney 绘制状态
 *
 * @author fansili
 */
@Component
@Slf4j
public class AiMidjourneySyncJob {

    @Resource
    private AiImageService imageService;

    @XxlJob("aiMidjourneySyncJob")
    public void execute(String param) {
        Integer count = imageService.midjourneySync();
        log.info("[execute][同步 Midjourney ({}) 个]", count);
    }

}
