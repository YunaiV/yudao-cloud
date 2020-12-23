package cn.iocoder.mall.shopweb.client.pay;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.payservice.rpc.transaction.PayTransactionRpc;
import cn.iocoder.mall.payservice.rpc.transaction.dto.*;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PayTransactionClient {

    @DubboReference(version = "${dubbo.consumer.PayTransactionRpc.version}")
    private PayTransactionRpc payTransactionRpc;

    public PayTransactionRespDTO getPayTransaction(Integer userId, String appId, String orderId) {
        CommonResult<PayTransactionRespDTO> getPayTransactionResult = payTransactionRpc.getPayTransaction(new PayTransactionGetReqDTO()
            .setAppId(appId).setOrderId(orderId));
        getPayTransactionResult.checkError();
        if (getPayTransactionResult.getData() == null) {
            return null;
        }
        // 如果用户编号不匹配，则返回 null
        return Objects.equals(getPayTransactionResult.getData().getUserId(), userId) ?
                getPayTransactionResult.getData() : null;
    }

    public PayTransactionSubmitRespDTO submitPayTransaction(PayTransactionSubmitReqDTO submitReqDTO) {
        CommonResult<PayTransactionSubmitRespDTO> submitPayTransactionResult = payTransactionRpc.submitPayTransaction(submitReqDTO);
        submitPayTransactionResult.checkError();
        return submitPayTransactionResult.getData();
    }

    public void updatePayTransactionSuccess(Integer payChannel, String params) {
        CommonResult<Boolean> updatePayTransactionSuccessResult = payTransactionRpc.updatePayTransactionSuccess(
                new PayTransactionSuccessReqDTO().setPayChannel(payChannel).setParams(params));
        updatePayTransactionSuccessResult.checkError();
    }

}
