package cn.iocoder.mall.promotion.rest.request.banner;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * banner：更新 status
 *
 * author: sin
 * time: 2020/5/14 15:44
 */
@Data
@Accessors(chain = true)
public class BannerUpdateStatusRequest implements Serializable {

    @NotNull
    @ApiModelProperty("banner编号")
    private Integer bannerId;

    @NotNull
    @ApiModelProperty("status状态")
    private Integer status;
}
