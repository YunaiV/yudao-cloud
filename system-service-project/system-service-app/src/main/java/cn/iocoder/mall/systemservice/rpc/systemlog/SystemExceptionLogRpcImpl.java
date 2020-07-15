package cn.iocoder.mall.systemservice.rpc.systemlog;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.systemservice.manager.systemlog.SystemExceptionLogManager;
import cn.iocoder.mall.systemservice.rpc.systemlog.dto.SystemExceptionLogCreateDTO;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service(version = "${dubbo.provider.SystemExceptionLogRpc.version}", validation = "false")
public class SystemExceptionLogRpcImpl implements SystemExceptionLogRpc {

    @Autowired
    private SystemExceptionLogManager systemExceptionLogManager;

    @Override
    public CommonResult<Boolean> createSystemExceptionLog(SystemExceptionLogCreateDTO createDTO) {
        systemExceptionLogManager.createSystemExceptionLog(createDTO);
        return CommonResult.success(true);
    }

}
