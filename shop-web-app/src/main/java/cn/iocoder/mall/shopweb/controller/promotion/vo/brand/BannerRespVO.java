package cn.iocoder.mall.shopweb.controller.promotion.vo.brand;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@ApiModel("Banner Response VO")
@Data
@Accessors(chain = true)
public class BannerRespVO {

    @ApiModelProperty(value = "跳转链接", required = true, example = "http://www.baidu.com")
    private String url;
    @ApiModelProperty(value = "图片链接", required = true, example = "http://www.iocoder.cn/01.jpg")
    private String picUrl;

}
