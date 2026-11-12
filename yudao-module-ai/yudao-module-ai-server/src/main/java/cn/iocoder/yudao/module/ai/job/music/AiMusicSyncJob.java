package cn.iocoder.yudao.module.ai.job.music;

import cn.iocoder.yudao.framework.tenant.core.job.TenantJob;
import cn.iocoder.yudao.module.ai.service.music.AiMusicService;
import com.xxl.job.core.handler.annotation.XxlJob;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * AI 音乐同步 Job：查询中转生成进度、拆分歌曲、转存文件、处理超时
 *
 * @author 芋道源码
 */
@Component
@Slf4j
public class AiMusicSyncJob {

    @Resource
    private AiMusicService musicService;

    @XxlJob("aiMusicSyncJob")
    @TenantJob
    public String execute() {
        int count = musicService.syncMusic();
        log.info("[execute][同步 AI 音乐 ({}) 个]", count);
        return String.format("同步 AI 音乐 %s 个", count);
    }

}
