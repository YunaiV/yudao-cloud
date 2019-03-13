package cn.iocoder.mall.pay.client;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.pay.dataobject.PayTransactionDO;
import cn.iocoder.mall.pay.dataobject.PayTransactionExtensionDO;

import java.util.Map;

public abstract class AbstractPaySDK {

    // extra 属性，用于支持不同支付平台的拓展字段。例如说，微信公众号支付，需要多传递一个 openid
    public abstract CommonResult<String> submitTransaction(PayTransactionDO transaction,
                                                           PayTransactionExtensionDO transactionExtension,
                                                           Map<String, Object> extra);

    // TODO 芋艿，理论来说不会出现解析失败的情况，先返回这个参数列。等后面封装支付宝和微信支付的时候，在看看。
    public abstract CommonResult<TransactionPaySuccessBO> parseTransactionPaySuccessParams(String params);

}