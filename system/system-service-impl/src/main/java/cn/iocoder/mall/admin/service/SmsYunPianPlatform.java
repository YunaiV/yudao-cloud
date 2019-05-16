package cn.iocoder.mall.admin.service;

import cn.iocoder.mall.admin.api.SmsPlatform;
import cn.iocoder.mall.admin.api.constant.AdminErrorCodeEnum;
import cn.iocoder.mall.admin.api.constant.SmsApplyStatusEnum;
import cn.iocoder.mall.admin.api.exception.SmsFailException;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
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
import org.springframework.stereotype.Service;

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
@Service
public class SmsYunPianPlatform implements SmsPlatform {

    protected static final Logger LOGGER = LoggerFactory.getLogger(SmsPlatform.class);

    private static final int SUCCESS_CODE = 0;

    //查账户信息的http地址
    private static final String URI_GET_USER_INFO =
            "https://sms.yunpian.com/v2/user/get.json";

    //智能匹配模板发送接口的http地址
    private static final String URI_SEND_SMS =
            "https://sms.yunpian.com/v2/sms/single_send.json";

    //模板发送接口的http地址
    private static final String URI_TPL_SEND_SMS =
            "https://sms.yunpian.com/v2/sms/tpl_single_send.json";

    //发送语音验证码接口的http地址
    private static final String URI_SEND_VOICE =
            "https://voice.yunpian.com/v2/voice/send.json";

    //绑定主叫、被叫关系的接口http地址
    private static final String URI_SEND_BIND =
            "https://call.yunpian.com/v2/call/bind.json";

    //解绑主叫、被叫关系的接口http地址
    private static final String URI_SEND_UNBIND =
            "https://call.yunpian.com/v2/call/unbind.json";

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

    //编码格式。发送编码格式统一用UTF-8
    private static String ENCODING = "UTF-8";

    @Value("${sms.apiKey}")
    private String apiKey;

    @Override
    public Result createSign(String sign) {
        // 调用 短信平台
        Map<String, String> params = new LinkedHashMap<>();
        params.put("apikey", apiKey);
        params.put("sign", sign);
        params.put("notify", "true");
        String result = post(URL_SIGN_ADD, params);
        JSONObject jsonObject = JSON.parseObject(result);
        if (!(jsonObject.getInteger("code") == SUCCESS_CODE)) {
            throw new SmsFailException(AdminErrorCodeEnum.SMS_SIGN_ADD_FAIL.getCode(),
                    AdminErrorCodeEnum.SMS_SIGN_ADD_FAIL.getMessage());
        }

        JSONObject signJSONObject = (JSONObject) jsonObject.get("sign");
        Integer applyState = smsStatusMapping(signJSONObject.getString("apply_state"));
        return new Result().setId(null).setApplyStatus(applyState).setApplyMessage(null);
    }

    @Override
    public Result getSign(String sign) {
        Map<String, String> params = new LinkedHashMap<>();
        params.put("apikey", apiKey);
        params.put("sign", sign);
        params.put("page_num", "1");
        params.put("page_size", "20");
        String result = post(URL_SIGN_GET, params);
        JSONObject jsonObject = JSON.parseObject(result);

        if (!(jsonObject.getInteger("code") == SUCCESS_CODE)) {
            throw new SmsFailException(AdminErrorCodeEnum.SMS_SIGN_ADD_FAIL.getCode(),
                    AdminErrorCodeEnum.SMS_SIGN_ADD_FAIL.getMessage());
        }

        JSONArray jsonArray = jsonObject.getJSONArray("sign");
        if (jsonArray.size() <= 0) {
            throw new SmsFailException(AdminErrorCodeEnum.SMS_SIGN_NOT_EXISTENT.getCode(),
                    AdminErrorCodeEnum.SMS_SIGN_NOT_EXISTENT.getMessage());
        }

        JSONObject signJSONObject = (JSONObject) jsonArray.get(0);
        String checkStatus = signJSONObject.getString("check_status");
        String applyMessage = signJSONObject.getString("remark");
        Integer applyStatus = smsStatusMapping(checkStatus);
        return new Result().setId(null).setApplyStatus(applyStatus).setApplyMessage(applyMessage);
    }

    @Override
    public Result updateSign(String oldSign, String sign) {
        Map<String, String> params = new LinkedHashMap<>();
        params.put("apikey", apiKey);
        params.put("old_sign", oldSign);
        params.put("sign", sign);
        String result = post(URL_SIGN_UPDATE, params);
        JSONObject jsonObject = JSON.parseObject(result);

        if (!(jsonObject.getInteger("code") == SUCCESS_CODE)) {
            throw new SmsFailException(AdminErrorCodeEnum.SMS_SIGN_UPDATE_FAIL.getCode(),
                    AdminErrorCodeEnum.SMS_SIGN_UPDATE_FAIL.getMessage());
        }

        JSONObject signJSONObject = (JSONObject) jsonObject.get("sign");
        Integer applyState = smsStatusMapping(signJSONObject.getString("apply_state"));
        return new Result().setId(null).setApplyStatus(applyState).setApplyMessage(null);
    }

    @Override
    public Result createTemplate(String sign, String template, Integer tplType) {
        Map<String, String> params = new LinkedHashMap<>();
        params.put("apikey", apiKey);
        params.put("tpl_content", sign + template);
        if (tplType != null) {
            params.put("tplType", String.valueOf(tplType));
        }
        String result = post(URL_TEMPLATE_ADD, params);
        JSONObject jsonObject = JSON.parseObject(result);
        String tipId = jsonObject.getString("tpl_id");
        String checkStatus = jsonObject.getString("check_status");
        String reason = jsonObject.getString("reason");
        Integer applyStatus = smsStatusMapping(checkStatus);
        return new Result().setId(tipId).setApplyStatus(applyStatus).setApplyMessage(reason);
    }

    @Override
    public Result getTemplate(String tipId) {
        Map<String, String> params = new LinkedHashMap<>();
        params.put("apikey", apiKey);
        params.put("tipId", tipId);
        String result = post(URL_TEMPLATE_GET, params);
        JSONObject jsonObject = JSON.parseObject(result);

        String checkStatus = jsonObject.getString("check_status");
        Integer applyStatus = smsStatusMapping(checkStatus);
        String reason = jsonObject.getString("reason");
        return new Result().setId(tipId).setApplyStatus(applyStatus).setApplyMessage(reason);
    }

    @Override
    public Result updateTemplate(String tipId, String template, Integer tplType) {
        Map<String, String> params = new LinkedHashMap<>();
        params.put("apikey", apiKey);
        params.put("tipId", tipId);
        params.put("template", template);
        String result = post(URL_TEMPLATE_UPDATE, params);
        JSONObject jsonObject = JSON.parseObject(result);

        String checkStatus = jsonObject.getString("check_status");
        Integer applyStatus = smsStatusMapping(checkStatus);
        String reason = jsonObject.getString("reason");
        return new Result().setId(tipId).setApplyStatus(applyStatus).setApplyMessage(reason);
    }

    /**
     * 短信 status 和 云片状态 映射关系
     *
     * @param checkStatus
     * @return
     */
    private Integer smsStatusMapping(String checkStatus) {
        Integer applyStatus;
        switch (checkStatus) {
            case "SUCCESS":
                applyStatus = SmsApplyStatusEnum.SUCCESS.getCode();
                break;
            case "FAIL":
                applyStatus = SmsApplyStatusEnum.FAIL.getCode();
                break;
            default:
                applyStatus = SmsApplyStatusEnum.CHECKING.getCode();
                break;
        }
        return applyStatus;
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
