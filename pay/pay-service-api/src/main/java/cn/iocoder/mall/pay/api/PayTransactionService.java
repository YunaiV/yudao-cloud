package cn.iocoder.mall.pay.api;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.pay.api.bo.PayTransactionBO;
import cn.iocoder.mall.pay.api.dto.PayTransactionCreateDTO;

public interface PayTransactionService {

    CommonResult<PayTransactionBO> createTransaction(PayTransactionCreateDTO payTransactionCreateDTO);

    CommonResult submitTransaction(); // TODO 1. params 2. result

    CommonResult cancelTransaction(); // TODO 1. params 2. result

}