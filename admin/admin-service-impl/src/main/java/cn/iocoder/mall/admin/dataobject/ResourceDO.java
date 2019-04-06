package cn.iocoder.mall.admin.dataobject;

import cn.iocoder.common.framework.dataobject.DeletableDO;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 资源实体
 */
@Data
@Accessors(chain = true)
public class ResourceDO extends DeletableDO {

    /**
     * 资源类型 - 菜单
     */
    @Deprecated
    public static final Integer TYPE_MENU = 1;
    /**
     * 资源类型 - 操作
     *
     * 例如，按钮。
     */
    @Deprecated
    public static final Integer TYPE_OPERATION = 2;

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
     * 父级资源编号(外键：{@link ResourceDO#id})
     */
    private Integer pid;
    /**
     * 操作
     *
     * 当资源类型为【菜单】时，handler 配置为界面 URL ，或者前端组件名
     * 当资源类型为【URL】时，handler 配置为后端 URL 。举个例子，如果有一个「创建管理员」的表单，那么前端界面上的按钮可以根据这个 url 判断是否展示，后端接收到该 url 的请求时会判断是否有权限。
     */
    private String handler;

}
