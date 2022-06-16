package cn.iocoder.mall.admin.dataobject;

import cn.iocoder.common.framework.dataobject.DeletableDO;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 短信签名
 *
 *  签名是短信发送前缀 如：【阿里云】、【小红书】
 *
 * @author Sin
 * @time 2019/5/16 12:28 PM
 */
@Data
@Accessors(chain = true)
@TableName("sms_sign")
public class SmsSignDO extends DeletableDO {

    /**
     * 编号
     */
    private Integer id;
    /**
     * 签名名称
     */
    private String sign;
    /**
     * 平台
     *
     * 1、云片
     * 2、阿里云
     */
    private Integer platform;
    /**
     * 审核状态
     *
     * - 1、审核中
     * - 2、审核成功
     * - 10、审核失败
     */
    private Integer applyStatus;
    /**
     * 审核信息
     */
    private String applyMessage;
}
