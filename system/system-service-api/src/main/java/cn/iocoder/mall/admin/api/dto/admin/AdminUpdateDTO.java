package cn.iocoder.mall.admin.api.dto.admin;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 管理员更新 DTO
 */
@Data
@Accessors(chain = true)
@ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "管理员编号", required = true, example = "1"),
        @ApiImplicitParam(name = "username", value = "账号", required = true, example = "15601691300"),
        @ApiImplicitParam(name = "nickname", value = "昵称", required = true, example = "小王"),
        @ApiImplicitParam(name = "password", value = "密码", example = "buzhidao"),
})
public class AdminUpdateDTO implements Serializable {

    /**
     * 管理员编号
     */
    @NotNull(message = "管理员编号不能为空")
    private Integer id;
    /**
     * 登陆账号
     */
    @NotEmpty(message = "登陆账号不能为空")
    @Length(min = 6, max = 16, message = "账号长度为 6-16 位")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "账号格式为数字以及字母")
    private String username;
    /**
     * 昵称
     */
    @NotEmpty(message = "昵称不能为空")
    @Length(max = 10, message = "昵称长度最大为 10 位")
    private String nickname;
    /**
     * 密码
     */
    @Length(min = 6, max = 16, message = "密码长度为 6-16 位")
    private String password;

}
