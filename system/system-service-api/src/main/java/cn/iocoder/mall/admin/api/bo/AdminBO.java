package cn.iocoder.mall.admin.api.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 管理员 BO
 */
@Data
@Accessors(chain = true)
public class AdminBO implements Serializable {

    /**
     * 管理员编号
     */
    private Integer id;
    /**
     * 登陆账号
     */
    private String username;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 账号状态
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date createTime;

}
