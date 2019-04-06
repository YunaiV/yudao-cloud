package cn.iocoder.mall.user.api.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class UserPageBO {

    /**
     * 用户数组
     */
    private List<UserBO> users;
    /**
     * 总量
     */
    private Integer count;

}
