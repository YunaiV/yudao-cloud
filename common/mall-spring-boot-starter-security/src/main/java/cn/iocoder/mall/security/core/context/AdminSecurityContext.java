package cn.iocoder.mall.security.core.context;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Set;

/**
 * Security 上下文
 */
@Data
@Accessors(chain = true)
public class AdminSecurityContext {

    /**
     * 管理员编号
     */
    private Integer adminId;
    /**
     * 管理员账号
     */
    private String username;
    /**
     * 拥有的角色编号
     */
    private Set<Integer> roleIds;

}
