package cn.iocoder.mall.system.biz.dto.errorcode;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * 只可以删除自定义错误码
 * @author ding
 */
@Data
@Accessors(chain = true)
public class ErrorCodeDeleteDTO {
    @NotNull(message = "错误码编号不能为空")
    private Integer id;
}
