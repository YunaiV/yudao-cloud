package cn.iocoder.mall.system.biz.bo.authorization;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 授权模块 - 资源信息树节点 BO
 */
@Data
@Accessors(chain = true)
public class ResourceTreeNodeBO {

    /**
     * 当前节点
     */
    private ResourceBO node;
    /**
     * 子节点们
     */
    private List<ResourceTreeNodeBO> children;

}
