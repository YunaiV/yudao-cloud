package cn.iocoder.mall.system.biz.dto.authorization;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * 资源模块 - 删除资源 DTO
 */
@Data
@Accessors(chain = true)
public class ResourceDeleteDTO {

    @NotNull(message = "管理员编号不能为空")
    private Integer adminId;

    @ApiModelProperty(value = "资源编号", required = true, example = "1")
    @NotNull(message = "资源编号不能为空")
    private Integer id;

}
