package cn.iocoder.mall.system.biz.dto.system;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * 访问日志添加 DTO
 */
@Data
@Accessors(chain = true)
public class AccessLogPageDTO { // TODO FROM 芋艿 to 2447007062：有个 PageParams 类哈，可以继承


    /**
     *  账号编号
     */
    private Integer accountId;

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
