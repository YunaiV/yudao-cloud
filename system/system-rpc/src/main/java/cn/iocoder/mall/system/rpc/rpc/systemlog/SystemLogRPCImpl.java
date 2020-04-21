package cn.iocoder.mall.system.rpc.rpc.systemlog;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.system.biz.dto.system.AccessLogAddDTO;
import cn.iocoder.mall.system.biz.dto.system.ExceptionLogAddDTO;
import cn.iocoder.mall.system.biz.service.systemlog.SystemLogService;
import cn.iocoder.mall.system.rpc.api.systemlog.SystemLogRPC;
import cn.iocoder.mall.system.rpc.convert.systemlog.SystemLogConvert;
import cn.iocoder.mall.system.rpc.request.systemlog.AccessLogAddRequest;
import cn.iocoder.mall.system.rpc.request.systemlog.ExceptionLogAddRequest;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service(version = "${dubbo.provider.SystemLogRPC.version}", validation = "true")
public class SystemLogRPCImpl implements SystemLogRPC {

    @Autowired
    private SystemLogService systemLogService;

    @Override
    public CommonResult<Boolean> addAccessLog(AccessLogAddRequest accessLogAddRequest) {
        AccessLogAddDTO accessLogAddDTO = SystemLogConvert.INSTANCE.convert(accessLogAddRequest);
        systemLogService.addAccessLog(accessLogAddDTO);
        return CommonResult.success(true);
    }

    @Override
    public CommonResult<Boolean> addExceptionLog(ExceptionLogAddRequest exceptionLogAddRequest) {
        ExceptionLogAddDTO exceptionLogAddDTO = SystemLogConvert.INSTANCE.convert(exceptionLogAddRequest);
        systemLogService.addExceptionLog(exceptionLogAddDTO);
        return CommonResult.success(true);
    }

}
