package cn.iocoder.mall.pay.api;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.pay.api.bo.PayTransactionBO;
import cn.iocoder.mall.pay.api.bo.PayTransactionSubmitBO;
import cn.iocoder.mall.pay.api.dto.PayTransactionCreateDTO;
import cn.iocoder.mall.pay.api.dto.PayTransactionSubmitDTO;

public interface PayTransactionService {

    CommonResult<PayTransactionBO> createTransaction(PayTransactionCreateDTO payTransactionCreateDTO);

    CommonResult<PayTransactionSubmitBO> submitTransaction(PayTransactionSubmitDTO payTransactionSubmitDTO);

    CommonResult cancelTransaction(); // TODO 1. params 2. result

}