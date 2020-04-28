package cn.iocoder.mall.system.rest.request.authorization;

import cn.iocoder.common.framework.vo.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@ApiModel("管理员 - 角色模块 - 分页列表 Request")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class AdminsRolePageRequest extends PageParam {

    @ApiModelProperty( value = "角色名，模糊匹配", example = "系统管理员")
    private String name;


}
