package cn.iocoder.mall.payservice.client.thirdpay;

import cn.iocoder.mall.payservice.enums.PayChannelEnum;

import java.util.HashMap;
import java.util.Map;

public class ThirdPayClientFactory {

    private static Map<Integer, AbstractThirdPayClient> CLIENTS = new HashMap<>();

    static {
        CLIENTS.put(PayChannelEnum.PINGXX.getId(), new PingxxThirdPayClient());
    }

    public static AbstractThirdPayClient getThirdPayClient(Integer payChannel) {
        AbstractThirdPayClient client = CLIENTS.get(payChannel);
        if (client == null) {
            throw new NullPointerException("找不到合适的 ThirdPayClient ：" + payChannel);
        }
        return client;
    }

}
