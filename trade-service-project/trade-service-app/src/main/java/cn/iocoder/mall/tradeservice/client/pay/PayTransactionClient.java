package cn.iocoder.mall.tradeservice.client.pay;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.payservice.rpc.transaction.PayTransactionRpc;
import cn.iocoder.mall.payservice.rpc.transaction.dto.PayTransactionCreateReqDTO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

@Service
public class PayTransactionClient {

    @DubboReference(version = "${dubbo.consumer.PayTransactionRpc.version}")
    private PayTransactionRpc payTransactionRpc;

    public Integer createPayTransaction(PayTransactionCreateReqDTO createReqDTO) {
        CommonResult<Integer> createPayTransactionResult = payTransactionRpc.createPayTransaction(createReqDTO);
        createPayTransactionResult.checkError();
        return createPayTransactionResult.getData();
    }

}
