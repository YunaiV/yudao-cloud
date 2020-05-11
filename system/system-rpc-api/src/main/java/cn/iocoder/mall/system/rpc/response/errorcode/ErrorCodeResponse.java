package cn.iocoder.mall.system.rpc.response.errorcode;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 类 描 述：错误码模块
 * @author ding
 */
@ApiModel("管理员 - 错误码模块 - 查询错误码")
@Data
@Accessors(chain = true)
public class ErrorCodeResponse {
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
