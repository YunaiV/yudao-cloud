package cn.iocoder.mall.managementweb.controller.systemlog;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.managementweb.controller.systemlog.dto.SystemExceptionLogPageDTO;
import cn.iocoder.mall.managementweb.controller.systemlog.vo.SystemExceptionLogDetailVO;
import cn.iocoder.mall.managementweb.controller.systemlog.vo.SystemExceptionLogVO;
import cn.iocoder.mall.managementweb.manager.systemlog.SystemExceptionLogManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static cn.iocoder.common.framework.vo.CommonResult.success;

/**
* 系统异常日志 Controller
*/
@RestController
@RequestMapping("/system-exception-log")
@Api(tags = "系统异常日志")
@Validated
public class SystemExceptionLogController {

    @Autowired
    private SystemExceptionLogManager systemExceptionLogManager;

    @GetMapping("/get")
    @ApiOperation("获得系统异常日志明细")
    @ApiImplicitParam(name = "logId", value = "系统异常日志编号", required = true)
    public CommonResult<SystemExceptionLogDetailVO> getSystemExceptionLogDetail(@RequestParam("logId") Integer logId) {
        return success(systemExceptionLogManager.getSystemExceptionLogDetail(logId));
    }

    @GetMapping("/page")
    @ApiOperation("获得系统异常日志分页")
    public CommonResult<PageResult<SystemExceptionLogVO>> pageSystemExceptionLog(SystemExceptionLogPageDTO pageDTO) {
        return success(systemExceptionLogManager.pageSystemExceptionLog(pageDTO));
    }

}
