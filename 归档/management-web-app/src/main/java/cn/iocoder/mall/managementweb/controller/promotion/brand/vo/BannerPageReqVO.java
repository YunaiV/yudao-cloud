package cn.iocoder.mall.managementweb.controller.promotion.brand.vo;

import cn.iocoder.common.framework.vo.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@ApiModel("Banner 分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class BannerPageReqVO extends PageParam {

    @ApiModelProperty(value = "标题",  required = true, example = "活动 A")
    private String title;

}
