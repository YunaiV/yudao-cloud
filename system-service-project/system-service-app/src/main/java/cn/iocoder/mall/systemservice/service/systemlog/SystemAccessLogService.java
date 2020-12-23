package cn.iocoder.mall.systemservice.service.systemlog;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.systemservice.convert.systemlog.SystemAccessLogConvert;
import cn.iocoder.mall.systemservice.dal.mysql.dataobject.systemlog.SystemAccessLogDO;
import cn.iocoder.mall.systemservice.dal.mysql.mapper.systemlog.SystemAccessLogMapper;
import cn.iocoder.mall.systemservice.service.systemlog.bo.SystemAccessLogBO;
import cn.iocoder.mall.systemservice.service.systemlog.bo.SystemAccessLogCreateBO;
import cn.iocoder.mall.systemservice.service.systemlog.bo.SystemAccessLogPageBO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 系统访问日志 Service
 */
@Service
public class SystemAccessLogService {

    @Autowired
    private SystemAccessLogMapper systemAccessLogMapper;

    /**
     * 创建系统访问日志
     *
     * @param createBO 创建系统访问日志 BO
     */
    public void createSystemAccessLog(SystemAccessLogCreateBO createBO) {
        SystemAccessLogDO logDO = SystemAccessLogConvert.INSTANCE.convert(createBO);
        systemAccessLogMapper.insert(logDO);
    }

    /**
     * 获得系统访问日志分页
     *
     * @param pageBO 系统访问日志分页查询
     * @return 系统访问日志分页结果
     */
    public PageResult<SystemAccessLogBO> pageSystemAccessLog(SystemAccessLogPageBO pageBO) {
        IPage<SystemAccessLogDO> systemAccessLogDOPage = systemAccessLogMapper.selectPage(pageBO);
        return SystemAccessLogConvert.INSTANCE.convertPage(systemAccessLogDOPage);
    }

}
