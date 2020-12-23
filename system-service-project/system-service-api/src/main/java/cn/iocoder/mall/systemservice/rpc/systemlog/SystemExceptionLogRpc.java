package cn.iocoder.mall.systemservice.rpc.systemlog;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.systemservice.rpc.systemlog.dto.SystemExceptionLogCreateDTO;
import cn.iocoder.mall.systemservice.rpc.systemlog.dto.SystemExceptionLogPageDTO;
import cn.iocoder.mall.systemservice.rpc.systemlog.dto.SystemExceptionLogProcessDTO;
import cn.iocoder.mall.systemservice.rpc.systemlog.vo.SystemExceptionLogVO;

/**
 * 系统异常日志 Rpc 接口
 */
public interface SystemExceptionLogRpc {

    /**
     * 创建系统异常日志
     *
     * @param createDTO 创建系统异常日志 DTO
     * @return 成功
     */
    CommonResult<Boolean> createSystemExceptionLog(SystemExceptionLogCreateDTO createDTO);

    /**
     * 获得系统异常日志
     *
     * @param systemExceptionLogId 系统异常日志编号
     * @return 系统异常日志
     */
    CommonResult<SystemExceptionLogVO> getSystemExceptionLog(Integer systemExceptionLogId);

    /**
     * 获得系统异常日志分页
     *
     * @param pageDTO 系统异常日志分页查询
     * @return 系统异常日志分页结果
     */
    CommonResult<PageResult<SystemExceptionLogVO>> pageSystemExceptionLog(SystemExceptionLogPageDTO pageDTO);

    /**
     * 处理系统异常日志，完成或者忽略
     *
     * @param processDTO 处理 DTO
     * @return 成功
     */
    CommonResult<Boolean> processSystemExceptionLog(SystemExceptionLogProcessDTO processDTO);

}
