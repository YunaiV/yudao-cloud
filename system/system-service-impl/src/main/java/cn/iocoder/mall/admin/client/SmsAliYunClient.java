package cn.iocoder.mall.admin.client;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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

    @Value("sms.aliYun.accessKeyId")
    private String accessKeyId;
    @Value("sms.aliYun.accessSecret")
    private String accessSecret;

    private static final String DOMAIN = "dysmsapi.aliyuncs.com";

    @Override
    public SendResult singleSend(String mobile, String sign, String template, Map<String, String> params) {
        IAcsClient client = getClient();
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain(DOMAIN);
        request.setVersion("2017-05-25");
        request.setAction("SendSms");

        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public SendResult batchSend(List<String> mobileList, String sign, String template, Map<String, String> params) {

        // 获取 client
        IAcsClient client = getClient();

        // params
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain(DOMAIN);
        request.setVersion("2017-05-25");
        request.setAction("SendBatchSms");

        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return null;
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
