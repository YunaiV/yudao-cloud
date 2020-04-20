package cn.iocoder.mall.system.rpc.rpc;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.system.biz.dto.system.AccessLogAddDTO;
import cn.iocoder.mall.system.biz.service.system.SystemLogService;
import cn.iocoder.mall.system.rpc.api.SystemLogRPC;
import cn.iocoder.mall.system.rpc.convert.SystemLogConvert;
import cn.iocoder.mall.system.rpc.request.system.AccessLogAddRequest;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service(version = "dubbo.provider.SystemLogRPC.version", validation = "true")
public class SystemLogRPCImpl implements SystemLogRPC {

    @Autowired
    private SystemLogService systemLogService;

    @Override
    public CommonResult<Boolean> addAccessLog(AccessLogAddRequest accessLogAddRequest) {
        AccessLogAddDTO accessLogAddDTO = SystemLogConvert.INSTANCE.convert(accessLogAddRequest);
        systemLogService.addAccessLog(accessLogAddDTO);
        return CommonResult.success(true);
    }

}
