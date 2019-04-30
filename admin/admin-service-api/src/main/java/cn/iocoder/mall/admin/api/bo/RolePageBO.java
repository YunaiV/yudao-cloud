package cn.iocoder.mall.admin.api.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 角色分页 BO
 */
@Data
@Accessors(chain = true)
public class RolePageBO implements Serializable {

    /**
     * 角色数组
     */
    private List<RoleBO> roles;
    /**
     * 总量
     */
    private Integer count;

}
