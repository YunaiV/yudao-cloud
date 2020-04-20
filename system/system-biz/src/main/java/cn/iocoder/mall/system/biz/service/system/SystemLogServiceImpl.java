package cn.iocoder.mall.system.biz.service.system;

import cn.iocoder.mall.system.biz.convert.systemlog.SystemLogConvert;
import cn.iocoder.mall.system.biz.dao.system.AccessLogMapper;
import cn.iocoder.mall.system.biz.dataobject.system.AccessLogDO;
import cn.iocoder.mall.system.biz.dto.system.AccessLogAddDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SystemLogServiceImpl implements SystemLogService {

    @Autowired
    private AccessLogMapper accessLogMapper;

    @Override
    public void addAccessLog(AccessLogAddDTO accessLogAddDTO) {
        AccessLogDO logDO = SystemLogConvert.INSTANCE.convert(accessLogAddDTO);
        if (logDO.getAccountId() == null) {
            logDO.setAccountId(AccessLogDO.ACCOUNT_ID_NULL);
        }
        logDO.setCreateTime(new Date());
        accessLogMapper.insert(logDO);
    }

}
