package cn.iocoder.mall.pay.application.controller.users;

import cn.iocoder.common.framework.util.HttpUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.pay.api.PayTransactionService;
import cn.iocoder.mall.pay.api.bo.PayTransactionSubmitBO;
import cn.iocoder.mall.pay.api.constant.PayChannelEnum;
import cn.iocoder.mall.pay.api.dto.PayTransactionSubmitDTO;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

@RestController
@RequestMapping("users/transaction") // TODO 芋艿，理论来说，是用户无关的。这里先酱紫先~
public class PayTransactionController {

    @Reference(validation = "true")
    private PayTransactionService payService;

    @PostMapping("/submit") // TODO api 注释
    // TODO result 后面改下
    public CommonResult<PayTransactionSubmitBO> submit(HttpServletRequest request,
                                                       @RequestParam("appId") String appId,
                                                       @RequestParam("orderId") String orderId,
                                                       @RequestParam("payChannel") Integer payChannel) {
        PayTransactionSubmitDTO payTransactionSubmitDTO = new PayTransactionSubmitDTO()
                .setAppId(appId).setOrderId(orderId).setPayChannel(payChannel)
                .setCreateIp(HttpUtil.getIp(request));
        // 提交支付提交
        return payService.submitTransaction(payTransactionSubmitDTO);
    }

    @PostMapping(value = "pingxx_pay_success", consumes = MediaType.APPLICATION_JSON_VALUE)
//    @GetMapping(value = "pingxx_pay_success")
    public String pingxxSuccess(HttpServletRequest request) throws IOException {
        // 读取 webhook
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = request.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }

//        JSONObject bodyObj = JSON.parseObject(sb.toString());
//        bodyObj.put("webhookId", bodyObj.remove("id"));
//        String body = bodyObj.toString();
        payService.updateTransactionPaySuccess(PayChannelEnum.PINGXX.getId(), sb.toString());
        return "";
    }

}