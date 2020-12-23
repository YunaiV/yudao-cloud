package cn.iocoder.mall.systemservice.rpc.systemlog;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.systemservice.rpc.systemlog.dto.SystemAccessLogCreateDTO;
import cn.iocoder.mall.systemservice.rpc.systemlog.vo.SystemAccessLogPageDTO;
import cn.iocoder.mall.systemservice.rpc.systemlog.vo.SystemAccessLogVO;

/**
* 系统访问日志 Rpc 接口
*/
public interface SystemAccessLogRpc {

    /**
    * 创建系统访问日志
    *
    * @param createDTO 创建系统访问日志 DTO
    * @return 系统访问日志编号
    */
    CommonResult<Boolean> createSystemAccessLog(SystemAccessLogCreateDTO createDTO);

    /**
    * 获得系统访问日志分页
    *
    * @param pageDTO 系统访问日志分页查询
    * @return 系统访问日志分页结果
    */
    CommonResult<PageResult<SystemAccessLogVO>> pageSystemAccessLog(SystemAccessLogPageDTO pageDTO);

}
