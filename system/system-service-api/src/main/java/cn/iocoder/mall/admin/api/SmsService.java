package cn.iocoder.mall.admin.api;

import cn.iocoder.mall.admin.api.bo.sms.SmsSignBO;
import cn.iocoder.mall.admin.api.bo.sms.SmsTemplateBO;

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
     * 获取模板信息
     *
     * @param id
     */
    SmsTemplateBO getTemplate(String id);

    /**
     * 更新模板内容
     *
     * @param id 模板id
     * @param template 模板内容
     * @param tplType 1 为验证码类型，其他为 null
     */
    void updateTemplate(String id, String template, Integer tplType);
}
