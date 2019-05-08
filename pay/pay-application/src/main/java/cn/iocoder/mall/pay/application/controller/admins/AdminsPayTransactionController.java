package cn.iocoder.mall.pay.application.controller.admins;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.pay.api.PayTransactionService;
import cn.iocoder.mall.pay.api.bo.PayTransactionPageBO;
import cn.iocoder.mall.pay.api.dto.PayTransactionPageDTO;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import static cn.iocoder.common.framework.vo.CommonResult.success;

@RestController
@RequestMapping("admins/transaction")
public class AdminsPayTransactionController {

    @Reference(validation = "true", version = "${dubbo.provider.PayTransactionService.version}")
    private PayTransactionService payTransactionService;

    @GetMapping("/page")
    public CommonResult<PayTransactionPageBO> page(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                                                   @RequestParam(value = "createBeginTime", required = false) Date createBeginTime,
                                                   @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                                                   @RequestParam(value = "createEndTime", required = false) Date createEndTime,
                                                   @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                                                   @RequestParam(value = "paymentBeginTime", required = false) Date paymentBeginTime,
                                                   @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                                                   @RequestParam(value = "paymentEndTime", required = false) Date paymentEndTime,
                                                   @RequestParam(value = "status", required = false) Integer status,
                                                   @RequestParam(value = "hasRefund", required = false) Boolean hasRefund,
                                                   @RequestParam(value = "payChannel", required = false) Integer payChannel,
                                                   @RequestParam(value = "orderSubject", required = false) String orderSubject,
                                                   @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                                                   @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        PayTransactionPageDTO payTransactionPageDTO = new PayTransactionPageDTO()
                .setCreateBeginTime(createBeginTime).setCreateEndTime(createEndTime)
                .setPaymentBeginTime(paymentBeginTime).setPaymentEndTime(paymentEndTime)
                .setStatus(status).setHasRefund(hasRefund)
                .setPayChannel(payChannel).setOrderSubject(orderSubject)
                .setPageNo(pageNo).setPageSize(pageSize);
        // 执行查询
        return success(payTransactionService.getTransactionPage(payTransactionPageDTO));
    }

}
