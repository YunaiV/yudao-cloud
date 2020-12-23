package cn.iocoder.mall.managementweb.controller.promotion.brand.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@ApiModel("Banner VO")
@Data
@Accessors(chain = true)
public class BannerRespVO {

    @ApiModelProperty(value = "Banner 编号", required = true, example = "1")
    private Integer id;
    @ApiModelProperty(value = "标题", required = true, example = "活动 A")
    private String title;
    @ApiModelProperty(value = "跳转链接", required = true, example = "http://www.baidu.com")
    private String url;
    @ApiModelProperty(value = "图片链接", required = true, example = "http://www.iocoder.cn/01.jpg")
    private String picUrl;
    @ApiModelProperty(value = "排序", required = true, example = "10")
    private Integer sort;
    @ApiModelProperty(value = "状态", required = true, example = "1")
    private Integer status;
    @ApiModelProperty(value = "备注", example = "这个活动很牛逼")
    private String memo;
    @ApiModelProperty(value = "创建时间", required = true, example = "时间戳格式")
    private Date createTime;

}
