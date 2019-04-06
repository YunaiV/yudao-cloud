package cn.iocoder.mall.promotion.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * Banner 分页 DTO
 */
@Data
@Accessors(chain = true)
public class BannerPageDTO {

    /**
     * 标题，模糊匹配
     */
    private String title;

    @NotNull(message = "页码不能为空")
    private Integer pageNo;
    @NotNull(message = "每页条数不能为空")
    private Integer pageSize;

}
