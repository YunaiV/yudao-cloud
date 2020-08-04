package cn.iocoder.mall.system.api;

import cn.iocoder.mall.system.api.bo.sms.SmsSignBO;
import cn.iocoder.mall.system.api.bo.sms.PageSmsSignBO;
import cn.iocoder.mall.system.api.bo.sms.SmsTemplateBO;
import cn.iocoder.mall.system.api.bo.sms.PageSmsTemplateBO;
import cn.iocoder.mall.system.api.dto.sms.PageQuerySmsSignDTO;
import cn.iocoder.mall.system.api.dto.sms.PageQuerySmsTemplateDTO;

import java.util.List;
import java.util.Map;

/**
 * 短信服务
 *
 * @author Sin
 * @time 2019/5/16 9:54 AM
 */
public interface SmsService {

    /**
     * 短信模板 - 分页
     *
     * @param queryDTO
     * @return
     */
    PageSmsSignBO pageSmsSign(PageQuerySmsSignDTO queryDTO);

    /**
     * 短信模板 - 分页
     *
     * @param queryDTO
     * @return
     */
    PageSmsTemplateBO pageSmsTemplate(PageQuerySmsTemplateDTO queryDTO);

    /**
     * 签名 - 创建
     *
     * @param sign
     */
    void addSign(String sign, Integer platform);

    /**
     * 签名 - 获取
     *
     * @param id
     */
    SmsSignBO getSign(Integer id);

    /**
     * 签名 - 更新
     *
     * @param id
     * @param newSign
     * @param platform
     */
    void updateSign(Integer id, String newSign, Integer platform);

    /**
     * 签名 - 更新
     *
     * @param id
     */
    void deleteSign(Integer id);

    /**
     * 模板 - 创建
     *
     * @param smsSignId 选用的哪个签名
     * @param templateCode 模板code
     * @param template 模板内容
     * @param platform 平台
     */
    void addTemplate(Integer smsSignId, String templateCode,
                     String template, Integer platform, Integer smsType);

    /**
     * 模板 - 获取
     *
     * @param id
     */
    SmsTemplateBO getTemplate(Integer id, Integer platform);

    /**
     * 模板 - 更新
     *
     * @param id 模板id
     * @param smsSignId 短期签名
     * @param template 模板内容
     * @param platform 短信平台
     */
    void updateTemplate(Integer id, Integer smsSignId, String templateCode,
                        String template, Integer platform, Integer smsType);

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
    void singleSend(String mobile, Integer smsTemplateId, Map<String, String> params);

    /**
     * 短信发送 - 批量
     *
     * @return
     */
    void batchSend(List<String> mobileList, Integer smsTemplateId, Map<String, String> params);
}
