package cn.iocoder.mall.admin.dataobject;

import cn.iocoder.common.framework.dataobject.DeletableDO;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 资源实体
 */
@Data
@Accessors(chain = true)
public class ResourceDO extends DeletableDO {

    /**
     * 资源编号
     */
    private Integer id;
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
     * 父级资源编号(外键：{@link ResourceDO#id})
     */
    private Integer pid;
    /**
     * 操作
     *
     * 目前当且仅当资源类型为【菜单】时，才会生效，即 handler 配置为界面 URL ，或者前端组件名，或者前端的路由。
     */
    private String handler;
    /**
     * 图表
     *
     * 目前当且仅当资源类型为【菜单】时，才会生效
     */
    private String icon;
    /**
     * 权限标识数组，使用逗号分隔。
     *
     * 例如：system.admin.add 。
     * 推荐格式为 ${系统}.${模块}.${操作} 。
     */
    private String permissions;

}
