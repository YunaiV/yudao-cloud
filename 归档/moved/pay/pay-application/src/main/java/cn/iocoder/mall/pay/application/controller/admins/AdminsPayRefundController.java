package cn.iocoder.mall.pay.application.controller.admins;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.pay.api.PayRefundService;
import cn.iocoder.mall.pay.api.PayTransactionService;
import cn.iocoder.mall.pay.api.bo.refund.PayRefundBO;
import cn.iocoder.mall.pay.api.bo.refund.PayRefundPageBO;
import cn.iocoder.mall.pay.api.bo.transaction.PayTransactionBO;
import cn.iocoder.mall.pay.api.dto.refund.PayRefundPageDTO;
import cn.iocoder.mall.pay.application.convert.PayRefundConvert;
import cn.iocoder.mall.pay.application.vo.admins.AdminsPayRefundPageVO;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

import static cn.iocoder.common.framework.vo.CommonResult.success;

@RestController
@RequestMapping("admins/refund")
public class AdminsPayRefundController {

    @Reference(validation = "true", version = "${dubbo.provider.PayRefundService.version}")
    private PayRefundService payRefundService;
    @Reference(validation = "true", version = "${dubbo.provider.PayTransactionService.version}")
    private PayTransactionService payTransactionService;

    @GetMapping("/page")
    public CommonResult<AdminsPayRefundPageVO> page(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                                                   @RequestParam(value = "createBeginTime", required = false) Date createBeginTime,
                                                    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                                                   @RequestParam(value = "createEndTime", required = false) Date createEndTime,
                                                    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                                                   @RequestParam(value = "finishBeginTime", required = false) Date finishBeginTime,
                                                    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                                                   @RequestParam(value = "finishEndTime", required = false) Date finishEndTime,
                                                    @RequestParam(value = "status", required = false) Integer status,
                                                    @RequestParam(value = "payChannel", required = false) Integer payChannel,
                                                    @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                                                    @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        PayRefundPageDTO payRefundPageDTO = new PayRefundPageDTO()
                .setCreateBeginTime(createBeginTime).setCreateEndTime(createEndTime)
                .setFinishBeginTime(finishBeginTime).setFinishEndTime(finishEndTime)
                .setStatus(status).setPayChannel(payChannel)
                .setPageNo(pageNo).setPageSize(pageSize);
        // 执行查询
        PayRefundPageBO refundBOPage = payRefundService.getRefundPage(payRefundPageDTO);
        AdminsPayRefundPageVO result = new AdminsPayRefundPageVO()
                .setList(PayRefundConvert.INSTANCE.convertList(refundBOPage.getList()))
                .setTotal(refundBOPage.getTotal());
        if (result.getList().isEmpty()) {
            return success(result);
        }
        // 拼接结果
        Map<Integer, PayTransactionBO> transactionMap = payTransactionService.getTransactionList(
                result.getList().stream().map(PayRefundBO::getTransactionId).collect(Collectors.toSet()))
                .stream().collect(Collectors.toMap(PayTransactionBO::getId, transaction -> transaction));
        result.getList().forEach(refund -> refund.setTransaction(transactionMap.get(refund.getTransactionId())));
        return success(result);
    }

}
