package cn.iocoder.mall.system.rpc.api.systemlog;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.system.rpc.request.systemlog.AccessLogAddRequest;
import cn.iocoder.mall.system.rpc.request.systemlog.ExceptionLogAddRequest;

public interface SystemLogRPC {

    CommonResult<Boolean> addAccessLog(AccessLogAddRequest accessLogAddRequest);

    CommonResult<Boolean> addExceptionLog(ExceptionLogAddRequest exceptionLogAddRequest);

}
