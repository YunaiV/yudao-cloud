package cn.iocoder.mall.systemservice.controller;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.systemservice.manager.systemlog.SystemExceptionLogManager;
import cn.iocoder.mall.systemservice.rpc.systemlog.dto.SystemExceptionLogCreateDTO;
import cn.iocoder.mall.systemservice.rpc.systemlog.dto.SystemExceptionLogPageDTO;
import cn.iocoder.mall.systemservice.rpc.systemlog.dto.SystemExceptionLogProcessDTO;
import cn.iocoder.mall.systemservice.rpc.systemlog.vo.SystemExceptionLogVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static cn.iocoder.common.framework.vo.CommonResult.success;

/**
 * Title:
 * Description:
 *
 * @author zhuyang
 * @version 1.0 2021/10/11
 */
@RestController
@RequestMapping("/system/exceptionlog")
public class SystemExceptionLogController {
    @Autowired
    private SystemExceptionLogManager systemExceptionLogManager;

    @PostMapping("createSystemExceptionLog")
    public CommonResult<Boolean> createSystemExceptionLog(@RequestBody SystemExceptionLogCreateDTO createDTO) {
        systemExceptionLogManager.createSystemExceptionLog(createDTO);
        return success(true);
    }

    @GetMapping("getSystemExceptionLog")
    public CommonResult<SystemExceptionLogVO> getSystemExceptionLog(@RequestParam("systemExceptionLogId") Integer systemExceptionLogId) {
        return success(systemExceptionLogManager.getSystemExceptionLog(systemExceptionLogId));
    }

    @PostMapping("pageSystemExceptionLog")
    public CommonResult<PageResult<SystemExceptionLogVO>> pageSystemExceptionLog(@RequestBody SystemExceptionLogPageDTO pageDTO) {
        return success(systemExceptionLogManager.pageSystemExceptionLog(pageDTO));
    }

    @PostMapping("processSystemExceptionLog")
    public CommonResult<Boolean> processSystemExceptionLog(@RequestBody SystemExceptionLogProcessDTO processDTO) {
        systemExceptionLogManager.processSystemExceptionLog(processDTO);
        return CommonResult.success(true);
    }
}
