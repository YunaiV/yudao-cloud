package cn.iocoder.yudao.module.system.api.permission.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Schema(description = "RPC 服务 - 部门的数据权限 Response DTO")
@Data
public class DeptDataPermissionRespDTO {

    @Schema(description = "是否可查看全部数据", requiredMode = Schema.RequiredMode.REQUIRED, example = "true")
    private Boolean all;

    @Schema(description = "是否可查看自己的数据", requiredMode = Schema.RequiredMode.REQUIRED, example = "true")
    private Boolean self;

    @Schema(description = "可查看的部门编号数组", requiredMode = Schema.RequiredMode.REQUIRED, example = "[1, 3]")
    private Set<Long> deptIds;

    public DeptDataPermissionRespDTO() {
        this.all = false;
        this.self = false;
        this.deptIds = new HashSet<>();
    }

}
