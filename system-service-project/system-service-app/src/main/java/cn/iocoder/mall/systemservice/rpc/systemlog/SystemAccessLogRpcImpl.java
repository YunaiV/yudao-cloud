package cn.iocoder.mall.systemservice.rpc.systemlog;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.systemservice.manager.systemlog.SystemAccessLogManager;
import cn.iocoder.mall.systemservice.rpc.systemlog.dto.SystemAccessLogCreateDTO;
import cn.iocoder.mall.systemservice.rpc.systemlog.vo.SystemAccessLogPageDTO;
import cn.iocoder.mall.systemservice.rpc.systemlog.vo.SystemAccessLogVO;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import static cn.iocoder.common.framework.vo.CommonResult.success;

/**
* 系统访问日志 Rpc 实现类
*/
@Service(version = "${dubbo.provider.SystemAccessLogRpc.version}")
public class SystemAccessLogRpcImpl implements SystemAccessLogRpc {

    @Autowired
    private SystemAccessLogManager systemAccessLogManager;

    @Override
    public CommonResult<Boolean> createSystemAccessLog(SystemAccessLogCreateDTO createDTO) {
        systemAccessLogManager.createSystemAccessLog(createDTO);
        return success(true);
    }

    @Override
    public CommonResult<PageResult<SystemAccessLogVO>> pageSystemAccessLog(SystemAccessLogPageDTO pageDTO) {
        return success(systemAccessLogManager.pageSystemAccessLog(pageDTO));
    }

}
