package cn.iocoder.yudao.module.ai.job.music;

import cn.iocoder.yudao.module.ai.service.music.AiMusicService;
import com.xxl.job.core.handler.annotation.XxlJob;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * 同步 Suno 任务状态以及回写对应的音乐信息 Job
 *
 * @author xiaoxin
 */
@Component
@Slf4j
public class AiSunoSyncJob {

    @Resource
    private AiMusicService musicService;

    @XxlJob("aiSunoSyncJob")
    public void execute(String param) {
        Integer count = musicService.syncMusic();
        log.info("[execute][同步 Suno ({}) 个]", count);
    }

}
