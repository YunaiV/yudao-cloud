package cn.iocoder.mall.systemservice.rpc.permission.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;

/**
 * 权限校验 DTO
 */
@Data
@Accessors(chain = true)
public class PermissionCheckDTO implements Serializable {

    @NotNull(message = "管理员编号不能为空")
    private Integer adminId;
    @NotNull(message = "权限不能为空")
    private Collection<String> permissions;

}
