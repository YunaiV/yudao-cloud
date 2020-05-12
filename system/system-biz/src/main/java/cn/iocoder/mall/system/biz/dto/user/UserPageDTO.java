package cn.iocoder.mall.system.biz.dto.user;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * @Author: jiangweifan
 * @Date: 2020/5/12
 * @Description: 用户信息 - 用户分页列表DTO
 */
@Data
@Accessors(chain = true)
public class UserPageDTO {

    /**
     * 昵称，模糊匹配
     */
    private String nickname;

    /**
     * 状态。1 - 开启；2 - 禁用
     */
    private Integer status;

    /**
     * 页码，从 1 开始
     */
    @NotNull(message = "页码不能为空")
    private Integer pageNo;

    /**
     * 每页条数
     */
    @NotNull(message = "每页条数不能为空")
    private Integer pageSize;
}
