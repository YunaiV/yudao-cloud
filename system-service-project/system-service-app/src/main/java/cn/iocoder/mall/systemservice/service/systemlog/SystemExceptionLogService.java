package cn.iocoder.mall.systemservice.service.systemlog;

import cn.iocoder.mall.systemservice.convert.systemlog.SystemExceptionLogConvert;
import cn.iocoder.mall.systemservice.dal.mysql.dataobject.systemlog.SystemExceptionLogDO;
import cn.iocoder.mall.systemservice.dal.mysql.mapper.systemlog.SystemExceptionLogMapper;
import cn.iocoder.mall.systemservice.service.systemlog.bo.SystemExceptionLogCreateBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 系统异常日志 Service
 */
@Service
public class SystemExceptionLogService {

    @Autowired
    private SystemExceptionLogMapper systemExceptionLogMapper;

    public void createSystemExceptionLog(SystemExceptionLogCreateBO createBO) {
        SystemExceptionLogDO logDO = SystemExceptionLogConvert.INSTANCE.convert(createBO);
        systemExceptionLogMapper.insert(logDO);
    }

}
