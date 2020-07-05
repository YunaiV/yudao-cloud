package cn.iocoder.mall.systemservice.service.admin.bo;

import cn.iocoder.common.framework.enums.CommonStatusEnum;
import cn.iocoder.common.framework.validator.InEnum;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 管理员修改 BO
 */
@Data
@Accessors(chain = true)
public class AdminUpdateBO {

    /**
     * 管理员编号
     */
    @NotNull(message = "管理员编号不能为空")
    private Integer id;
    /**
     * 昵称
     */
    @Length(max = 10, message = "真实名字长度最大为 10 位")
    private String name;
    /**
     * 部门编号
     */
    @NotNull(message = "部门不能为空")
    private Integer departmentId;
    /**
     * 状态
     */
    @InEnum(value = CommonStatusEnum.class, message = "修改状态必须是 {value}")
    private Integer status;

    /**
     * 登录账号
     */
    @Length(min = 5, max = 16, message = "账号长度为 5-16 位")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "账号格式为数字以及字母")
    private String username;
    /**
     * 密码
     */
    @Length(min = 4, max = 16, message = "密码长度为 4-16 位")
    private String password;

}
