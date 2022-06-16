package cn.iocoder.mall.system.biz.bo.smsTemplate;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * sms page
 *
 * @author Sin
 * @time 2019/5/19 4:23 PM
 */
@Data
@Accessors(chain = true)
public class ListSmsTemplateBO {

    /**
     * 编号
     */
    private Integer id;
    /**
     * 模板编号 (第三方的)
     */
    private Integer smsSignId;
    /**
     * 短信签名 id
     */
    private String platform;
    /**
     * 短信模板 Code
     */
    private String templateCode;
    /**
     * 短信模板
     */
    private String template;
    /**
     * 短信类型
     */
    private Integer smsType;
    /**
     * 审核状态
     * <p>
     * 1、审核中
     * 2、审核成功
     * 3、审核失败
     */
    private Integer applyStatus;
    /**
     * 审核信息
     */
    private String applyMessage;
    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    ///
    /// 关联字段

    /**
     * 签名信息
     */
    private Sign sign;


    @Data
    @Accessors(chain = true)
    public static class Sign {
        /**
         * 编号
         */
        private Integer id;
        /**
         * 签名id 这个是第三方的
         */
        private String platformId;
        /**
         * 签名名称
         */
        private String sign;
        /**
         * 审核状态
         * <p>
         * - 1、审核中
         * - 2、审核成功
         * - 3、审核失败
         */
        private Integer applyStatus;
        /**
         * 审核信息
         */
        private String applyMessage;
    }
}
