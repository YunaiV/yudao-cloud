package cn.iocoder.mall.admin.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 资源添加 DTO
 */
@Data
@Accessors(chain = true)
public class ResourceAddDTO implements Serializable {

    /**
     * 资源名字（标识）
     */
    @NotEmpty(message = "资源名字不能为空")
    private String name;
    /**
     * 类型
     */
    @NotNull(message = "类型不能为空")
    private Integer type;
    /**
     * 排序值
     */
    @NotNull(message = "类型不能为空")
    private Integer sort;
    /**
     * 展示名
     */
    @NotEmpty(message = "资源名字不能为空")
    private String displayName;
    /**
     * 父资源编号
     */
    private Integer pid;
    /**
     * 操作
     */
    private String handler;

}
