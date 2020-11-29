package cn.iocoder.mall.pay.api;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.pay.api.bo.transaction.PayTransactionBO;
import cn.iocoder.mall.pay.api.bo.transaction.PayTransactionPageBO;
import cn.iocoder.mall.pay.api.bo.transaction.PayTransactionSubmitBO;
import cn.iocoder.mall.pay.api.dto.transaction.PayTransactionCreateDTO;
import cn.iocoder.mall.pay.api.dto.transaction.PayTransactionGetDTO;
import cn.iocoder.mall.pay.api.dto.transaction.PayTransactionPageDTO;
import cn.iocoder.mall.pay.api.dto.transaction.PayTransactionSubmitDTO;

import java.util.Collection;
import java.util.List;

public interface PayTransactionService {

    List<PayTransactionBO> getTransactionList(Collection<Integer> ids);

    PayTransactionPageBO getTransactionPage(PayTransactionPageDTO payTransactionPageDTO);

    CommonResult cancelTransaction(); // TODO 1. params 2. result

}
