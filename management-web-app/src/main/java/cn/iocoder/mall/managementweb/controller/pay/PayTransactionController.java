package cn.iocoder.mall.managementweb.controller.pay;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.managementweb.controller.pay.vo.transaction.PayTransactionPageReqVO;
import cn.iocoder.mall.managementweb.controller.pay.vo.transaction.PayTransactionRespVO;
import cn.iocoder.mall.managementweb.service.pay.transaction.PayTransactionService;
import cn.iocoder.security.annotations.RequiresPermissions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static cn.iocoder.common.framework.vo.CommonResult.success;

@Api("支付交易单 API")
@RestController
@RequestMapping("/pay/transaction")
@Validated
@Slf4j
public class PayTransactionController {

    @Autowired
    private PayTransactionService payTransactionService;

    @GetMapping("/page")
    @RequiresPermissions("pay:transaction:page")
    @ApiOperation("获得交易支付单分页")
    public CommonResult<PageResult<PayTransactionRespVO>> pagePayTransaction(PayTransactionPageReqVO pageReqVO) {
        // 执行查询
        return success(payTransactionService.pagePayTransaction(pageReqVO));
    }

}
