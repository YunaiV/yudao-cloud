package cn.iocoder.mall.managementweb.manager.systemlog;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.managementweb.controller.systemlog.dto.SystemExceptionLogPageDTO;
import cn.iocoder.mall.managementweb.controller.systemlog.dto.SystemExceptionLogProcessDTO;
import cn.iocoder.mall.managementweb.controller.systemlog.vo.SystemExceptionLogDetailVO;
import cn.iocoder.mall.managementweb.controller.systemlog.vo.SystemExceptionLogVO;
import cn.iocoder.mall.managementweb.convert.systemlog.SystemExceptionLogConvert;
import cn.iocoder.mall.systemservice.rpc.admin.AdminRpc;
import cn.iocoder.mall.systemservice.rpc.admin.vo.AdminVO;
import cn.iocoder.mall.systemservice.rpc.systemlog.SystemExceptionLogRpc;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

/**
* 系统异常日志 Manager
*/
@Service
public class SystemExceptionLogManager {

    @Reference(version = "${dubbo.consumer.SystemExceptionLogRpc.version}")
    private SystemExceptionLogRpc systemExceptionLogRpc;
    @Reference(version = "${dubbo.consumer.AdminRpc.version}")
    private AdminRpc adminRpc;

    /**
    * 获得系统异常日志
    *
    * @param systemExceptionLogId 系统异常日志编号
    * @return 系统异常日志
    */
    public SystemExceptionLogDetailVO getSystemExceptionLogDetail(Integer systemExceptionLogId) {
        // 获得系统异常明细
        CommonResult<cn.iocoder.mall.systemservice.rpc.systemlog.vo.SystemExceptionLogVO> getSystemExceptionLogResult
                = systemExceptionLogRpc.getSystemExceptionLog(systemExceptionLogId);
        getSystemExceptionLogResult.checkError();
        SystemExceptionLogDetailVO logDetailVO = SystemExceptionLogConvert.INSTANCE.convert(getSystemExceptionLogResult.getData());
        // 拼接处理管理员信息
        if (getSystemExceptionLogResult.getData().getProcessAdminId() != null) {
            CommonResult<AdminVO> adminVOResult = adminRpc.getAdmin(getSystemExceptionLogResult.getData().getProcessAdminId());
            adminVOResult.checkError();
            if (adminVOResult.getData() != null) {
                SystemExceptionLogDetailVO.Admin admin = SystemExceptionLogConvert.INSTANCE.convert(adminVOResult.getData());
                logDetailVO.setProcessAdmin(admin);
            }
        }
        return logDetailVO;
    }

    /**
    * 获得系统异常日志分页
    *
    * @param pageDTO 系统异常日志分页查询
    * @return 系统异常日志分页结果
    */
    public PageResult<SystemExceptionLogVO> pageSystemExceptionLog(SystemExceptionLogPageDTO pageDTO) {
        CommonResult<PageResult<cn.iocoder.mall.systemservice.rpc.systemlog.vo.SystemExceptionLogVO>> pageSystemExceptionLogResult
                = systemExceptionLogRpc.pageSystemExceptionLog(SystemExceptionLogConvert.INSTANCE.convert(pageDTO));
        pageSystemExceptionLogResult.checkError();
        return SystemExceptionLogConvert.INSTANCE.convertPage(pageSystemExceptionLogResult.getData());
    }

    /**
     * 处理系统异常日志
     *
     * @param processAdminId 处理管理员编号
     * @param processDTO 处理系统异常日志 DTO
     */
    public void processSystemExceptionLog(Integer processAdminId, SystemExceptionLogProcessDTO processDTO) {
        CommonResult<Boolean> processSystemExceptionLogResult = systemExceptionLogRpc.processSystemExceptionLog(
                SystemExceptionLogConvert.INSTANCE.convert(processDTO).setProcessAdminId(processAdminId));
        processSystemExceptionLogResult.checkError();
    }

}
