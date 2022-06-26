package cn.iocoder.mall.shopweb.client.pay;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.payservice.rpc.transaction.PayTransactionFeign;
import cn.iocoder.mall.payservice.rpc.transaction.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PayTransactionClient {


    @Autowired
    private PayTransactionFeign payTransactionFeign;

    public PayTransactionRespDTO getPayTransaction(Integer userId, String appId, String orderId) {
        CommonResult<PayTransactionRespDTO> getPayTransactionResult = payTransactionFeign.getPayTransaction(new PayTransactionGetReqDTO()
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
        CommonResult<PayTransactionSubmitRespDTO> submitPayTransactionResult = payTransactionFeign.submitPayTransaction(submitReqDTO);
        submitPayTransactionResult.checkError();
        return submitPayTransactionResult.getData();
    }

    public void updatePayTransactionSuccess(Integer payChannel, String params) {
        CommonResult<Boolean> updatePayTransactionSuccessResult = payTransactionFeign.updatePayTransactionSuccess(
                new PayTransactionSuccessReqDTO().setPayChannel(payChannel).setParams(params));
        updatePayTransactionSuccessResult.checkError();
    }

}
