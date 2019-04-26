package cn.iocoder.mall.pay.biz.client;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.pay.biz.dataobject.PayRefundDO;
import cn.iocoder.mall.pay.biz.dataobject.PayTransactionDO;
import cn.iocoder.mall.pay.biz.dataobject.PayTransactionExtensionDO;

import java.util.Map;

public abstract class AbstractPaySDK {

    /**
     * 提交支付请求给支付平台，并返回请求结果
     *
     * @param transaction 支付交易数据
     * @param transactionExtension 交易扩展数据
     * @param extra 额外参数。用于支持不同支付平台的拓展字段。例如说，微信公众号支付，需要多传递一个 openid
     * @return 请求结果
     */
    public abstract CommonResult<String> submitTransaction(PayTransactionDO transaction,
                                                           PayTransactionExtensionDO transactionExtension,
                                                           Map<String, Object> extra);

    /**
     * 解析支付成功回调的参数，返回 TransactionSuccessBO 对象
     *
     * @param params 回调的参数
     * @return 解析结果
     */
    // TODO 芋艿，理论来说不会出现解析失败的情况，先返回这个参数列。等后面封装支付宝和微信支付的时候，在看看。
    public abstract CommonResult<TransactionSuccessBO> parseTransactionSuccessParams(String params);

    /**
     * 提交退款请求给支付平台，并返回请求结果
     *
     * @param refund 退款数据
     * @param transactionExtension 交易扩展数据
     * @param extra 额外参数。用于支持不同支付平台的拓展字段。
     * @return 请求结果
     */
    public abstract CommonResult<String> submitRefund(PayRefundDO refund,
                                                      PayTransactionExtensionDO transactionExtension,
                                                      Map<String, Object> extra);

    /**
     * 解析退款成功回调的参数，返回 RefundSuccessBO 对象
     *
     * @param params 回调的参数
     * @return 解析结果
     */
    // TODO 芋艿，理论来说不会出现解析失败的情况，先返回这个参数列。等后面封装支付宝和微信支付的时候，在看看。
    public abstract CommonResult<RefundSuccessBO> parseRefundSuccessParams(String params);

}
