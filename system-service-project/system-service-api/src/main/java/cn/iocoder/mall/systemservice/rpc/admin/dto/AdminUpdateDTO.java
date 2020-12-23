package cn.iocoder.mall.systemservice.rpc.admin.dto;

import cn.iocoder.common.framework.enums.CommonStatusEnum;
import cn.iocoder.common.framework.validator.InEnum;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 管理员修改 DTO
 */
@Data
@Accessors(chain = true)
public class AdminUpdateDTO implements Serializable {

    /**
     * 管理员编号
     */
    @NotNull(message = "管理员编号不能为空")
    private Integer id;
    /**
     * 真实名字
     */
    @Length(max = 10, message = "真实名字长度最大为 10 位")
    private String name;
    /**
     * 部门编号
     */
    private Integer departmentId;
    /**
     * 状态
     */
    @InEnum(value = CommonStatusEnum.class, message = "修改状态必须是 {value}")
    private Integer status;

    /**
     * 登录账号
     */
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "账号格式为数字以及字母")
    private String username;
    /**
     * 密码
     */
    @Length(min = 4, max = 16, message = "密码长度为 4-16 位")
    private String password;

}
