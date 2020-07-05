package cn.iocoder.mall.systemservice.service.admin.bo;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 管理员添加 BO
 */
@Data
@Accessors(chain = true)
public class AdminCreateBO {

    /**
     * 昵称
     */
    @NotEmpty(message = "真实名字不能为空")
    @Length(max = 10, message = "真实名字长度最大为 10 位")
    private String name;
    /**
     * 部门编号
     */
    @NotNull(message = "部门不能为空")
    private Integer departmentId;

    /**
     * 登录账号
     */
    @NotEmpty(message = "登陆账号不能为空")
    @Length(min = 5, max = 16, message = "账号长度为 5-16 位")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "账号格式为数字以及字母")
    private String username;
    /**
     * 密码
     */
    @NotEmpty(message = "密码不能为空")
    @Length(min = 4, max = 16, message = "密码长度为 4-16 位")
    private String password;

    /**
     * 创建管理员编号
     */
    @NotNull(message = "创建管理员编号不能为空")
    private Integer createAdminId;
    /**
     * 创建 IP
     */
    @NotNull(message = "创建 IP 不能为空")
    private String createIp;

}
