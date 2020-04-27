package cn.iocoder.mall.system.biz.event.authorization;

import cn.iocoder.mall.system.biz.dataobject.authorization.ResourceDO;
import org.springframework.context.ApplicationEvent;

/**
 * {@link ResourceDO} 删除事件
 */
public class ResourceDeleteEvent extends ApplicationEvent {

    /**
     * 资源编号
     */
    private Integer id;

    public ResourceDeleteEvent(Object source) {
        super(source);
    }

    public ResourceDeleteEvent(Object source, Integer id) {
        super(source);
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

}
