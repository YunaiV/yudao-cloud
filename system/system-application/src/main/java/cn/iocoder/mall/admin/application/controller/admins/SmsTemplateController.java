package cn.iocoder.mall.admin.application.controller.admins;

import cn.iocoder.mall.admin.api.SmsService;
import cn.iocoder.mall.admin.api.bo.sms.PageSmsTemplateBO;
import cn.iocoder.mall.admin.api.dto.sms.PageQuerySmsSignDTO;
import cn.iocoder.mall.admin.api.dto.sms.PageQuerySmsTemplateDTO;
import cn.iocoder.mall.admin.application.po.sms.SmsTemplateAddPO;
import cn.iocoder.mall.admin.application.po.sms.SmsTemplateUpdatePO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 短信服务
 *
 * @author Sin
 * @time 2019/5/26 12:26 PM
 */
@RestController
@RequestMapping("sms/template")
@Api("短信服务(短信模板)")
public class SmsTemplateController {

    @Autowired
    private SmsService smsService;

    @PostMapping("page")
    @ApiOperation("短信模板-page")
    public PageSmsTemplateBO pageSign(PageQuerySmsTemplateDTO pageQuerySmsTemplateDTO) {
        return smsService.pageSmsTemplate(pageQuerySmsTemplateDTO);
    }

    @PostMapping("add")
    @ApiOperation("短信模板-添加")
    public void addSign(SmsTemplateAddPO smsTemplateAddPO) {
        smsService.addTemplate(
                smsTemplateAddPO.getSmsSignId(),
                smsTemplateAddPO.getTemplateCode(),
                smsTemplateAddPO.getTemplate(),
                smsTemplateAddPO.getPlatform(),
                smsTemplateAddPO.getSmsType());
    }

    @PostMapping("update")
    @ApiOperation("短信模板-更新")
    public void updateSign(SmsTemplateUpdatePO smsTemplateUpdatePO) {
        smsService.updateTemplate(
                smsTemplateUpdatePO.getId(),
                smsTemplateUpdatePO.getSmsSignId(),
                smsTemplateUpdatePO.getTemplateCode(),
                smsTemplateUpdatePO.getTemplate(),
                smsTemplateUpdatePO.getPlatform(),
                smsTemplateUpdatePO.getSmsType());
    }

    @PostMapping("deleted")
    @ApiOperation("短信模板-删除")
    public void deletedSign(@RequestParam("id") Integer id) {
        smsService.deleteTemplate(id);
    }
}
