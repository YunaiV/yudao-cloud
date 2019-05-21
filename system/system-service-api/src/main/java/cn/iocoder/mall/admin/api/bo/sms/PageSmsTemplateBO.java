package cn.iocoder.mall.admin.api.bo.sms;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * sms page
 *
 * @author Sin
 * @time 2019/5/19 4:23 PM
 */
@Data
@Accessors(chain = true)
public class PageSmsTemplateBO {

    private Long total;

    private Long current;

    private Long size;

    private List<Template> data;

    @Data
    @Accessors(chain = true)
    public static class Template {
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
        private String platformId;
        /**
         * 短信模板
         */
        private String template;
        /**
         * 审核状态
         *
         * 1、审核中
         * 2、审核成功
         * 3、审核失败
         */
        private Integer applyStatus;
        /**
         * 审核信息
         */
        private String applyMessage;

        ///
        /// 关联字段

        /**
         * 签名信息
         */
        private Sign sign;
    }

    @Data
    @Accessors(chain = true)
    public class Sign {
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
         *
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
