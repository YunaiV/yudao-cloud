package cn.iocoder.mall.system.biz.service.systemlog;

import cn.iocoder.mall.system.biz.dto.system.AccessLogAddDTO;
import cn.iocoder.mall.system.biz.dto.system.ExceptionLogAddDTO;

public interface SystemLogService {

    void addAccessLog(AccessLogAddDTO accessLogAddDTO);

    void addExceptionLog(ExceptionLogAddDTO exceptionLogAddDTO);

}
