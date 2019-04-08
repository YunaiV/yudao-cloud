package cn.iocoder.mall.user.api.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
public class UserBO implements Serializable {

    /**
     * 用户编号
     */
    private Integer id;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 账号状态
     *
     * 1 - 开启
     * 2 - 禁用
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date createTime;

}
