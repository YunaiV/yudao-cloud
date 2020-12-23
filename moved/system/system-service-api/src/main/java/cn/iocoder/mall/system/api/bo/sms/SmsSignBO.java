package cn.iocoder.mall.system.api.bo.sms;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 短信签名
 *
 * @author Sin
 * @time 2019/5/16 6:30 PM
 */
@Data
@Accessors(chain = true)
public class SmsSignBO {

    /**
     * 编号
     */
    private Integer id;
    /**
     * 签名id 这个是第三方的
     */
    private Integer signId;
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
