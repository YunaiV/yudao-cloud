package cn.iocoder.mall.systemservice.manager.systemlog;

import cn.iocoder.mall.systemservice.convert.systemlog.SystemLogConvert;
import cn.iocoder.mall.systemservice.rpc.systemlog.dto.AccessLogAddDTO;
import cn.iocoder.mall.systemservice.rpc.systemlog.dto.ExceptionLogAddDTO;
import cn.iocoder.mall.systemservice.service.systemlog.SystemLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemLogManager {

    @Autowired
    private SystemLogService systemLogService;

    public void addAccessLog(AccessLogAddDTO accessLogAddDTO) {
        systemLogService.addAccessLog(SystemLogConvert.INSTANCE.convert(accessLogAddDTO));
    }

    public void addExceptionLog(ExceptionLogAddDTO exceptionLogAddDTO) {
        systemLogService.addExceptionLog(SystemLogConvert.INSTANCE.convert(exceptionLogAddDTO));
    }

}
