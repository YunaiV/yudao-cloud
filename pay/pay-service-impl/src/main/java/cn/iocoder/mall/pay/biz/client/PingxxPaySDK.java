package cn.iocoder.mall.pay.biz.client;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.pay.biz.dataobject.PayRefundDO;
import cn.iocoder.mall.pay.biz.dataobject.PayTransactionDO;
import cn.iocoder.mall.pay.biz.dataobject.PayTransactionExtensionDO;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.pingplusplus.Pingpp;
import com.pingplusplus.exception.*;
import com.pingplusplus.model.Charge;
import com.pingplusplus.model.Refund;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

// TODO 代码略乱，后面重构下
public class PingxxPaySDK extends AbstractPaySDK {

    static {
        Pingpp.privateKeyPath = "/Users/yunai/Downloads/pingxx.pem";
        Pingpp.apiKey = "sk_test_8a9SGSXLKqX1ennjX9DenvbT";
    }

    @Override
    public CommonResult<String> submitTransaction(PayTransactionDO transaction,
                                                  PayTransactionExtensionDO transactionExtension,
                                                  Map<String, Object> extra) {
        Map<String, Object> reqObj = createChargeRequest(transaction, transactionExtension, extra);
        // 请求ping++
        try {
            Charge charge = Charge.create(reqObj);
            System.out.println(charge.toString());
            return CommonResult.success(charge.toString());
        } catch (AuthenticationException | InvalidRequestException |
                APIConnectionException | APIException |
                ChannelException | RateLimitException e) {
            e.printStackTrace();
            throw new RuntimeException(e); // TODO 芋艿，后续优化
        }
    }

    private static Map<String, Object> createChargeRequest(PayTransactionDO transaction,
                                                           PayTransactionExtensionDO transactionExtension,
                                                           Map<String, Object> extra) {
        // 计算支付渠道和支付额外参数
        String channel = "wx_pub"; // 因为 ping++ 是用来做模拟支付的渠道，所以这里强制就选择了 wx_pub 微信公众号支付
        extra = new HashMap<>(); // TODO 临时，后面用 extra
        extra.put("open_id", "just_for_test");
        // 生成支付对象
        Map<String, Object> reqObj = new HashMap<>();
        reqObj.put("subject", transaction.getOrderSubject());
        reqObj.put("body", transaction.getOrderDescription());
        reqObj.put("description", transaction.getOrderMemo());
        reqObj.put("amount", transaction.getPrice());
        reqObj.put("order_no", transactionExtension.getTransactionCode());
        reqObj.put("channel", channel);
        reqObj.put("currency", "cny");
        reqObj.put("client_ip", transactionExtension.getCreateIp());
        reqObj.put("app", ImmutableMap.of("id", "app_aTyfXDjrvzDSbLuz")); // TODO 写死先
        reqObj.put("extra", extra);
        return reqObj;
    }

    @Override
    public CommonResult<TransactionSuccessBO> parseTransactionSuccessParams(String params) {
        JSONObject paramsObj = JSON.parseObject(params);
        JSONObject chargeObj = paramsObj.getJSONObject("data").getJSONObject("object");
        TransactionSuccessBO transactionPaySuccessBO = new TransactionSuccessBO()
                .setTransactionCode(chargeObj.getString("order_no"))
                .setPaymentTime(new Date(chargeObj.getLong("time_paid") * 1000))
                .setTradeNo(chargeObj.getString("transaction_no"));
        return CommonResult.success(transactionPaySuccessBO);
    }

    @Override
    public CommonResult<String> submitRefund(PayRefundDO refund,
                                             PayTransactionExtensionDO transactionExtension,
                                             Map<String, Object> extra) {
        // 解析出 chargeId
        JSONObject paramsObj = JSON.parseObject(transactionExtension.getExtensionData());
        JSONObject chargeObj = paramsObj.getJSONObject("data").getJSONObject("object");
        String chargeId = chargeObj.getString("id");
        // 请求ping++
        Map<String, Object> reqObj = createRefundRequest(chargeId, refund.getOrderDescription(), refund.getPrice());
        try {
            Refund pingxxRefund = Refund.create(chargeId, reqObj);
            System.out.println(pingxxRefund.toString());
            return CommonResult.success(pingxxRefund.toString());
        } catch (AuthenticationException | InvalidRequestException |
                APIConnectionException | APIException |
                ChannelException | RateLimitException e) {
            e.printStackTrace();
            throw new RuntimeException(e); // TODO 芋艿，后续优化
        }
    }

    @Override
    public CommonResult<RefundSuccessBO> parseRefundSuccessParams(String params) {
        return null;
    }

    private Map<String, Object> createRefundRequest(String chargeId, String orderDescription, Integer price) {
        Map<String, Object> reqObj = new HashMap<>();
//        reqObj.put("CHARGE_ID", chargeId);
        reqObj.put("description", orderDescription);
        reqObj.put("amount", price);
        return reqObj;
    }

    public static void main(String[] args) {
        if (false) { // 测试支付请求
            PayTransactionDO transaction = new PayTransactionDO();
            transaction.setOrderSubject("测试商品");
            transaction.setOrderDescription("测试描述");
            transaction.setPrice(1);

            PayTransactionExtensionDO extension = new PayTransactionExtensionDO();
            extension.setTransactionCode(System.currentTimeMillis() + "");
            extension.setCreateIp("127.0.0.1");

            new PingxxPaySDK().submitTransaction(transaction, extension, null);
        }
        if (true) { // 测试退款请求
            PayRefundDO refund = new PayRefundDO().setPrice(1).setOrderDescription("测试描述");
            PayTransactionExtensionDO transactionExtension = new PayTransactionExtensionDO()
                    .setExtensionData("{\"id\":\"evt_400190423100354205607502\",\"created\":1555985033,\"livemode\":false,\"type\":\"charge.succeeded\",\"data\":{\"object\":{\"id\":\"ch_DCGyXTmDGuHKb1C0yTzjPOGC\",\"object\":\"charge\",\"created\":1555985032,\"livemode\":false,\"paid\":true,\"refunded\":false,\"reversed\":false,\"app\":\"app_aTyfXDjrvzDSbLuz\",\"channel\":\"wx_pub\",\"order_no\":\"20190423100352158401\",\"client_ip\":\"114.87.158.59\",\"amount\":10,\"amount_settle\":10,\"currency\":\"cny\",\"subject\":\"kafka 实战\",\"body\":\"测试描述\",\"extra\":{\"open_id\":\"just_for_test\",\"bank_type\":\"your bank type\"},\"time_paid\":1555985033,\"time_expire\":1555992232,\"time_settle\":null,\"transaction_no\":\"1244341374201904238178164740\",\"refunds\":{\"object\":\"list\",\"url\":\"/v1/charges/ch_DCGyXTmDGuHKb1C0yTzjPOGC/refunds\",\"has_more\":false,\"data\":[]},\"amount_refunded\":0,\"failure_code\":null,\"failure_msg\":null,\"metadata\":{},\"credential\":{},\"description\":\"测试备注\"}},\"object\":\"event\",\"request\":\"iar_4e9mPODW5ujPqLen5OOmvL8S\",\"pending_webhooks\":0}");

            new PingxxPaySDK().submitRefund(refund, transactionExtension, null);
        }
    }

}
