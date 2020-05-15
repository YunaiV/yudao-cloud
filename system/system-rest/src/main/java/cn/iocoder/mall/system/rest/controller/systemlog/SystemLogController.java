package cn.iocoder.mall.system.rest.controller.systemlog;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.system.biz.bo.systemlog.AccessLogBO;
import cn.iocoder.mall.system.biz.dto.system.AccessLogPageDTO;
import cn.iocoder.mall.system.biz.service.systemlog.SystemLogService;
import cn.iocoder.mall.system.rest.convert.systemlog.AccessLogConvert;
import cn.iocoder.mall.system.rest.response.systemlog.AccessLogPageResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author:ycjx
 * @descriptio
 * @create:2019-06-23 16:42
 */
@RestController
@RequestMapping("admins/system/logs")
@Api("系统日志")
public class SystemLogController {

    @Autowired
    private SystemLogService systemLogService;

    @GetMapping("/access/page")
    @ApiOperation(value = "访问日志分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", example = "1"),
            @ApiImplicitParam(name = "pageNo", value = "页码，从 1 开始", example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = true, example = "10"),
    })
    public CommonResult<PageResult<AccessLogPageResponse>> page(@RequestParam(value = "accountId", required = false) Integer accountId,
                                                                @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                                                                @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {

        // TODO FROM 芋艿 to 2447007062：不要留这么大的空行；
        // TODO FROM 芋艿 to 2447007062：使用 Request 接收参数噢；
        AccessLogPageDTO accessLogPageDTO = new AccessLogPageDTO().setAccountId(accountId)
                .setPageNo(pageNo).setPageSize(pageSize);
        // 查询分页
        PageResult<AccessLogBO> result = systemLogService.getAccessLogPage(accessLogPageDTO);
        // 转换结果
        return CommonResult.success(AccessLogConvert.INSTANCE.convert(result));

    }


}
