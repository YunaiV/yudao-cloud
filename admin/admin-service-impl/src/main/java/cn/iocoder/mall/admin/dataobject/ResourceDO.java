package cn.iocoder.mall.admin.dataobject;

import cn.iocoder.common.framework.dataobject.DeletableDO;

import java.util.Date;

/**
 * 资源实体
 */
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

    public Integer getId() {
        return id;
    }

    public ResourceDO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ResourceDO setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getType() {
        return type;
    }

    public ResourceDO setType(Integer type) {
        this.type = type;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    public ResourceDO setSort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public String getDisplayName() {
        return displayName;
    }

    public ResourceDO setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public ResourceDO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Integer getPid() {
        return pid;
    }

    public ResourceDO setPid(Integer pid) {
        this.pid = pid;
        return this;
    }

    public String getHandler() {
        return handler;
    }

    public ResourceDO setHandler(String handler) {
        this.handler = handler;
        return this;
    }

}
