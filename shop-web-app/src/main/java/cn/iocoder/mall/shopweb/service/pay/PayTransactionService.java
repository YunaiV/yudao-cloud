package cn.iocoder.mall.shopweb.service.pay;

import cn.iocoder.mall.payservice.rpc.transaction.dto.PayTransactionSubmitRespDTO;
import cn.iocoder.mall.shopweb.client.pay.PayTransactionClient;
import cn.iocoder.mall.shopweb.controller.pay.vo.transaction.PayTransactionRespVO;
import cn.iocoder.mall.shopweb.controller.pay.vo.transaction.PayTransactionSubmitReqVO;
import cn.iocoder.mall.shopweb.controller.pay.vo.transaction.PayTransactionSubmitRespVO;
import cn.iocoder.mall.shopweb.convert.pay.PayTransactionConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayTransactionService {

    @Autowired
    private PayTransactionClient payTransactionClient;

    public PayTransactionSubmitRespVO submitPayTransaction(PayTransactionSubmitReqVO submitReqVO, String ip) {
        PayTransactionSubmitRespDTO submitPayTransaction = payTransactionClient.submitPayTransaction(
                PayTransactionConvert.INSTANCE.convert(submitReqVO).setCreateIp(ip));
        return PayTransactionConvert.INSTANCE.convert(submitPayTransaction);
    }

    public PayTransactionRespVO getPayTransaction(Integer userId, String appId, String orderId) {
        return PayTransactionConvert.INSTANCE.convert(payTransactionClient.getPayTransaction(userId, appId, orderId));
    }

    public void updatePayTransactionSuccess(Integer payChannel, String params) {
        payTransactionClient.updatePayTransactionSuccess(payChannel, params);
    }

}
