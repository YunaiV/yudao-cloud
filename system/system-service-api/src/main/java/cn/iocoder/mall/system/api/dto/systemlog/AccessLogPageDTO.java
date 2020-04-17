package cn.iocoder.mall.system.api.dto.systemlog;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * @author:ycjx
 * @descriptio
 * @create:2019-06-23 16:53
 */
@Data
@Accessors(chain = true)
public class AccessLogPageDTO {


    /**
     * 用户id
     */
    private Integer userId;

    @NotNull(message = "页码不能为空")
    private Integer pageNo;
    @NotNull(message = "每页条数不能为空")
    private Integer pageSize;

}
