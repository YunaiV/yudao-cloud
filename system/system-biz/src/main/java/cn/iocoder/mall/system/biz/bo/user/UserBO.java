package cn.iocoder.mall.system.biz.bo.user;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * TODO 注释
 */
@Data
@Accessors(chain = true)
public class UserBO {

    /**
     * 用户编号
     */
    private Integer id;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 头像
     */
    private String avatar;

}
