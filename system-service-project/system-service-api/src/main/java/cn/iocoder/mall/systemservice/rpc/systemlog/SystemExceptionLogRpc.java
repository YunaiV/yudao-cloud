package cn.iocoder.mall.systemservice.rpc.systemlog;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.systemservice.rpc.systemlog.dto.SystemExceptionLogCreateDTO;

public interface SystemExceptionLogRpc {

    CommonResult<Boolean> createSystemExceptionLog(SystemExceptionLogCreateDTO createDTO);

}
