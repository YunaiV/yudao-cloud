package cn.iocoder.mall.search.rest.response.user;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
public class UsersProductPageResponse implements Serializable {

    /**
     * 管理员数组
     */
    private List<ProductResponse> list;
    /**
     * 总量
     */
    private Integer total;

}
