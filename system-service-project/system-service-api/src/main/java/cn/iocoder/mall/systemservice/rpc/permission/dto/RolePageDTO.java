package cn.iocoder.mall.systemservice.rpc.permission.dto;

import cn.iocoder.common.framework.vo.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* 角色分页 DTO
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class RolePageDTO extends PageParam {

    /**
    * 角色名
    */
    private String name;

}
