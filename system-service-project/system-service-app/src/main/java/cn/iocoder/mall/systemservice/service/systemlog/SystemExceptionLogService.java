package cn.iocoder.mall.systemservice.service.systemlog;

import cn.iocoder.common.framework.exception.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.systemservice.convert.systemlog.SystemExceptionLogConvert;
import cn.iocoder.mall.systemservice.dal.mysql.dataobject.systemlog.SystemExceptionLogDO;
import cn.iocoder.mall.systemservice.dal.mysql.mapper.systemlog.SystemExceptionLogMapper;
import cn.iocoder.mall.systemservice.enums.systemlog.SystemExceptionLogProcessStatusEnum;
import cn.iocoder.mall.systemservice.service.systemlog.bo.SystemExceptionLogBO;
import cn.iocoder.mall.systemservice.service.systemlog.bo.SystemExceptionLogCreateBO;
import cn.iocoder.mall.systemservice.service.systemlog.bo.SystemExceptionLogPageBO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static cn.iocoder.mall.systemservice.enums.SystemErrorCodeConstants.SYSTEM_EXCEPTION_LOG_NOT_FOUND;
import static cn.iocoder.mall.systemservice.enums.SystemErrorCodeConstants.SYSTEM_EXCEPTION_LOG_PROCESSED;

/**
 * 系统异常日志 Service
 */
@Service
public class SystemExceptionLogService {

    @Autowired
    private SystemExceptionLogMapper systemExceptionLogMapper;

    /**
     * 创建系统异常日志
     *
     * @param createBO 创建 BO
     */
    public void createSystemExceptionLog(SystemExceptionLogCreateBO createBO) {
        SystemExceptionLogDO logDO = SystemExceptionLogConvert.INSTANCE.convert(createBO);
        logDO.setProcessStatus(SystemExceptionLogProcessStatusEnum.INIT.getStatus());
        systemExceptionLogMapper.insert(logDO);
    }

    /**
     * 处理系统异常日志
     *
     * @param logId 日志编号
     * @param processAdminId 处理管理员编号
     * @param processStatus 处理状态
     */
    public void processSystemExceptionLog(Integer logId, Integer processAdminId, Integer processStatus) {
        SystemExceptionLogDO logDO = systemExceptionLogMapper.selectById(logId);
        if (logDO == null) {
            throw ServiceExceptionUtil.exception(SYSTEM_EXCEPTION_LOG_NOT_FOUND);
        }
        if (!SystemExceptionLogProcessStatusEnum.INIT.getStatus().equals(logDO.getProcessStatus())) {
            throw ServiceExceptionUtil.exception(SYSTEM_EXCEPTION_LOG_PROCESSED);
        }
        // 标记处理
        SystemExceptionLogDO updateObj = new SystemExceptionLogDO().setId(logId)
                .setProcessAdminId(processAdminId).setProcessStatus(processStatus);
        systemExceptionLogMapper.updateById(updateObj);
    }

    /**
     * 获得系统异常日志分页
     *
     * @param pageBO 系统异常日志分页查询
     * @return 系统异常日志分页结果
     */
    public PageResult<SystemExceptionLogBO> pageSystemExceptionLog(SystemExceptionLogPageBO pageBO) {
        IPage<SystemExceptionLogDO> systemExceptionLogDOPage = systemExceptionLogMapper.selectPage(pageBO);
        return SystemExceptionLogConvert.INSTANCE.convertPage(systemExceptionLogDOPage);
    }

    public SystemExceptionLogBO getSystemExceptionLog(Integer logId) {
        SystemExceptionLogDO logDO = systemExceptionLogMapper.selectById(logId);
        return SystemExceptionLogConvert.INSTANCE.convert(logDO);
    }
}
