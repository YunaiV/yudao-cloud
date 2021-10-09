package cn.iocoder.mall.managementweb.client.pay.transaction;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.payservice.rpc.transaction.PayTransactionFeign;
import cn.iocoder.mall.payservice.rpc.transaction.dto.PayTransactionPageReqDTO;
import cn.iocoder.mall.payservice.rpc.transaction.dto.PayTransactionRespDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayTransactionClient {


    @Autowired
    private PayTransactionFeign payTransactionFeign;
    public PageResult<PayTransactionRespDTO> pagePayTransaction(PayTransactionPageReqDTO pageReqDTO) {
        CommonResult<PageResult<PayTransactionRespDTO>> pagePayTransactionResult = payTransactionFeign.pagePayTransaction(pageReqDTO);
        pagePayTransactionResult.checkError();
        return pagePayTransactionResult.getData();
    }

}
