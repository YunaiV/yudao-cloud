package cn.iocoder.mall.system.biz.dto.user;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * @Author: jiangweifan
 * @Date: 2020/5/12
 * @Description: 用户信息 - 更新用户状态DTO
 */
@Data
@Accessors(chain = true)
public class UserUpdateStatusDTO {

    /**
     * 用户编号
     */
    @NotNull(message = "用户编号不能为空")
    private Integer id;

    /**
     * 用户状态，1 - 启用；2 - 禁用
     */
    @NotNull(message = "用户状态不能为空")
    private Integer status;
}
