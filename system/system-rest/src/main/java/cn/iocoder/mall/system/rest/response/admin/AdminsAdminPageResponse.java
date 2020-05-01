package cn.iocoder.mall.system.rest.response.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

@ApiModel("管理员 - 管理员模块 - 管理员分页信息 Response")
@Data
@Accessors(chain = true)
public class AdminsAdminPageResponse {

    @ApiModel("角色")
    @Data
    @Accessors(chain = true)
    public static class Role {

        @ApiModelProperty(value = "角色编号", required = true, example = "1")
        private Integer id;

        @ApiModelProperty(value = "角色名", required = true, example = "码神")
        private String name;

    }

    @ApiModel("部门")
    @Data
    @Accessors(chain = true)
    public static class Department {

        @ApiModelProperty(value = "部门编号", required = true, example = "1")
        private Integer id;

        @ApiModelProperty(value = "部门名称", required = true, example = "研发部")
        private String name;

    }

    @ApiModel("账号")
    @Data
    @Accessors(chain = true)
    public static class Account {

        @ApiModelProperty(value = "账号编号", required = true, example = "1")
        private Integer id;

        @ApiModelProperty(value = "登陆账号", required = true, example = "15601691300")
        private String username;

    }

    @ApiModelProperty(value = "管理员编号", required = true, example = "1")
    private Integer id;
    @ApiModelProperty(value = "真实名字", required = true, example = "小王")
    private String name;
    @ApiModelProperty(value = "创建时间", required = true, example = "时间戳格式")
    private Date createTime;
    @ApiModelProperty(value = "在职状态", required = true, example = "1", notes = "见 AdminStatusEnum 枚举")
    private Integer status;

    /**
     * 账号
     */
    private Account account;

    /**
     * 角色列表
     */
    private List<Role> roles;

    /**
     * 所在部门
     */
    private Department department;

}
