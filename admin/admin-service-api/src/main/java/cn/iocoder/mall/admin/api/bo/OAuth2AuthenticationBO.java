package cn.iocoder.mall.admin.api.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Set;

/**
 * OAUTH2 认证 BO
 */
@Data
@Accessors(chain = true)
public class OAuth2AuthenticationBO implements Serializable {

    /**
     * 管理员编号
     */
    private Integer adminId;
    /**
     * 角色编号数组
     */
    private Set<Integer> roleIds;

}
