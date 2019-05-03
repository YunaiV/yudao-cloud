package cn.iocoder.mall.admin.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * 管理员分页 DTO
 */
@Data
@Accessors(chain = true)
public class AdminPageDTO {

    /**
     * 昵称，模糊匹配
     */
    private String nickname;

    @NotNull(message = "页码不能为空")
    private Integer pageNo;
    @NotNull(message = "每页条数不能为空")
    private Integer pageSize;

}
