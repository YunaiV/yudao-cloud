package cn.iocoder.mall.systemservice.rpc.systemlog;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.systemservice.rpc.systemlog.dto.AccessLogAddDTO;
import cn.iocoder.mall.systemservice.rpc.systemlog.dto.ExceptionLogAddDTO;

public interface SystemLogRPC {

    CommonResult<Boolean> addAccessLog(AccessLogAddDTO accessLogAddDTO);

    CommonResult<Boolean> addExceptionLog(ExceptionLogAddDTO exceptionLogAddDTO);

}
