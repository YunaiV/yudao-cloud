package cn.iocoder.mall.user.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
public class UserPageDTO {

    /**
     * 查询的昵称
     *
     * 模糊查询
     */
    private String nickname;
    /**
     * 状态
     */
    private Integer status;

    @NotNull(message = "页码不能为空")
    private Integer pageNo;
    @NotNull(message = "每页条数不能为空")
    private Integer pageSize;

}
