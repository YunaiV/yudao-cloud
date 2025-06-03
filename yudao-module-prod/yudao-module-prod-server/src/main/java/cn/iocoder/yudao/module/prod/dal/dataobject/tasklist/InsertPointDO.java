package cn.iocoder.yudao.module.prod.dal.dataobject.tasklist;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 插入点 DO
 *
 * @author 麦芽
 */
@TableName("prod_insert_point")
@KeySequence("prod_insert_point_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InsertPointDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 任务列表的键
     */
    private Long taskId;
    /**
     * 插入点
     */
    private String point;
    /**
     * 替换的内容
     */
    private String content;
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