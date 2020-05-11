package cn.iocoder.mall.system.biz.dto.errorcode;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author ding
 */
@Data
@Accessors(chain = true)
public class ErrorCodeDTO {
    /**
     * 错误码编号
     */
    private Integer id;
    /**
     * 错误码编码
     */
    private Integer code;
    /**
     * 错误码错误信息
     */
    private String message;
    /**
     * 错误码类型
     */
    private Integer type;
}
