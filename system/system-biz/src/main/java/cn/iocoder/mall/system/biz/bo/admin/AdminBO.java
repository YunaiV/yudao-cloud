package cn.iocoder.mall.system.biz.bo.admin;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 管理员模块 - 账号信息 BO
 */
@Data
@Accessors(chain = true)
public class AdminBO {

    /**
     * 管理员编号
     */
    private Integer id;
    /**
     * 账号编号
     */
    private Integer accountId;
    /**
     * 真实名字
     */
    private String name;
    /**
     * 部门编号
     */
    private Integer departmentId;
    /**
     * 在职状态
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date createTime;

}
