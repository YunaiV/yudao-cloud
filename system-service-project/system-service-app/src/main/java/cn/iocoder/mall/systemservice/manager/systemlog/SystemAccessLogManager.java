package cn.iocoder.mall.systemservice.manager.systemlog;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.systemservice.convert.systemlog.SystemAccessLogConvert;
import cn.iocoder.mall.systemservice.rpc.systemlog.dto.SystemAccessLogCreateDTO;
import cn.iocoder.mall.systemservice.rpc.systemlog.vo.SystemAccessLogPageDTO;
import cn.iocoder.mall.systemservice.rpc.systemlog.vo.SystemAccessLogVO;
import cn.iocoder.mall.systemservice.service.systemlog.SystemAccessLogService;
import cn.iocoder.mall.systemservice.service.systemlog.bo.SystemAccessLogBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 系统访问日志 Manager
 */
@Service
public class SystemAccessLogManager {

    @Autowired
    private SystemAccessLogService systemAccessLogService;

    /**
     * 创建系统访问日志
     *
     * @param createDTO 创建系统访问日志 DTO
     */
    public void createSystemAccessLog(SystemAccessLogCreateDTO createDTO) {
        systemAccessLogService.createSystemAccessLog(SystemAccessLogConvert.INSTANCE.convert(createDTO));
    }

    /**
     * 获得系统访问日志分页
     *
     * @param pageDTO 系统访问日志分页查询
     * @return 系统访问日志分页结果
     */
    public PageResult<SystemAccessLogVO> pageSystemAccessLog(SystemAccessLogPageDTO pageDTO) {
        PageResult<SystemAccessLogBO> pageResultBO = systemAccessLogService.pageSystemAccessLog(SystemAccessLogConvert.INSTANCE.convert(pageDTO));
        return SystemAccessLogConvert.INSTANCE.convertPage(pageResultBO);
    }

}
