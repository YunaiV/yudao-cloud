package cn.iocoder.mall.promotion.rest.request.banner;

import cn.iocoder.common.framework.vo.PageParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * banner：更新 banner
 *
 * author: sin
 * time: 2020/5/14 15:44
 */
@Data
@Accessors(chain = true)
public class BannerListRequest extends PageParam {

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("状态")
    private Integer status;
}
