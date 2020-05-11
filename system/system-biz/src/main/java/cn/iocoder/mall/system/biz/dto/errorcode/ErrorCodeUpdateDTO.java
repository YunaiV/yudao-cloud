package cn.iocoder.mall.system.biz.dto.errorcode;

import cn.iocoder.mall.system.biz.enums.errorcode.ErrorCodeTypeEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * @author ding
 */
@Data
@Accessors(chain = true)
public class ErrorCodeUpdateDTO {

    /**
     * 错误码编号,内置错误码的id是没有的
     */
    // TODO FROM 芋艿 to 鱿鱼丝：必要的参数校验噢
    private Integer id;

    @NotNull(message = "错误码编码不能为空")
    private Integer code;
    /**
     * 错误码错误信息
     */
    private String message;
    /**
     * 错误码类型 {@link ErrorCodeTypeEnum}
     */
    private Integer type;
}
