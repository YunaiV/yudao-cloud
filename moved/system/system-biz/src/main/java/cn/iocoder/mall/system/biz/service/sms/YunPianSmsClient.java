package cn.iocoder.mall.system.biz.service.sms;

import cn.iocoder.common.framework.exception.ServiceException;
import cn.iocoder.mall.system.biz.enums.SystemErrorCodeEnum;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 云片 短信平台
 *
 * @author Sin
 * @time 2019/5/16 6:34 PM
 */
@Component
public class YunPianSmsClient implements SmsClient {

    protected static final Logger LOGGER = LoggerFactory.getLogger(YunPianSmsClient.class);

    private static final int SUCCESS_CODE = 0;
    private static final String SUCCESS_MESSAGE = "SUCCESS";

    /**
     * 云片短信 - 批量推送最大数 500，支持 1000
     */
    private static final int MAX_BATCH_SIZE = 500;
    /**
     * 模板 - 参数拼接
     */
    private static final String PARAM_TEMPLATE = "#%s#";
    /**
     * 模板 - 签名拼接
     */
    private static final String SIGN_TEMPLATE = "【%s】%s";

    /**
     * 签名 - 添加
     */
    private static final String URL_SIGN_ADD = "https://sms.yunpian.com/v2/sign/add.json";
    /**
     * 签名 - 获取
     */
    private static final String URL_SIGN_GET = "https://sms.yunpian.com/v2/sign/get.json";
    /**
     * 签名 - 更新
     */
    private static final String URL_SIGN_UPDATE = "https://sms.yunpian.com/v2/sign/update.json";
    /**
     * 模板 - 添加
     */
    private static final String URL_TEMPLATE_ADD = "https://sms.yunpian.com/v2/tpl/add.json";
    /**
     * 模板 - 获取
     */
    private static final String URL_TEMPLATE_GET = "https://sms.yunpian.com/v2/tpl/get.json";
    /**
     * 模板 - 更新
     */
    private static final String URL_TEMPLATE_UPDATE = "https://sms.yunpian.com/v2/tpl/update.json";
    /**
     * 模板 - 删除
     */
    private static final String URL_TEMPLATE_DELETE = "https://sms.yunpian.com/v2/tpl/del.json";
    /**
     * 短信发送 - 单个
     */
    private static final String URL_SEND_SINGLE = "https://sms.yunpian.com/v2/sms/single_send.json";
    /**
     * 短信发送 - 批量
     */
    private static final String URL_SEND_BATCH = "https://sms.yunpian.com/v2/sms/batch_send.json";

    //编码格式。发送编码格式统一用UTF-8
    private static String ENCODING = "UTF-8";

    @Value("${sms.yunPian.apiKey?:'default_value'}")
    private String apiKey;

    @Override
    public SendResult singleSend(String mobile, String sign, String templateCode, String template, Map<String, String> templateParams) {
        // build 模板
        template = buildTemplate(sign, template, templateParams);
        // 请求参数
        Map<String, String> params = new LinkedHashMap<>();
        params.put("apikey", apiKey);
        params.put("mobile", mobile);
        params.put("text", template);
        // TODO: 2019/5/19 sin 运营商发送报告 回调
        // params.put("callback_url", template);
        String result = post(URL_SEND_SINGLE, params);
        JSONObject jsonObject = JSON.parseObject(result);
        if (jsonObject.containsKey("code")
                && !(jsonObject.getInteger("code") == SUCCESS_CODE)) {
            throw new ServiceException(SystemErrorCodeEnum.SMS_PLATFORM_FAIL.getCode(),
                    jsonObject.getString("detail"));
        }
        // 转换 result
        return new SendResult()
                .setIsSuccess(SUCCESS_CODE == jsonObject.getInteger("code"))
                .setCode(jsonObject.getInteger("code"))
                .setMessage(jsonObject.getString("detail"));
    }

    @Override
    public SendResult batchSend(List<String> mobileList, String sign,
                                String templateCode, String template,
                                Map<String, String> templateParams) {
        // build 模板
        template = buildTemplate(sign, template, templateParams);
        // 最大发送数为 1000，我们设置为 500 个, 分段发送
        int maxSendSize = MAX_BATCH_SIZE;
        int maxSendSizeCount = mobileList.size() % maxSendSize == 0
                ? mobileList.size() / maxSendSize
                : mobileList.size() / maxSendSize + 1;
        int j = 0;
        int j2 = mobileList.size();

        for (int i = 0; i < maxSendSizeCount; i++) {
            StringBuffer sendMobileStr = new StringBuffer();
            for (int k = j; k < j2; k++) {
                sendMobileStr.append(",");
                sendMobileStr.append(mobileList.get(k));
            }
            String dividedMobile = sendMobileStr.toString().substring(1);
            // 发送手机号
            Map<String, String> params = new LinkedHashMap<>();
            params.put("apikey", apiKey);
            params.put("mobile", dividedMobile);
            params.put("text", template);
            // TODO: 2019/5/19 sin 运营商发送报告 回调
            // params.put("callback_url", template);
            String result = post(URL_SEND_BATCH, params);
            JSONObject jsonObject = JSON.parseObject(result);
            if (jsonObject.containsKey("code")
                    && !(jsonObject.getInteger("code") == SUCCESS_CODE)) {
                throw new ServiceException(SystemErrorCodeEnum.SMS_PLATFORM_FAIL.getCode(),
                        jsonObject.getString("detail"));
            }
            // 用于递增 maxSendSize
            j = j2;
            j2 = j + maxSendSize;
        }
        return new SendResult()
                .setIsSuccess(true)
                .setCode(SUCCESS_CODE)
                .setMessage(SUCCESS_MESSAGE);
    }

    /**
     * 构建模板
     *
     * @param sign
     * @param template
     * @param templateParams
     * @return
     */
    private static String buildTemplate(String sign, String template,
                                        Map<String, String> templateParams) {
        // 不处理 empty 数据
        if (CollectionUtils.isEmpty(templateParams)) {
            return template;
        }
        // 处理template参数
        for (Map.Entry<String, String> entry : templateParams.entrySet()) {
            String paramsKey = entry.getKey();
            String value = entry.getValue();
            String paramPlace = String.format(PARAM_TEMPLATE, paramsKey);
            template = template.replaceAll(paramPlace, value);
        }
        template = String.format(SIGN_TEMPLATE, sign, template);
        return template;
    }

    /**
     * 基于HttpClient 4.3的通用POST方法
     *
     * @param url       提交的URL
     * @param paramsMap 提交<参数，值>Map
     * @return 提交响应
     */

    public static String post(String url, Map<String, String> paramsMap) {
        CloseableHttpClient client = HttpClients.createDefault();
        String responseText = "";
        CloseableHttpResponse response = null;
        try {
            HttpPost method = new HttpPost(url);
            if (paramsMap != null) {
                List<NameValuePair> paramList = new ArrayList<>();
                for (Map.Entry<String, String> param : paramsMap.entrySet()) {
                    NameValuePair pair = new BasicNameValuePair(param.getKey(),
                            param.getValue());
                    paramList.add(pair);
                }
                method.setEntity(new UrlEncodedFormEntity(paramList, ENCODING));
            }
            response = client.execute(method);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                responseText = EntityUtils.toString(entity, ENCODING);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        LOGGER.debug("云片短信平台 res: {}", responseText);
        return responseText;
    }
}
