package cn.iocoder.mall.managementweb.controller.errorcode;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.managementweb.controller.errorcode.dto.ErrorCodeCreateDTO;
import cn.iocoder.mall.managementweb.controller.errorcode.dto.ErrorCodePageDTO;
import cn.iocoder.mall.managementweb.controller.errorcode.dto.ErrorCodeUpdateDTO;
import cn.iocoder.mall.managementweb.controller.errorcode.vo.ErrorCodeVO;
import cn.iocoder.mall.managementweb.manager.errorcode.ErrorCodeManager;
import cn.iocoder.security.annotations.RequiresPermissions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static cn.iocoder.common.framework.vo.CommonResult.success;

/**
* 错误码 Controller
*/
@RestController
@RequestMapping("/error-code")
@Api(tags = "错误码")
@Validated
public class ErrorCodeController {

    @Autowired
    private ErrorCodeManager errorCodeManager;

    @PostMapping("/create")
    @ApiOperation("创建错误码")
    @RequiresPermissions("system:error-code:create")
    public CommonResult<Integer> createErrorCode(@Valid ErrorCodeCreateDTO createDTO) {
        return success(errorCodeManager.createErrorCode(createDTO));
    }

    @PostMapping("/update")
    @ApiOperation("更新错误码")
    @RequiresPermissions("system:error-code:update")
    public CommonResult<Boolean> updateErrorCode(@Valid ErrorCodeUpdateDTO updateDTO) {
        errorCodeManager.updateErrorCode(updateDTO);
        return success(true);
    }

    @PostMapping("/delete")
    @ApiOperation("删除错误码")
    @ApiImplicitParam(name = "errorCodeId", value = "错误码编号", required = true)
    @RequiresPermissions("system:error-code:delete")
    public CommonResult<Boolean> deleteErrorCode(@RequestParam("errorCodeId") Integer errorCodeId) {
        errorCodeManager.deleteErrorCode(errorCodeId);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得错误码")
    @ApiImplicitParam(name = "errorCodeId", value = "错误码编号", required = true)
    @RequiresPermissions("system:error-code:page")
    public CommonResult<ErrorCodeVO> getErrorCode(@RequestParam("errorCodeId") Integer errorCodeId) {
        return success(errorCodeManager.getErrorCode(errorCodeId));
    }

    @GetMapping("/page")
    @ApiOperation("获得错误码分页")
    @RequiresPermissions("system:error-code:page")
    public CommonResult<PageResult<ErrorCodeVO>> pageErrorCode(ErrorCodePageDTO pageDTO) {
        return success(errorCodeManager.pageErrorCode(pageDTO));
    }

}
