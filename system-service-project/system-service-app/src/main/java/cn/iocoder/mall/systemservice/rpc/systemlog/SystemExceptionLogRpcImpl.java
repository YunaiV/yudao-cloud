package cn.iocoder.mall.systemservice.rpc.systemlog;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.systemservice.manager.systemlog.SystemExceptionLogManager;
import cn.iocoder.mall.systemservice.rpc.systemlog.dto.SystemExceptionLogCreateDTO;
import cn.iocoder.mall.systemservice.rpc.systemlog.dto.SystemExceptionLogPageDTO;
import cn.iocoder.mall.systemservice.rpc.systemlog.dto.SystemExceptionLogProcessDTO;
import cn.iocoder.mall.systemservice.rpc.systemlog.vo.SystemExceptionLogVO;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import static cn.iocoder.common.framework.vo.CommonResult.success;

@Service(version = "${dubbo.provider.SystemExceptionLogRpc.version}")
public class SystemExceptionLogRpcImpl implements SystemExceptionLogRpc {

    @Autowired
    private SystemExceptionLogManager systemExceptionLogManager;

    @Override
    public CommonResult<Boolean> createSystemExceptionLog(SystemExceptionLogCreateDTO createDTO) {
        systemExceptionLogManager.createSystemExceptionLog(createDTO);
        return success(true);
    }

    @Override
    public CommonResult<SystemExceptionLogVO> getSystemExceptionLog(Integer systemExceptionLogId) {
        return success(systemExceptionLogManager.getSystemExceptionLog(systemExceptionLogId));
    }

    @Override
    public CommonResult<PageResult<SystemExceptionLogVO>> pageSystemExceptionLog(SystemExceptionLogPageDTO pageDTO) {
        return success(systemExceptionLogManager.pageSystemExceptionLog(pageDTO));
    }

    @Override
    public CommonResult<Boolean> processSystemExceptionLog(SystemExceptionLogProcessDTO processDTO) {
        systemExceptionLogManager.processSystemExceptionLog(processDTO);
        return CommonResult.success(true);
    }

}
