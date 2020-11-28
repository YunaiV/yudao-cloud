package cn.iocoder.mall.payservice.rpc.transaction;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.payservice.rpc.transaction.dto.PayTransactionCreateReqDTO;
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

}
