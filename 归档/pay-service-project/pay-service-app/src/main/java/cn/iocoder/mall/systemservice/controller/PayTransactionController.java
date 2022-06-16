package cn.iocoder.mall.systemservice.controller;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.payservice.rpc.transaction.dto.*;
import cn.iocoder.mall.payservice.service.transaction.PayTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static cn.iocoder.common.framework.vo.CommonResult.success;

/**
 * Title:
 * Description:
 *
 * @author zhuyang
 * @version 1.0 2021/10/9
 */
@RestController
@RequestMapping("/pay/transaction")
public class PayTransactionController {
    @Autowired
    private PayTransactionService payTransactionService;

    /**
     * 创建支付交易单
     *
     * @param createReqDTO 创建信息
     * @return 支付交易单号
     */
    @PostMapping("createPayTransaction")
    CommonResult<Integer> createPayTransaction(@RequestBody PayTransactionCreateReqDTO createReqDTO){
        return success(payTransactionService.createPayTransaction(createReqDTO));
    }

    /**
     * 提交支付交易单
     *
     * @param submitReqDTO 提交信息
     * @return 提交响应，包含三方支付的响应
     */
    @PostMapping("submitPayTransaction")
    CommonResult<PayTransactionSubmitRespDTO> submitPayTransaction(@RequestBody PayTransactionSubmitReqDTO submitReqDTO){
        return success(payTransactionService.submitPayTransaction(submitReqDTO));
    }

    /**
     * 获得当支付交易单
     *
     * @param getReqDTO 获得条件
     * @return 支付交易单
     */
    @PostMapping("getPayTransaction")
    CommonResult<PayTransactionRespDTO> getPayTransaction(@RequestBody PayTransactionGetReqDTO getReqDTO){
        return success(payTransactionService.getPayTransaction(getReqDTO));}

    /**
     * 更新交易支付成功
     *
     * @param successReqDTO 支付成功信息
     * @return 是否成功
     */
    @PostMapping("updatePayTransactionSuccess")
    CommonResult<Boolean> updatePayTransactionSuccess(@RequestBody PayTransactionSuccessReqDTO successReqDTO){
        return success(payTransactionService.updateTransactionPaySuccess(successReqDTO.getPayChannel(),
            successReqDTO.getParams()));}

    /**
     * 获得交易支付单分页
     *
     * @param pageReqDTO 分页条件
     * @return 交易支付单分页
     */
    @PostMapping("pagePayTransaction")
    CommonResult<PageResult<PayTransactionRespDTO>> pagePayTransaction(@RequestBody  PayTransactionPageReqDTO pageReqDTO){
        return success(payTransactionService.pagePayTransaction(pageReqDTO));}

}
