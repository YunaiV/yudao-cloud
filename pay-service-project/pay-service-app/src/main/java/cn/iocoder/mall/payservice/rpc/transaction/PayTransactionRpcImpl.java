package cn.iocoder.mall.payservice.rpc.transaction;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.payservice.rpc.transaction.dto.*;
import cn.iocoder.mall.payservice.service.transaction.PayTransactionService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import static cn.iocoder.common.framework.vo.CommonResult.success;

@DubboService
public class PayTransactionRpcImpl implements PayTransactionRpc {

    @Autowired
    private PayTransactionService payTransactionService;

    @Override
    public CommonResult<Integer> createPayTransaction(PayTransactionCreateReqDTO createReqDTO) {
        return success(payTransactionService.createPayTransaction(createReqDTO));
    }

    @Override
    public CommonResult<PayTransactionSubmitRespDTO> submitPayTransaction(PayTransactionSubmitReqDTO submitReqDTO) {
        return success(payTransactionService.submitPayTransaction(submitReqDTO));
    }

    @Override
    public CommonResult<PayTransactionRespDTO> getPayTransaction(PayTransactionGetReqDTO getReqDTO) {
        return success(payTransactionService.getPayTransaction(getReqDTO));
    }

    @Override
    public CommonResult<Boolean> updatePayTransactionSuccess(PayTransactionSuccessReqDTO successReqDTO) {
        return success(payTransactionService.updateTransactionPaySuccess(successReqDTO.getPayChannel(),
                successReqDTO.getParams()));
    }

    @Override
    public CommonResult<PageResult<PayTransactionRespDTO>> pagePayTransaction(PayTransactionPageReqDTO pageReqDTO) {
        return success(payTransactionService.pagePayTransaction(pageReqDTO));
    }

}
