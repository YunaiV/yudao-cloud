package cn.iocoder.mall.system.biz.bo.smsSign;

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
public class ListSmsSignBO {

    /**
     * 编号
     */
    private Integer id;
    /**
     * 短信平台
     */
    private Integer platform;
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
}
