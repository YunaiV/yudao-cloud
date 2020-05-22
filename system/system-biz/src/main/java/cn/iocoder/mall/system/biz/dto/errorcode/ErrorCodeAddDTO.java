package cn.iocoder.mall.system.biz.dto.errorcode;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 角色模块 - 添加角色 DTO
 */
@Data
@Accessors(chain = true)
public class ErrorCodeAddDTO {

    @NotNull(message = "错误码编码")
    private Integer code;

    @NotEmpty(message = "错误码错误信息")
    private String message;

    @NotNull(message = "错误码分组id")
    private Integer group;

    /**
     * 错误码备注
     */
    private String remark;
}
