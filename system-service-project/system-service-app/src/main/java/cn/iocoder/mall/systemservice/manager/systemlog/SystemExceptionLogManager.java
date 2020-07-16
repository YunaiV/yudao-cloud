package cn.iocoder.mall.systemservice.manager.systemlog;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.systemservice.convert.systemlog.SystemExceptionLogConvert;
import cn.iocoder.mall.systemservice.rpc.systemlog.dto.SystemExceptionLogCreateDTO;
import cn.iocoder.mall.systemservice.rpc.systemlog.dto.SystemExceptionLogPageDTO;
import cn.iocoder.mall.systemservice.rpc.systemlog.dto.SystemExceptionLogProcessDTO;
import cn.iocoder.mall.systemservice.rpc.systemlog.vo.SystemExceptionLogVO;
import cn.iocoder.mall.systemservice.service.systemlog.SystemExceptionLogService;
import cn.iocoder.mall.systemservice.service.systemlog.bo.SystemExceptionLogBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 系统异常日志 Manager
 */
@Service
public class SystemExceptionLogManager {

    @Autowired
    private SystemExceptionLogService systemExceptionLogService;

    /**
     * 创建系统异常日志
     *
     * @param createDTO 创建系统异常日志 DTO
     */
    public void createSystemExceptionLog(SystemExceptionLogCreateDTO createDTO) {
        systemExceptionLogService.createSystemExceptionLog(SystemExceptionLogConvert.INSTANCE.convert(createDTO));
    }

    /**
     * 获得系统异常日志
     *
     * @param systemExceptionLogId 系统异常日志编号
     * @return 系统异常日志
     */
    public SystemExceptionLogVO getSystemExceptionLog(Integer systemExceptionLogId) {
        SystemExceptionLogBO systemExceptionLogBO = systemExceptionLogService.getSystemExceptionLog(systemExceptionLogId);
        return SystemExceptionLogConvert.INSTANCE.convert(systemExceptionLogBO);
    }

    /**
     * 获得系统异常日志分页
     *
     * @param pageDTO 系统异常日志分页查询
     * @return 系统异常日志分页结果
     */
    public PageResult<SystemExceptionLogVO> pageSystemExceptionLog(SystemExceptionLogPageDTO pageDTO) {
        PageResult<SystemExceptionLogBO> pageResultBO = systemExceptionLogService.pageSystemExceptionLog(SystemExceptionLogConvert.INSTANCE.convert(pageDTO));
        return SystemExceptionLogConvert.INSTANCE.convertPage(pageResultBO);
    }

    /**
     * 处理系统异常日志，完成或者忽略
     *
     * @param processDTO 处理 DTO
     */
    public void processSystemExceptionLog(SystemExceptionLogProcessDTO processDTO) {
        systemExceptionLogService.processSystemExceptionLog(processDTO.getLogId(), processDTO.getProcessAdminId(),
                processDTO.getProcessStatus());
    }

}
