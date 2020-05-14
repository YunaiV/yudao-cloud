package cn.iocoder.mall.promotion.biz.dto.banner;

import cn.iocoder.common.framework.vo.PageParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Banner 分页 DTO
 */
@Data
@Accessors(chain = true)
public class BannerListDTO extends PageParam {

    @ApiModelProperty("标题")
    @NotNull(message = "页码不能为空")
    private String title;

    @ApiModelProperty("标题")
    @NotNull(message = "页码不能为空")
    private Integer status;

}
