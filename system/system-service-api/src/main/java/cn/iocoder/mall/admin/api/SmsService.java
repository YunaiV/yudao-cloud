package cn.iocoder.mall.admin.api;

import cn.iocoder.mall.admin.api.bo.sms.SmsSignBO;
import cn.iocoder.mall.admin.api.bo.sms.SmsTemplateBO;

import java.util.List;

/**
 * 短信服务
 *
 * @author Sin
 * @time 2019/5/16 9:54 AM
 */
public interface SmsService {



    /**
     * 签名 - 创建
     *
     * @param sign
     */
    void createSign(String sign);

    /**
     * 签名 - 获取
     *
     * @param sign
     */
    SmsSignBO getSign(String sign);

    /**
     * 签名 - 更新
     *
     * @param oldSign
     * @param sign
     */
    void updateSign(String oldSign, String sign);

    /**
     * 模板 - 创建
     *
     * @param smsSignId 选用的哪个签名
     * @param template 模板内容
     * @param tplType 1 为验证码类型，其他为 null
     */
    void createTemplate(Integer smsSignId, String template, Integer tplType);

    /**
     * 模板 - 获取
     *
     * @param id
     */
    SmsTemplateBO getTemplate(Integer id);

    /**
     * 模板 - 更新
     *
     * @param id 模板id
     * @param template 模板内容
     * @param tplType 1 为验证码类型，其他为 null
     */
    void updateTemplate(Integer id, String template, Integer tplType);

    /**
     * 模板 - 删除
     *
     * @param id
     */
    void deleteTemplate(Integer id);


    /**
     * 短信发送 - 单个
     *
     * @return
     */
    void singleSend(String mobile, Integer smsTemplateId);

    /**
     * 短信发送 - 批量
     *
     * @return
     */
    void batchSend(List<String> mobileList, Integer smsTemplateId);
}
