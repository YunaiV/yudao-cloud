package cn.iocoder.mall.system.biz.log.operation.model.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 操作日志
 *
 * @author hccake
 * @date 2020-05-15 15:12:53
 */
@Data
@TableName("operation_log")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "操作日志")
public class OperationLogPO extends Model<OperationLogPO> {
    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId
    private Integer id;
    /**
     * 链路追踪编号
     */
    private String traceId;
    /**
     * 账号编号
     */
    private Integer accountId;
    /**
     * 应用名
     */
    private String applicationName;
    /**
     * 访问地址
     */
    private String uri;
    /**
     * 参数
     */
    private String params;
    /**
     * http 方法
     */
    private String method;
    /**
     * userAgent
     */
    private String userAgent;
    /**
     * ip
     */
    private String ip;
    /**
     * 请求时间
     */
    private LocalDateTime startTime;
    /**
     * 响应时长 -- 毫秒级
     */
    private Integer responseTime;
    /**
     * 日志消息
     */
    private String msg;
    /**
     * 操作状态
     */
    private Integer status;
    /**
     * 创建者
     */
    private String operator;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
