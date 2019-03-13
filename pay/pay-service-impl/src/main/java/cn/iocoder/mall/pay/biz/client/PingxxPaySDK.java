package cn.iocoder.mall.pay.biz.client;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.pay.biz.dataobject.PayTransactionDO;
import cn.iocoder.mall.pay.biz.dataobject.PayTransactionExtensionDO;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.pingplusplus.Pingpp;
import com.pingplusplus.exception.*;
import com.pingplusplus.model.Charge;

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
    public CommonResult<String> submitTransaction(PayTransactionDO transaction, PayTransactionExtensionDO transactionExtension, Map<String, Object> extra) {
        Map<String, Object> reqObj = createChargeRequest(transaction, transactionExtension, extra);
        // 请求ping++
        try {
            Charge charge = Charge.create(reqObj);
            System.out.println(charge.toString());
            return CommonResult.success(charge.toString());
        } catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        } catch (ChannelException e) {
            e.printStackTrace();
        } catch (RateLimitException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public CommonResult<TransactionPaySuccessBO> parseTransactionPaySuccessParams(String params) {
        JSONObject paramsObj = JSON.parseObject(params);
        JSONObject chargeObj = paramsObj.getJSONObject("data").getJSONObject("object");
        TransactionPaySuccessBO transactionPaySuccessBO = new TransactionPaySuccessBO()
                .setTransactionCode(chargeObj.getString("order_no"))
                .setPaymentTime(new Date(chargeObj.getLong("time_paid") * 1000))
                .setTradeNo(chargeObj.getString("transaction_no"));
        return CommonResult.success(transactionPaySuccessBO);
    }

    private Map<String, Object> createChargeRequest(PayTransactionDO transaction, PayTransactionExtensionDO transactionExtension, Map<String, Object> extra) {
        // 计算支付渠道和支付额外参数
        String channel = "wx_pub"; // 因为 ping++ 是用来做模拟支付的渠道，所以这里强制就选择了 wx_pub 微信公众号支付
        extra = new HashMap<>(); // TODO 临时
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

    public static void main(String[] args) {
        PayTransactionDO transaction = new PayTransactionDO();
        transaction.setOrderSubject("测试商品");
        transaction.setOrderDescription("测试描述");
        transaction.setPrice(1);

        PayTransactionExtensionDO extension = new PayTransactionExtensionDO();
        extension.setTransactionCode(System.currentTimeMillis() + "");
        extension.setCreateIp("127.0.0.1");

        new PingxxPaySDK().submitTransaction(transaction, extension, null);
    }

}