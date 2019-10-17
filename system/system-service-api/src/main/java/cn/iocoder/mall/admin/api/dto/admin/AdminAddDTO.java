package cn.iocoder.mall.admin.api.dto.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@ApiModel("管理员添加 DTO")
@Data
@Accessors(chain = true)
public class AdminAddDTO implements Serializable {

    @ApiModelProperty(value = "登陆账号", required = true, example = "15601691300")
    @NotEmpty(message = "登陆账号不能为空")
    @Length(min = 5, max = 16, message = "账号长度为 5-16 位")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "账号格式为数字以及字母")
    private String username;

    @ApiModelProperty(value = "昵称", required = true, example = "小王")
    @NotEmpty(message = "昵称不能为空")
    @Length(max = 10, message = "昵称长度最大为 10 位")
    private String nickname;

    @ApiModelProperty(value = "密码", required = true, example = "buzhidao")
    @NotEmpty(message = "密码不能为空")
    @Length(min = 4, max = 16, message = "密码长度为 4-16 位")
    private String password;

    @ApiModelProperty(value = "部门ID", required = true, example = "1")
    @NotNull(message = "部门不能为空")
    private Integer deptmentId;

}
