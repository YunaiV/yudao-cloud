package cn.iocoder.yudao.module.mp.dal.dataobject.template;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * 消息模板 DO
 *
 * @author dengsl
 */
@TableName("mp_msg_template")
@KeySequence("mp_msg_template_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MsgTemplateDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * appid
     */
    private String appId;
    /**
     * 公众号模板ID
     */
    private String templateId;
    /**
     * 模版名称
     */
    private String name;
    public String getName() {
        return this.templateId;
    }

    /**
     * 标题
     */
    private String title;
    /**
     * 模板内容
     */
    private String content;
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
     * 模板所属行业的一级行业
     */
    private String primaryIndustry;
    /**
     * 模板所属行业的二级行业
     */
    private String deputyIndustry;
    /**
     * 模板示例
     */
    private String example;
    /**
     * 是否有效 0有效,1无效
     */
    private Integer status;
    /**
     * 公众号是否已移除 0未移除,1已移除
     */
    private Integer isRemoved;

}