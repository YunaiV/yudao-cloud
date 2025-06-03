package cn.iocoder.yudao.module.prod.dal.dataobject.tasklist;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 任务列 DO
 *
 * @author 麦芽
 */
@TableName("prod_task_list")
@KeySequence("prod_task_list_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskListDO extends BaseDO {

    /**
     * 任务id
     */
    @TableId
    private Long id;
    /**
     * 任务类型
     *
     * 枚举 {@link TODO prod_task_type 对应的类}
     */
    private Boolean taskType;
    /**
     * 产品模板配置id
     */
    private Long prodConfigId;
    /**
     * 生成数量
     */
    private Integer taskGenQty;
    /**
     * 每多少次切换prompt
     */
    private Integer taskSwitchNum;
    /**
     * 任务咒语
     */
    private String taskPrompt;
    /**
     * 任务状态
     */
    private Boolean taskStatus;
    /**
     * 图片大小
     */
    private String imgSize;
    /**
     * 模板名称
     */
    private Long templateId;
    /**
     * 任务咒语描述
     */
    private String taskPromptDesc;
    @TableField("attribute_1")
    private String attribute1;
    /**
     * 拓展
     */
    @TableField("attribute_2")
    private String attribute2;
    /**
     * 拓展
     */
    @TableField("attribute_3")
    private String attribute3;
    /**
     * 拓展
     */
    @TableField("attribute_4")
    private String attribute4;
    /**
     * 拓展
     */
    @TableField("attribute_5")
    private String attribute5;
    /**
     * 拓展
     */
    @TableField("attribute_6")
    private String attribute6;
    /**
     * 拓展
     */
    @TableField("attribute_7")
    private String attribute7;
    /**
     * 拓展
     */
    @TableField("attribute_8")
    private String attribute8;
    /**
     * 拓展
     */
    @TableField("attribute_9")
    private String attribute9;
    /**
     * 拓展
     */
    @TableField("attribute_10")
    private String attribute10;



}