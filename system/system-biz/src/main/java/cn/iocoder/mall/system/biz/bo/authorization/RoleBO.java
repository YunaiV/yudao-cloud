package cn.iocoder.mall.system.biz.bo.authorization;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 授权模块 - 角色信息 BO
 */
@Data
@Accessors(chain = true)
public class RoleBO {

    /**
     * 角色编号
     */
    private Integer id;
    /**
     * 角色名字
     */
    private String name;
    /**
     * 角色编码
     */
    private String code;
    /**
     * 添加时间
     */
    private Date createTime;

}
