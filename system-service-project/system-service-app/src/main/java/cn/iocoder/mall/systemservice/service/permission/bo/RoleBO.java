package cn.iocoder.mall.systemservice.service.permission.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
* 角色 BO
*/
@Data
@Accessors(chain = true)
public class RoleBO {

    /**
     * 角色编号
     */
    private Integer id;
    /**
     * 角色名
     */
    private String name;
    /**
     * 角色编码
     */
    private String code;
    /**
     * 角色类型
     */
    private Integer type;
    /**
     * 创建管理员编号
     */
    private Integer createAdminId;
    /**
     * 创建时间
     */
    private Date createTime;

}
