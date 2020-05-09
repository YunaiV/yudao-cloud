package cn.iocoder.mall.system.biz.bo.errorcode;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 错误码模块 - 错误码信息 BO
 * @author ding
 */
@Data
@Accessors(chain = true)
public class ErrorCodeBO {
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
     * 添加时间
     */
    private Date createTime;
}
