package cn.iocoder.mall.systemservice.service.systemlog;

import cn.iocoder.mall.systemservice.convert.systemlog.SystemLogConvert;
import cn.iocoder.mall.systemservice.dal.mysql.dataobject.systemlog.AccessLogDO;
import cn.iocoder.mall.systemservice.dal.mysql.dataobject.systemlog.ExceptionLogDO;
import cn.iocoder.mall.systemservice.dal.mysql.mapper.systemlog.AccessLogMapper;
import cn.iocoder.mall.systemservice.dal.mysql.mapper.systemlog.ExceptionLogMapper;
import cn.iocoder.mall.systemservice.service.systemlog.bo.AccessLogAddBO;
import cn.iocoder.mall.systemservice.service.systemlog.bo.ExceptionLogAddBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemLogService {

    @Autowired
    private AccessLogMapper accessLogMapper;
    @Autowired
    private ExceptionLogMapper exceptionLogMapper;

    public void addAccessLog(AccessLogAddBO accessLogAddBO) {
        AccessLogDO logDO = SystemLogConvert.INSTANCE.convert(accessLogAddBO);
        accessLogMapper.insert(logDO);
    }

    public void addExceptionLog(ExceptionLogAddBO exceptionLogAddBO) {
        ExceptionLogDO logDO = SystemLogConvert.INSTANCE.convert(exceptionLogAddBO);
        exceptionLogMapper.insert(logDO);
    }

//    @Override
//    @SuppressWarnings("Duplicates")
//    public PageResult<AccessLogBO> getAccessLogPage(AccessLogPageDTO accessLogPageDTO) {
//        PageResult<AccessLogBO> accessLogPageBOPageResult = SystemLogConvert.INSTANCE.convertPage(
//                accessLogMapper.selectPage(accessLogPageDTO)); // TODO FROM 芋艿 to 2447007062：可以考虑直接 return，简洁 + IDEA 不告警；
//        return accessLogPageBOPageResult;
//    }

}
