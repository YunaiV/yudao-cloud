package cn.iocoder.yudao.module.mp.dal.dataobject.template;

import lombok.*;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 微信模版消息发送记录 DO
 *
 * @author dengsl
 */
@TableName("mp_msg_template_log")
@KeySequence("mp_msg_template_log_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MsgTemplateLogDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * appId
     */
    private String appId;
    /**
     * 用户openid
     */
    private String toUser;
    /**
     * 公众号模板ID
     */
    private String templateId;
    /**
     * 消息内容
     */
    private String data;
    /**
     * 链接
     */
    private String url;
    /**
     * 小程序appid
     */
    private String miniProgramAppId;
    /**
     * 小程序页面路径
     */
    private String miniProgramPagePath;
    /**
     * 发送时间
     */
    private LocalDateTime sendTime;
    /**
     * 发送状态 0成功，1失败
     */
    private Integer sendStatus;
    /**
     * 发送结果
     */
    private String sendResult;


}