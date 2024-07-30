package cn.iocoder.yudao.framework.operatelog.config;

import cn.hutool.extra.spring.SpringUtil;
import cn.iocoder.yudao.framework.operatelog.core.service.LogRecordServiceImpl;
import cn.iocoder.yudao.module.system.api.logger.OperateLogApi;
import com.mzt.logapi.service.ILogRecordService;
import com.mzt.logapi.starter.annotation.EnableLogRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * 操作日志配置类
 *
 * @author HUIHUI
 */
@EnableLogRecord(tenant = "") // 貌似用不上 tenant 这玩意给个空好啦
@AutoConfiguration
@Slf4j
public class YudaoOperateLogConfiguration {

    @Bean
    @Primary
    public ILogRecordService iLogRecordServiceImpl(OperateLogApi operateLogApi) {
        // Cloud 专属逻辑：优先使用本地的 operateLogApi 实现类，而不是 Feign 调用
        try {
            OperateLogApi operateLogApiImpl = SpringUtil.getBean("operateLogApiImpl", OperateLogApi.class);
            if (operateLogApiImpl != null) {
                operateLogApi =  operateLogApiImpl;
            }
        } catch (Exception ignored) {}
        return new LogRecordServiceImpl(operateLogApi);
    }

}
