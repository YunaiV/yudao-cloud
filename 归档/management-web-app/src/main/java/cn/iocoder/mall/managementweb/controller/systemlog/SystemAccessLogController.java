package cn.iocoder.mall.managementweb.controller.systemlog;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.managementweb.controller.systemlog.dto.SystemAccessLogPageDTO;
import cn.iocoder.mall.managementweb.controller.systemlog.vo.SystemAccessLogVO;
import cn.iocoder.mall.managementweb.manager.systemlog.SystemAccessLogManager;
import cn.iocoder.security.annotations.RequiresPermissions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static cn.iocoder.common.framework.vo.CommonResult.success;

/**
* 系统访问日志 Controller
*/
@RestController
@RequestMapping("/system-access-log")
@Api(tags = "系统访问日志")
@Validated
public class SystemAccessLogController {

    @Autowired
    private SystemAccessLogManager systemAccessLogManager;

    @GetMapping("/page")
    @ApiOperation("获得系统访问日志分页")
    @RequiresPermissions("system:system-access-log:page")
    public CommonResult<PageResult<SystemAccessLogVO>> pageSystemAccessLog(SystemAccessLogPageDTO pageDTO) {
        return success(systemAccessLogManager.pageSystemAccessLog(pageDTO));
    }

}
