package cn.iocoder.mall.admin.api.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 资源 BO
 */
@Data
@Accessors(chain = true)
public class ResourceBO {

    /**
     * 资源编号
     */
    private Integer id;
    /**
     * 资源名字（标识）
     */
    private String name;
    /**
     * 资源类型
     */
    private Integer type;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 展示名
     */
    private String displayName;
    /**
     * 添加时间
     */
    private Date createTime;
    /**
     * 父级资源编号
     */
    private Integer pid;
    /**
     * 操作
     */
    private String handler;

}
