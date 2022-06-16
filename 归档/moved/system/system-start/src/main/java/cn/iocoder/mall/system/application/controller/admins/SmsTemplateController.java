package cn.iocoder.mall.system.application.controller.admins;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.system.api.SmsService;
import cn.iocoder.mall.system.api.bo.sms.PageSmsTemplateBO;
import cn.iocoder.mall.system.api.dto.sms.PageQuerySmsTemplateDTO;
import cn.iocoder.mall.system.application.po.sms.SmsTemplateAddPO;
import cn.iocoder.mall.system.application.po.sms.SmsTemplateUpdatePO;
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
public class SmsTemplateController {

    @Autowired
    private SmsService smsService;

    @GetMapping("page")
    @ApiOperation("短信模板-page")
    public CommonResult<PageSmsTemplateBO> pageSign(PageQuerySmsTemplateDTO pageQuerySmsTemplateDTO) {
         return CommonResult.success(smsService.pageSmsTemplate(pageQuerySmsTemplateDTO));
    }

    @PostMapping("add")
    @ApiOperation("短信模板-添加")
    public CommonResult addSign(SmsTemplateAddPO smsTemplateAddPO) {
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
    public CommonResult updateSign(SmsTemplateUpdatePO smsTemplateUpdatePO) {
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
