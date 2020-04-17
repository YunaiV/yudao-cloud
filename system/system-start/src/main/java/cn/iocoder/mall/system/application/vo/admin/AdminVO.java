package cn.iocoder.mall.system.application.vo.admin;

import cn.iocoder.mall.system.api.bo.admin.AdminBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@ApiModel("管理员 VO")
@Data
@Accessors(chain = true)
public class AdminVO extends AdminBO {

    private List<Role> roles;

    private Deptment deptment;

    @ApiModel("管理员 VO - 角色")
    @Data
    @Accessors(chain = true)
    public static class Role {

        @ApiModelProperty(value = "角色编号", required = true, example = "1")
        private Integer id;

        @ApiModelProperty(value = "角色名", required = true, example = "码神")
        private String name;

    }

    @ApiModel("管理员 VO - 部门")
    @Data
    @Accessors(chain = true)
    @AllArgsConstructor
    public static class Deptment {

        @ApiModelProperty(value = "部门编号", required = true, example = "1")
        private Integer id;

        @ApiModelProperty(value = "部门名称", required = true, example = "研发部")
        private String name;


    }

}
