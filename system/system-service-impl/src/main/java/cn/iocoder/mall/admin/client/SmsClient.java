package cn.iocoder.mall.admin.client;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

/**
 * 短信平台
 *
 * @author Sin
 * @time 2019/5/16 6:33 PM
 */
public interface SmsClient {

    @Data
    @Accessors(chain = true)
    class SendResult {

        private Boolean isSuccess;

        private Integer code;

        private String message;
    }

    /**
     * 短信发送 - 单个
     *
     * @return
     */
    SendResult singleSend(String mobile, String sign, String template, Map<String, String> params);

    /**
     * 短信发送 - 批量
     *
     * @return
     */
    SendResult batchSend(List<String> mobileList, String sign, String template, Map<String, String> params);
}
