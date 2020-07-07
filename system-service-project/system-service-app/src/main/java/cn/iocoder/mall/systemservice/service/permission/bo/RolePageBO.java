package cn.iocoder.mall.systemservice.service.permission.bo;

import cn.iocoder.common.framework.vo.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* 角色分页 BO
*/
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class RolePageBO extends PageParam {

    /**
    * 角色名，模糊匹配
    */
    private String name;

}
