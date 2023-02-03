package cn.iocoder.yudao.module.system.controller.admin.permission.vo.menu;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 菜单列表 Request VO")
@Data
public class MenuListReqVO {

    @Schema(description = "菜单名称", example = "芋道", notes = "模糊匹配")
    private String name;

    @Schema(description = "展示状态", example = "1", notes = "参见 CommonStatusEnum 枚举类")
    private Integer status;

}