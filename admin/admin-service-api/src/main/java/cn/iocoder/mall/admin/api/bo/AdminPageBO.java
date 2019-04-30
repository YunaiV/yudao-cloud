package cn.iocoder.mall.admin.api.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 管理员分页 BO
 */
@Data
@Accessors(chain = true)
public class AdminPageBO implements Serializable {

    /**
     * 管理员数组
     */
    private List<AdminBO> list;
    /**
     * 总量
     */
    private Integer total;

}
