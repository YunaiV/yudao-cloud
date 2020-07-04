package cn.iocoder.mall.systemservice.rpc.systemlog;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.systemservice.manager.systemlog.SystemLogManager;
import cn.iocoder.mall.systemservice.rpc.systemlog.dto.AccessLogAddDTO;
import cn.iocoder.mall.systemservice.rpc.systemlog.dto.ExceptionLogAddDTO;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service(version = "${dubbo.provider.SystemLogRPC.version}", validation = "false")
public class SystemLogRPCImpl implements SystemLogRPC {

    @Autowired
    private SystemLogManager systemLogManager;

    @Override
    public CommonResult<Boolean> addAccessLog(AccessLogAddDTO accessLogAddDTO) {
        systemLogManager.addAccessLog(accessLogAddDTO);
        return CommonResult.success(true);
    }

    @Override
    public CommonResult<Boolean> addExceptionLog(ExceptionLogAddDTO exceptionLogAddDTO) {
        systemLogManager.addExceptionLog(exceptionLogAddDTO);
        return CommonResult.success(true);
    }

}
