package cn.iocoder.mall.system.biz.service.sms;

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

    /**
     * 短信发送 - 单个
     *
     * @param mobile 手机号
     * @param sign 签名
     * @param templateCode 短信模板code
     * @param template 短信模板
     * @param templateParams 短信模板 params
     * @return 发送后信息
     */
    SendResult singleSend(String mobile, String sign, String templateCode,
                          String template, Map<String, String> templateParams);

    /**
     * 短信发送 - 批量
     *
     * @param mobileList 手机号
     * @param sign 签名
     * @param templateCode 短信模板 code
     * @param template 短信模板
     * @param templateParams 短信模板params
     * @return 发送后信息
     */
    SendResult batchSend(List<String> mobileList, String sign, String templateCode,
                         String template, Map<String, String> templateParams);

    @Data
    @Accessors(chain = true)
    class SendResult {

        public static final int SUCCESS_CODE = 0;
        public static final int ERROR_CODE = 1;
        public static final String SUCCESS_MESSAGE = "SUCCESS";

        /**
         * 错误码
         */
        private Integer code;
        /**
         * 错误信息
         */
        private String message;
        /**
         * 是否成功
         */
        private Boolean isSuccess;
    }
}
