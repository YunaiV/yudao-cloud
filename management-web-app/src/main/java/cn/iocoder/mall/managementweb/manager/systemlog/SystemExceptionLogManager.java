package cn.iocoder.mall.managementweb.manager.systemlog;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.managementweb.controller.systemlog.dto.SystemExceptionLogPageDTO;
import cn.iocoder.mall.managementweb.controller.systemlog.vo.SystemExceptionLogDetailVO;
import cn.iocoder.mall.managementweb.controller.systemlog.vo.SystemExceptionLogVO;
import cn.iocoder.mall.managementweb.convert.systemlog.SystemExceptionLogConvert;
import cn.iocoder.mall.systemservice.rpc.systemlog.SystemExceptionLogRpc;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

/**
* 系统异常日志 Manager
*/
@Service
public class SystemExceptionLogManager {

    @Reference(version = "$ {dubbo.consumer.SystemExceptionLogRpc.version}", validation = "false")
    private SystemExceptionLogRpc systemExceptionLogRpc;

    /**
    * 获得系统异常日志
    *
    * @param systemExceptionLogId 系统异常日志编号
    * @return 系统异常日志
    */
    public SystemExceptionLogDetailVO getSystemExceptionLogDetail(Integer systemExceptionLogId) {
        CommonResult<cn.iocoder.mall.systemservice.rpc.systemlog.vo.SystemExceptionLogVO> getSystemExceptionLogResult
                = systemExceptionLogRpc.getSystemExceptionLog(systemExceptionLogId);
        getSystemExceptionLogResult.checkError();
//        return SystemExceptionLogConvert.INSTANCE.convert(getSystemExceptionLogResult.getData());
        return null;
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

}
