package cn.iocoder.mall.admin.api;

import cn.iocoder.mall.admin.api.bo.sms.SmsSignBO;
import lombok.Data;
import lombok.experimental.Accessors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 短信平台
 *
 * @author Sin
 * @time 2019/5/16 6:33 PM
 */
public interface SmsPlatform {

    @Data
    @Accessors(chain = true)
    class Result {
        /**
         * 编号
         */
        private String id;
        /**
         * 审核状态
         */
        private Integer applyStatus;
        /**
         * 审核内容
         */
        private String applyMessage;
    }

    /**
     * 签名 - 创建
     *
     * @param sign
     */
    Result createSign(String sign);

    /**
     * 签名 - 获取
     *
     * @param sign
     */
    Result getSign(String sign);

    /**
     * 签名 - 更新
     *
     * @param oldSign
     * @param sign
     */
    Result updateSign(String oldSign, String sign);

    /**
     * 模板 - 创建
     *
     * @param sign 选用的哪个签名
     * @param template 模板内容
     * @param tplType 1 为验证码类型，其他为 null
     */
    Result createTemplate(String sign, String template, Integer tplType);

    /**
     * 获取模板信息
     *
     * @param tipId
     */
    Result getTemplate(String tipId);

    /**
     * 更新模板内容
     *
     * @param tipId 选用的哪个签名
     * @param template 模板内容
     * @param tplType 1 为验证码类型，其他为 null
     */
    Result updateTemplate(String tipId, String template, Integer tplType);
}
