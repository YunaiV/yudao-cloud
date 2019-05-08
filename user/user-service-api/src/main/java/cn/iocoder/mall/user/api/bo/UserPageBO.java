package cn.iocoder.mall.user.api.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
public class UserPageBO implements Serializable {

    /**
     * 用户数组
     */
    private List<UserBO> list;
    /**
     * 总量
     */
    private Integer total;

}
