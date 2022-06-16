package cn.iocoder.mall.system.rest.controller.sms;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.system.biz.bo.smsSign.ListSmsSignBO;
import cn.iocoder.mall.system.biz.dto.smsSign.ListSmsSignDTO;
import cn.iocoder.mall.system.biz.service.sms.SmsService;
import cn.iocoder.mall.system.rest.convert.sms.AdminsSmsConvert;
import cn.iocoder.mall.system.rest.request.sms.AddSignRequest;
import cn.iocoder.mall.system.rest.request.sms.UpdateSignRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 短信服务
 *
 * @author Sin
 * @time 2019/5/26 12:26 PM
 */
@RestController
@RequestMapping("admins/sms/sign")
@Api("短信服务(签名)")
public class AdminsSmsSignController {

    @Autowired
    private SmsService smsService;

    @GetMapping("page")
    @ApiOperation("签名-page")
    public CommonResult<PageResult<ListSmsSignBO>> pageSign(@Validated ListSmsSignDTO listSmsSignDTO) {
        return CommonResult.success(smsService.listSmsSign(listSmsSignDTO));
    }

    @PostMapping("add")
    @ApiOperation("签名-添加")
    public CommonResult<?> addSign(@RequestBody AddSignRequest addSignRequest) {
        smsService.addSign(AdminsSmsConvert.INSTANCE.convert(addSignRequest));
        return CommonResult.success(null);
    }

    @PutMapping("update")
    @ApiOperation("签名-更新")
    public CommonResult<?> updateSign(@RequestBody UpdateSignRequest updateSignRequest) {
        smsService.updateSign(AdminsSmsConvert.INSTANCE.convert(updateSignRequest));
        return CommonResult.success(null);
    }

    @DeleteMapping("deleted")
    @ApiOperation("签名-删除")
    public CommonResult<?> deletedSign(@RequestParam("id") Integer id) {
        smsService.deleteSign(id);
        return CommonResult.success(null);
    }
}
