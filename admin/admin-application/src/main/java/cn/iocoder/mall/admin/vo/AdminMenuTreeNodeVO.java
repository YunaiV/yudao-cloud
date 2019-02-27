package cn.iocoder.mall.admin.vo;

import java.util.List;

public class AdminMenuTreeNodeVO {

    /**
     * 菜单编号
     */
    private Integer id;
    /**
     * 彩蛋名
     */
    private String name;
    /**
     * 操作
     */
    private String handler;
    /**
     * 父菜单编号
     */
    private Integer pid;
    /**
     * 子节点数组
     */
    private List<AdminMenuTreeNodeVO> children;

    public Integer getId() {
        return id;
    }

    public AdminMenuTreeNodeVO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public AdminMenuTreeNodeVO setName(String name) {
        this.name = name;
        return this;
    }

    public String getHandler() {
        return handler;
    }

    public AdminMenuTreeNodeVO setHandler(String handler) {
        this.handler = handler;
        return this;
    }

    public List<AdminMenuTreeNodeVO> getChildren() {
        return children;
    }

    public AdminMenuTreeNodeVO setChildren(List<AdminMenuTreeNodeVO> children) {
        this.children = children;
        return this;
    }

    public Integer getPid() {
        return pid;
    }

    public AdminMenuTreeNodeVO setPid(Integer pid) {
        this.pid = pid;
        return this;
    }
}
