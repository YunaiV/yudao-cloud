package cn.iocoder.mall.shopweb.controller.pay;

import cn.iocoder.common.framework.util.HttpUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.payservice.enums.PayChannelEnum;
import cn.iocoder.mall.security.user.core.context.UserSecurityContextHolder;
import cn.iocoder.mall.shopweb.controller.pay.vo.transaction.PayTransactionRespVO;
import cn.iocoder.mall.shopweb.controller.pay.vo.transaction.PayTransactionSubmitReqVO;
import cn.iocoder.mall.shopweb.controller.pay.vo.transaction.PayTransactionSubmitRespVO;
import cn.iocoder.mall.shopweb.service.pay.PayTransactionService;
import cn.iocoder.security.annotations.RequiresAuthenticate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

import static cn.iocoder.common.framework.vo.CommonResult.success;

@Api("支付交易 API")
@RestController
@RequestMapping("/pay/transaction")
@Validated
@Slf4j
public class PayTransactionController {

    @Autowired
    private PayTransactionService payTransactionService;

    // TODO 芋艿：这个 API 定义可能不太太合适，应该改成支付交易单号
    @GetMapping("/get")
    @ApiOperation("获得支付交易")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appId", required = true, value = "应用编号", example = "POd4RC6a"),
            @ApiImplicitParam(name = "orderId", required = true, value = "订单号", example = "1024"),
    })
    @RequiresAuthenticate
    public CommonResult<PayTransactionRespVO> getPayTransaction(@RequestParam("appId") String appId,
                                                                @RequestParam("orderId") String orderId) {
        return success(payTransactionService.getPayTransaction(UserSecurityContextHolder.getUserId(), appId, orderId));
    }

    // TODO 芋艿：这个 API 定义可能不太太合适，应该改成支付交易单号
    @PostMapping("/submit")
    @ApiOperation("提交支付交易")
    @RequiresAuthenticate
    public CommonResult<PayTransactionSubmitRespVO> submitPayTransaction(HttpServletRequest request,
                                                                         PayTransactionSubmitReqVO submitReqVO) {
        return success(payTransactionService.submitPayTransaction(submitReqVO, HttpUtil.getIp(request)));
    }

    @PostMapping(value = "pingxx_pay_success", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Pingxx 支付成功回调")
//    @GetMapping(value = "pingxx_pay_success")
    public String updatePayTransactionSuccess(HttpServletRequest request) throws IOException {
        log.info("[pingxxPaySuccess][被回调]");
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
        payTransactionService.updatePayTransactionSuccess(PayChannelEnum.PINGXX.getId(), sb.toString());
        return "success";
    }

}
