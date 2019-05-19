package cn.iocoder.mall.admin.service;

import cn.iocoder.common.framework.exception.ServiceException;
import cn.iocoder.mall.admin.api.SmsPlatform;
import cn.iocoder.mall.admin.api.constant.AdminErrorCodeEnum;
import cn.iocoder.mall.admin.api.constant.SmsApplyStatusEnum;
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
import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

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

    @Value("${sms.apiKey}")
    private String apiKey;

    @Override
    public Result createSign(String sign) {
        try {
            // 存在直接 return 相当于，创建了
            return getSign(sign);
        } catch (ServiceException e) {
            // skip 不存在会进这里，不错任何操作
        }

        // 调用 短信平台
        Map<String, String> params = new LinkedHashMap<>();
        params.put("apikey", apiKey);
        params.put("sign", sign);
        params.put("notify", "true");
        String result = post(URL_SIGN_ADD, params);
        JSONObject jsonObject = JSON.parseObject(result);
        if (jsonObject.containsKey("code")
                && !(jsonObject.getInteger("code") == SUCCESS_CODE)) {
            throw new ServiceException(AdminErrorCodeEnum.SMS_PLATFORM_FAIL.getCode(),
                    jsonObject.getString("detail"));
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

        if (jsonObject.containsKey("code")
                && !(jsonObject.getInteger("code") == SUCCESS_CODE)) {
            throw new ServiceException(AdminErrorCodeEnum.SMS_PLATFORM_FAIL.getCode(),
                    jsonObject.getString("detail"));
        }

        JSONArray jsonArray = jsonObject.getJSONArray("sign");
        if (jsonArray.size() <= 0) {
            throw new ServiceException(AdminErrorCodeEnum.SMS_SIGN_NOT_EXISTENT.getCode(),
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

        if (jsonObject.containsKey("code")
                && !(jsonObject.getInteger("code") == SUCCESS_CODE)) {
            throw new ServiceException(AdminErrorCodeEnum.SMS_PLATFORM_FAIL.getCode(),
                    jsonObject.getString("detail"));
        }

        JSONObject signJSONObject = (JSONObject) jsonObject.get("sign");
        Integer applyState = smsStatusMapping(signJSONObject.getString("apply_state"));
        return new Result().setId(null).setApplyStatus(applyState).setApplyMessage(null);
    }

    @Override
    public Result createTemplate(String template, Integer tplType) {
        Map<String, String> params = new LinkedHashMap<>();
        params.put("apikey", apiKey);
        params.put("tpl_content", template);
        if (tplType != null) {
            params.put("tplType", String.valueOf(tplType));
        }
        String result = post(URL_TEMPLATE_ADD, params);
        JSONObject jsonObject = JSON.parseObject(result);

        if (jsonObject.containsKey("code")
                && !(jsonObject.getInteger("code") == SUCCESS_CODE)) {
            throw new ServiceException(AdminErrorCodeEnum.SMS_PLATFORM_FAIL.getCode(),
                    jsonObject.getString("detail"));
        }

        String tipId = jsonObject.getString("tpl_id");
        String checkStatus = jsonObject.getString("check_status");
        String reason = jsonObject.getString("reason");
        Integer applyStatus = smsStatusMapping(checkStatus);
        return new Result().setId(tipId).setApplyStatus(applyStatus).setApplyMessage(reason);
    }

    @Override
    public Result getTemplate(String tplId) {
        Map<String, String> params = new LinkedHashMap<>();
        params.put("apikey", apiKey);
        params.put("tpl_id", tplId);
        String result = post(URL_TEMPLATE_GET, params);
        JSONObject jsonObject = JSON.parseObject(result);

        if (jsonObject.containsKey("code")
                && !(jsonObject.getInteger("code") == SUCCESS_CODE)) {
            throw new ServiceException(AdminErrorCodeEnum.SMS_PLATFORM_FAIL.getCode(),
                    jsonObject.getString("detail"));
        }

        String checkStatus = jsonObject.getString("check_status");
        Integer applyStatus = smsStatusMapping(checkStatus);
        String reason = jsonObject.getString("reason");
        return new Result().setId(tplId).setApplyStatus(applyStatus).setApplyMessage(reason);
    }

    @Override
    public Result updateTemplate(String tplId, String template, Integer tplType) {
        Map<String, String> params = new LinkedHashMap<>();
        params.put("apikey", apiKey);
        params.put("tpl_id", tplId);
        params.put("tpl_content", template);
        String result = post(URL_TEMPLATE_UPDATE, params);
        JSONObject jsonObject = JSON.parseObject(result);

        if (jsonObject.containsKey("code")
                && !(jsonObject.getInteger("code") == SUCCESS_CODE)) {
            throw new ServiceException(AdminErrorCodeEnum.SMS_PLATFORM_FAIL.getCode(),
                    jsonObject.getString("detail"));
        }

        JSONObject templateJSONObject = (JSONObject) jsonObject.get("template");
        String checkStatus = templateJSONObject.getString("check_status");
        Integer applyStatus = smsStatusMapping(checkStatus);
        String reason = jsonObject.getString("reason");
        return new Result().setId(tplId).setApplyStatus(applyStatus).setApplyMessage(reason);
    }

    @Override
    public Result deleteTemplate(String tplId) {

        // 如果不存在/已删除，直接返回
        try {
            getTemplate(tplId);
        } catch (ServiceException e) {
            // skip
            return new Result().setId(tplId).setApplyStatus(null).setApplyMessage(null);
        }

        Map<String, String> params = new LinkedHashMap<>();
        params.put("apikey", apiKey);
        params.put("tpl_id", tplId);
        String result = post(URL_TEMPLATE_DELETE, params);
        JSONObject jsonObject = JSON.parseObject(result);

        if (jsonObject.containsKey("code")
                && !(jsonObject.getInteger("code") == SUCCESS_CODE)) {
            throw new ServiceException(AdminErrorCodeEnum.SMS_PLATFORM_FAIL.getCode(),
                    jsonObject.getString("detail"));
        }

        return new Result().setId(tplId).setApplyStatus(null).setApplyMessage(null);
    }

    @Override
    public SendResult singleSend(String mobile, String template) {
        Map<String, String> params = new LinkedHashMap<>();
        params.put("apikey", apiKey);
        params.put("mobile", mobile);
        params.put("text", template);
        // TODO: 2019/5/19 sin 运营商发送报告 回调
        // params.put("callback_url", template);
        String result = post(URL_TEMPLATE_DELETE, params);
        JSONObject jsonObject = JSON.parseObject(result);
        if (jsonObject.containsKey("code")
                && !(jsonObject.getInteger("code") == SUCCESS_CODE)) {
            throw new ServiceException(AdminErrorCodeEnum.SMS_PLATFORM_FAIL.getCode(),
                    jsonObject.getString("detail"));
        }

        return new SendResult()
                .setHasSuccess(SUCCESS_CODE == jsonObject.getInteger("code"))
                .setCode(jsonObject.getInteger("code"))
                .setMessage(jsonObject.getString("detail"))
                .setSuccess(Lists.newArrayList(mobile))
                .setFail(Collections.EMPTY_LIST);
    }

    @Override
    public SendResult batchSend(List<String> mobileList, String template) {

        // 最大发送数为 1000，我们设置为 500 个, 分段发送
        int maxSendSize = 500;
        int maxSendSizeCount = mobileList.size() % maxSendSize;
        int j = 0;
        int j2 = maxSendSize;

        List<String> successList = new ArrayList<>();
        List<String> failList = new ArrayList<>();
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
                throw new ServiceException(AdminErrorCodeEnum.SMS_PLATFORM_FAIL.getCode(),
                        jsonObject.getString("detail"));
            }

            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for (Object o : jsonArray) {
                JSONObject dataJSONObject = (JSONObject) o;
                if (SUCCESS_CODE == dataJSONObject.getInteger("code")) {
                    successList.add(dataJSONObject.getString("mobile"));
                } else {
                    failList.add(dataJSONObject.getString("mobile"));
                }
            }

            // 用于递增 maxSendSize
            j = j2;
            j2 = j + maxSendSize;
        }

        return new SendResult()
                .setHasSuccess(true)
                .setCode(SUCCESS_CODE)
                .setMessage(null)
                .setSuccess(successList)
                .setFail(failList);
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
