package cn.iocoder.mall.system.biz.event.authorization;

import cn.iocoder.mall.system.biz.dataobject.authorization.RoleDO;
import org.springframework.context.ApplicationEvent;

/**
 * {@link RoleDO} 删除事件
 */
public class RoleDeleteEvent extends ApplicationEvent {
 /**
     * 角色编号
     */
    private Integer id;

    public RoleDeleteEvent(Object source) {
        super(source);
    }

    public RoleDeleteEvent(Object source, Integer id) {
        super(source);
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

}
