package cn.iocoder.mall.system.biz.dto.authorization;

import cn.iocoder.common.framework.vo.PageParam;
import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty( value = "角色名，模糊匹配", example = "系统管理员")
    private String name;

}
