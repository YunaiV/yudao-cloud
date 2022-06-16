package cn.iocoder.mall.tradeservice.client.pay;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.payservice.rpc.transaction.PayTransactionFeign;
import cn.iocoder.mall.payservice.rpc.transaction.dto.PayTransactionCreateReqDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayTransactionClient {

    @Autowired
    private PayTransactionFeign payTransactionFeign;

    public Integer createPayTransaction(PayTransactionCreateReqDTO createReqDTO) {
        CommonResult<Integer> createPayTransactionResult = payTransactionFeign.createPayTransaction(createReqDTO);
        createPayTransactionResult.checkError();
        return createPayTransactionResult.getData();
    }

}
