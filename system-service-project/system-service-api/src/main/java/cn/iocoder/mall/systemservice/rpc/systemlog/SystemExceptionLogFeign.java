package cn.iocoder.mall.systemservice.rpc.systemlog;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.systemservice.rpc.systemlog.dto.SystemExceptionLogCreateDTO;
import cn.iocoder.mall.systemservice.rpc.systemlog.dto.SystemExceptionLogPageDTO;
import cn.iocoder.mall.systemservice.rpc.systemlog.dto.SystemExceptionLogProcessDTO;
import cn.iocoder.mall.systemservice.rpc.systemlog.vo.SystemExceptionLogVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
* 部门 Rpc 接口
*/
@FeignClient("system-service")
public interface SystemExceptionLogFeign {

    @PostMapping("/system/exceptionlog/createSystemExceptionLog")
    public CommonResult<Boolean> createSystemExceptionLog(@RequestBody SystemExceptionLogCreateDTO createDTO);

    @GetMapping("/system/exceptionlog/getSystemExceptionLog")
    public CommonResult<SystemExceptionLogVO> getSystemExceptionLog(@RequestParam("systemExceptionLogId") Integer systemExceptionLogId);
    @PostMapping("/system/exceptionlog/pageSystemExceptionLog")
    public CommonResult<PageResult<SystemExceptionLogVO>> pageSystemExceptionLog(@RequestBody SystemExceptionLogPageDTO pageDTO) ;

    @PostMapping("/system/exceptionlog/processSystemExceptionLog")
    public CommonResult<Boolean> processSystemExceptionLog(@RequestBody SystemExceptionLogProcessDTO processDTO) ;
}