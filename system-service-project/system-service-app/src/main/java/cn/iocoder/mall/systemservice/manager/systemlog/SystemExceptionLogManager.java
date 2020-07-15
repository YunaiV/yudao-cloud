package cn.iocoder.mall.systemservice.manager.systemlog;

import cn.iocoder.mall.systemservice.convert.systemlog.SystemExceptionLogConvert;
import cn.iocoder.mall.systemservice.rpc.systemlog.dto.SystemExceptionLogCreateDTO;
import cn.iocoder.mall.systemservice.service.systemlog.SystemExceptionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 系统异常日志 Manager
 */
@Service
public class SystemExceptionLogManager {

    @Autowired
    private SystemExceptionLogService systemLogService;

    public void createSystemExceptionLog(SystemExceptionLogCreateDTO createDTO) {
        systemLogService.createSystemExceptionLog(SystemExceptionLogConvert.INSTANCE.convert(createDTO));
    }

}
