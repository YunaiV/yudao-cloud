package cn.iocoder.mall.admin.application.controller.admins;

import cn.iocoder.mall.admin.api.SmsService;
import cn.iocoder.mall.admin.api.dto.sms.PageQuerySmsSignDTO;
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
@RequestMapping("sms/sign")
@Api("短信服务(签名)")
public class SmsSignController {

    @Autowired
    private SmsService smsService;

    @PostMapping("page")
    @ApiOperation("签名-page")
    public void pageSign(PageQuerySmsSignDTO querySmsSignDTO) {
        smsService.pageSmsSign(querySmsSignDTO);
    }

    @PostMapping("add")
    @ApiOperation("签名-添加")
    public void addSign(@RequestParam("sign") String sign,
                        @RequestParam("platform") Integer platform) {
        smsService.addSign(sign, platform);
    }

    @PostMapping("update")
    @ApiOperation("签名-更新")
    public void updateSign(@RequestParam("id") Integer id,
                           @RequestParam("sign") String sign,
                           @RequestParam("platform") Integer platform) {
        smsService.updateSign(id, sign, platform);
    }

    @PostMapping("deleted")
    @ApiOperation("签名-删除")
    public void deletedSign(@RequestParam("id") Integer id) {
        smsService.deleteSign(id);
    }
}
