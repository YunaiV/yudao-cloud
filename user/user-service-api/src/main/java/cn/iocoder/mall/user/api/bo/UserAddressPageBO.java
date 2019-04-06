package cn.iocoder.mall.user.api.bo;

import java.io.Serializable;
import java.util.List;

/**
 * 用户地址 page
 *
 * @author Sin
 * @time 2019-04-06 13:56
 */
public class UserAddressPageBO implements Serializable {

    /**
     * 总量
     */
    private Integer total;
    /**
     * 地址
     */
    private List<UserAddressBO> list;

    @Override
    public String toString() {
        return "UserAddressPageBO{" +
                "total=" + total +
                ", list=" + list +
                '}';
    }

    public Integer getTotal() {
        return total;
    }

    public UserAddressPageBO setTotal(Integer total) {
        this.total = total;
        return this;
    }

    public List<UserAddressBO> getList() {
        return list;
    }

    public UserAddressPageBO setList(List<UserAddressBO> list) {
        this.list = list;
        return this;
    }
}
