package cn.iocoder.mall.managementweb.client.pay.transaction;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.payservice.rpc.transaction.PayTransactionRpc;
import cn.iocoder.mall.payservice.rpc.transaction.dto.PayTransactionPageReqDTO;
import cn.iocoder.mall.payservice.rpc.transaction.dto.PayTransactionRespDTO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

@Service
public class PayTransactionClient {

    @DubboReference(version = "${dubbo.consumer.PayTransactionRpc.version}")
    private PayTransactionRpc payTransactionRpc;

    public PageResult<PayTransactionRespDTO> pagePayTransaction(PayTransactionPageReqDTO pageReqDTO) {
        CommonResult<PageResult<PayTransactionRespDTO>> pagePayTransactionResult = payTransactionRpc.pagePayTransaction(pageReqDTO);
        pagePayTransactionResult.checkError();
        return pagePayTransactionResult.getData();
    }

}
