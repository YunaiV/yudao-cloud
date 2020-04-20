package cn.iocoder.mall.system.rpc.api;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.system.rpc.request.system.AccessLogAddRequest;

public interface SystemLogRPC {

    CommonResult<Boolean> addAccessLog(AccessLogAddRequest accessLogAddRequest);

}
