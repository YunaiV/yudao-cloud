package cn.iocoder.mall.managementweb.service.pay.transaction;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.managementweb.client.pay.transaction.PayTransactionClient;
import cn.iocoder.mall.managementweb.controller.pay.vo.transaction.PayTransactionPageReqVO;
import cn.iocoder.mall.managementweb.controller.pay.vo.transaction.PayTransactionRespVO;
import cn.iocoder.mall.managementweb.convert.pay.transaction.PayTransactionConvert;
import cn.iocoder.mall.payservice.rpc.transaction.dto.PayTransactionRespDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayTransactionService {

    @Autowired
    private PayTransactionClient payTransactionClient;

    public PageResult<PayTransactionRespVO> pagePayTransaction(PayTransactionPageReqVO pageReqVO) {
        PageResult<PayTransactionRespDTO> payTransactionPage = payTransactionClient.pagePayTransaction(
                PayTransactionConvert.INSTANCE.convert(pageReqVO));
        return PayTransactionConvert.INSTANCE.convertPage(payTransactionPage);
    }

}
