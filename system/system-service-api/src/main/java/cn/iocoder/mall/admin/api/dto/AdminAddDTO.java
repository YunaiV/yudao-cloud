package cn.iocoder.mall.admin.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 管理员添加 DTO
 */
@Data
@Accessors(chain = true)
public class AdminAddDTO implements Serializable {

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
    @NotEmpty(message = "密码不能为空")
    @Length(min = 6, max = 16, message = "密码长度为 6-16 位")
    private String password;

}
