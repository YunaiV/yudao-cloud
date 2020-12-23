package cn.iocoder.mall.systemservice.dal.mysql.dataobject.permission;

import cn.iocoder.mall.mybatis.core.dataobject.DeletableDO;
import cn.iocoder.mall.systemservice.dal.mysql.dataobject.admin.AdminDO;
import cn.iocoder.mall.systemservice.enums.permission.ResourceTypeEnum;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 资源实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName(value = "permission_resource")
public class ResourceDO extends DeletableDO {

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
     *
     * 一般格式为：${系统}:${模块}:${操作}
     * 例如说：system:admin:add，即 system 服务的添加管理员。
     *
     * 当我们把该 ResourceDO 赋予给角色后，意味着该角色有该资源：
     * - 对于后端，配合 @RequiresPermissions 注解，配置 API 接口需要该权限，从而对 API 接口进行权限控制。
     * - 对于前端，配合前端标签，配置按钮是否展示，避免用户没有该权限时，结果可以看到该操作。
     */
    private String permission;
    /**
     * 资源类型
     *
     * 关联 {@link ResourceTypeEnum}
     */
    private Integer type;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 父级资源编号
     *
     * 关联：{@link ResourceDO#getId()}
     */
    private Integer pid;
    /**
     * 前端路由
     *
     * 目前当且仅当资源类型为 {@link ResourceTypeEnum#MENU} 时，才会生效
     */
    private String route;
    /**
     * 菜单图标
     *
     * 目前当且仅当资源类型为 {@link ResourceTypeEnum#MENU} 时，才会生效
     */
    private String icon;
    /**
     * 前端界面
     * 例如说，vue 框架下的 component 对应的 view 页面。
     *
     * 目前当且仅当资源类型为 {@link ResourceTypeEnum#MENU} 时，才会生效
     */
    private String view;

    /**
     * 创建管理员编号
     *
     * 外键 {@link AdminDO#getId()}
     */
    private Integer createAdminId;

}
