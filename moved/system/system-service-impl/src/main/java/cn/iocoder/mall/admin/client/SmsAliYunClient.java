package cn.iocoder.mall.admin.client;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 短信 AliYun client
 *
 * @author Sin
 * @time 2019/5/25 12:28 PM
 */
@Component
public class SmsAliYunClient implements SmsClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(SmsAliYunClient.class);

    private static final String DOMAIN = "dysmsapi.aliyuncs.com";
    private static final String SUCCESS_CODE = "OK";
    private static final String SUCCESS_MESSAGE = "OK";
    /**
     * 阿里云短信 - 批量推送最大数 500，支持 1000
     */
    private static final int MAX_BATCH_SIZE = 500;

    @Value("${sms.aliYun.accessKeyId?:'default_value'}")
    private String accessKeyId;
    @Value("${sms.aliYun.accessSecret?:'default_value'}")
    private String accessSecret;

    @Data
    @Accessors(chain = true)
    public static class Result {
        /**
         * 发送回执ID，可根据该ID在接口QuerySendDetails中查询具体的发送状态。
         */
        private String BizId;
        /**
         * 请求状态码。
         *
         * - OK 蔡成功
         */
        private String Code;
        /**
         * 状态码的描述。
         */
        private String Message;
        /**
         * 请求ID。
         */
        private String RequestId;
    }

    @Override
    public SendResult singleSend(String mobile, String sign, String templateCode,
                                 String template, Map<String, String> templateParams) {
        // params
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain(DOMAIN);
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("PhoneNumbers", mobile);
        request.putQueryParameter("SignName", sign);
        request.putQueryParameter("TemplateCode", templateCode);
        request.putQueryParameter("TemplateParam", JSON.toJSONString(templateParams));

        // 发送请求
        return doSend(request);
    }

    @Override
    public SendResult batchSend(List<String> mobileList, String sign, String templateCode,
                                String template, Map<String, String> templateParams) {

        // 最大发送数为 1000，我们设置为 500 个, 分段发送
        int maxSendSize = MAX_BATCH_SIZE;
        int maxSendSizeCount = mobileList.size() % maxSendSize == 0
                ? mobileList.size() / maxSendSize
                : mobileList.size() / maxSendSize + 1;

        SendResult sendResult = null;
        for (int i = 0; i < maxSendSizeCount; i++) {
            // 分批发送
            List<String> batchSendMobile = mobileList
                    .subList(i * maxSendSize, (i + 1) * maxSendSize);

            // params
            CommonRequest request = new CommonRequest();
            request.setMethod(MethodType.POST);
            request.setDomain(DOMAIN);
            request.setVersion("2017-05-25");
            request.setAction("SendBatchSms");
            request.putQueryParameter("PhoneNumberJson", JSON.toJSONString(batchSendMobile));
            request.putQueryParameter("SignNameJson", JSON.toJSONString(Collections.singletonList(sign)));
            request.putQueryParameter("TemplateCode", templateCode);
            request.putQueryParameter("TemplateParamJson", JSON.toJSONString(Collections.singletonList(templateParams)));

            // 发送请求
            sendResult = doSend(request);
        }
        return sendResult;
    }

    private SendResult doSend(CommonRequest request) {
        // 获取 client
        IAcsClient client = getClient();
        try {
            CommonResponse response = client.getCommonResponse(request);
            Result result = JSON.parseObject(response.getData(), Result.class);
            if (!SUCCESS_CODE.equals(result.getCode())) {

                LOGGER.info("发送验证码失败 params {} res {}", JSON.toJSON(request), JSON.toJSON(result));

                // 错误发送失败
                return new SendResult()
                        .setIsSuccess(false)
                        .setCode(SendResult.ERROR_CODE)
                        .setMessage(result.getMessage());
            } else {
                LOGGER.info("发送验证码失败 params {} res", JSON.toJSON(request), JSON.toJSON(result));

                // 发送成功
                return new SendResult()
                        .setIsSuccess(true)
                        .setCode(SendResult.SUCCESS_CODE)
                        .setMessage(result.getMessage());
            }
        } catch (ClientException e) {
            LOGGER.error("发送验证码异常 {}", ExceptionUtils.getMessage(e));
            return new SendResult()
                    .setIsSuccess(false)
                    .setCode(SendResult.ERROR_CODE)
                    .setMessage(ExceptionUtils.getMessage(e));
        }
    }

    /**
     * 获取 client
     *
     * @return
     */
    private IAcsClient getClient() {
        return new DefaultAcsClient(DefaultProfile.getProfile("default", accessKeyId, accessSecret));
    }
}
