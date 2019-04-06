package cn.iocoder.mall.user.api.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 用户地址 page
 *
 * @author Sin
 * @time 2019-04-06 13:56
 */
@Data
@Accessors(chain = true)
public class UserAddressPageBO implements Serializable {

    /**
     * 总量
     */
    private Integer total;
    /**
     * 地址
     */
    private List<UserAddressBO> list;

}
