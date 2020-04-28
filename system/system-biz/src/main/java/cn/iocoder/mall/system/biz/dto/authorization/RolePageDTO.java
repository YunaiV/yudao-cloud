package cn.iocoder.mall.system.biz.dto.authorization;

import cn.iocoder.common.framework.vo.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 角色模块 - 角色分页 DTO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class RolePageDTO extends PageParam {

    /**
     * 角色名，模糊匹配
     */
    private String name;

}
