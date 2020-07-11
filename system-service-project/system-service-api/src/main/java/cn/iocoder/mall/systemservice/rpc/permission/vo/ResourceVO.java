package cn.iocoder.mall.systemservice.rpc.permission.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
* 资源 VO
*/
@Data
@Accessors(chain = true)
public class ResourceVO implements Serializable {

    /**
     * 资源编号
     */
    private Integer id;
    /**
     * 菜单名
     */
    private String name;
    /**
     * 权限标识
     */
    private String permission;
    /**
     * 资源类型
     */
    private Integer type;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 父级资源编号
     */
    private Integer pid;
    /**
     * 前端路由
     */
    private String route;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 前端界面
     */
    private String view;
    /**
     * 添加时间
     */
    private Date createTime;

}
