package cn.iocoder.mall.system.rest.controller.sms;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.system.biz.bo.smsTemplate.ListSmsTemplateBO;
import cn.iocoder.mall.system.biz.service.sms.SmsService;
import cn.iocoder.mall.system.rest.convert.sms.AdminsSmsConvert;
import cn.iocoder.mall.system.rest.request.sms.AddSmsTemplateRequest;
import cn.iocoder.mall.system.rest.request.sms.ListSmsTemplateRequest;
import cn.iocoder.mall.system.rest.request.sms.UpdateSmsTemplateRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 短信服务
 *
 * @author Sin
 * @time 2019/5/26 12:26 PM
 */
@RestController
@RequestMapping("admins/sms/template")
@Api("短信服务(短信模板)")
public class AdminsSmsTemplateController {

    @Autowired
    private SmsService smsService;

    @PostMapping("page")
    @ApiOperation("短信模板-page")
    public CommonResult<PageResult<ListSmsTemplateBO>> pageSign(@RequestBody ListSmsTemplateRequest request) {
         return CommonResult.success(smsService.listSmsTemplate(AdminsSmsConvert.INSTANCE.convert(request)));
    }

    @PostMapping("add")
    @ApiOperation("短信模板-添加")
    public CommonResult addSign(@RequestBody AddSmsTemplateRequest smsTemplateAddPO) {
        smsService.addTemplate(
                smsTemplateAddPO.getSmsSignId(),
                smsTemplateAddPO.getTemplateCode(),
                smsTemplateAddPO.getTemplate(),
                smsTemplateAddPO.getPlatform(),
                smsTemplateAddPO.getSmsType());
        return CommonResult.success(null);
    }

    @PutMapping("update")
    @ApiOperation("短信模板-更新")
    public CommonResult updateSign(@RequestBody UpdateSmsTemplateRequest smsTemplateUpdatePO) {
        smsService.updateTemplate(
                smsTemplateUpdatePO.getId(),
                smsTemplateUpdatePO.getSmsSignId(),
                smsTemplateUpdatePO.getTemplateCode(),
                smsTemplateUpdatePO.getTemplate(),
                smsTemplateUpdatePO.getPlatform(),
                smsTemplateUpdatePO.getSmsType());
        return CommonResult.success(null);
    }

    @DeleteMapping("deleted")
    @ApiOperation("短信模板-删除")
    public CommonResult deletedSign(@RequestParam("id") Integer id) {
        smsService.deleteTemplate(id);
        return CommonResult.success(null);
    }
}
