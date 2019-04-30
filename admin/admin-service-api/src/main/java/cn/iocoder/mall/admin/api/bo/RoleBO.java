package cn.iocoder.mall.admin.api.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色 BO
 */
@Data
@Accessors(chain = true)
public class RoleBO implements Serializable {

    /**
     * 角色编号
     */
    private Integer id;
    /**
     * 角色名字
     */
    private String name;
    /**
     * 添加时间
     */
    private Date createTime;

}
