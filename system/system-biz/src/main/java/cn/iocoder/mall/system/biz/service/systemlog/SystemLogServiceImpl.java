package cn.iocoder.mall.system.biz.service.systemlog;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.system.biz.bo.systemlog.AccessLogBO;
import cn.iocoder.mall.system.biz.convert.systemlog.SystemLogConvert;
import cn.iocoder.mall.system.biz.dao.system.AccessLogMapper;
import cn.iocoder.mall.system.biz.dao.system.ExceptionLogMapper;
import cn.iocoder.mall.system.biz.dataobject.systemlog.AccessLogDO;
import cn.iocoder.mall.system.biz.dataobject.systemlog.ExceptionLogDO;
import cn.iocoder.mall.system.biz.dto.system.AccessLogAddDTO;
import cn.iocoder.mall.system.biz.dto.system.AccessLogPageDTO;
import cn.iocoder.mall.system.biz.dto.system.ExceptionLogAddDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SystemLogServiceImpl implements SystemLogService {

    @Autowired
    private AccessLogMapper accessLogMapper;
    @Autowired
    private ExceptionLogMapper exceptionLogMapper;

    @Override
    public void addAccessLog(AccessLogAddDTO accessLogAddDTO) {
        AccessLogDO logDO = SystemLogConvert.INSTANCE.convert(accessLogAddDTO);
        if (logDO.getAccountId() == null) {
            logDO.setAccountId(AccessLogDO.ACCOUNT_ID_NULL);
        }
        logDO.setCreateTime(new Date());
        accessLogMapper.insert(logDO);
    }

    @Override
    public void addExceptionLog(ExceptionLogAddDTO exceptionLogAddDTO) {
        ExceptionLogDO logDO = SystemLogConvert.INSTANCE.convert(exceptionLogAddDTO);
        if (logDO.getAccountId() == null) {
            logDO.setAccountId(ExceptionLogDO.ACCOUNT_ID_NULL);
        }
        logDO.setCreateTime(new Date());
        exceptionLogMapper.insert(logDO);
    }


    @Override
    @SuppressWarnings("Duplicates")
    public PageResult<AccessLogBO> getAccessLogPage(AccessLogPageDTO accessLogPageDTO) {
        PageResult<AccessLogBO> accessLogPageBOPageResult = SystemLogConvert.INSTANCE.convertPage(
                accessLogMapper.selectPage(accessLogPageDTO));
        return accessLogPageBOPageResult;
    }
}
