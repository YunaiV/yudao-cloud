package cn.iocoder.mall.managementweb.controller.promotion.brand.vo;

import cn.iocoder.common.framework.enums.CommonStatusEnum;
import cn.iocoder.common.framework.validator.InEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@ApiModel("Banner 创建 Request VO")
@Data
public class BannerCreateReqVO {

    @ApiModelProperty(value = "标题",  required = true, example = "活动 A")
    @NotEmpty(message = "标题不能为空")
    @Length(min = 2, max = 32, message = "标题长度为 2-32 位")
    private String title;
    @ApiModelProperty(value = "跳转链接", required = true, example = "http://www.baidu.com")
    @NotEmpty(message = "跳转链接不能为空")
    @URL(message = "跳转链接格式不正确")
    @Length(max = 255, message = "跳转链接最大长度为 255 位")
    private String url;
    @ApiModelProperty(value = "跳转链接", required = true, example = "http://www.iocoder.cn/01.jpg")
    @NotEmpty(message = "跳转链接不能为空")
    @URL(message = "图片链接格式不正确")
    @Length(max = 255, message = "图片链接最大长度为 255 位")
    private String picUrl;
    @ApiModelProperty(value = "排序", required = true, example = "10")
    @NotNull(message = "排序不能为空")
    private Integer sort;
    @ApiModelProperty(value = "状态", required = true, example = "1")
    @NotNull(message = "状态不能为空")
    @InEnum(value = CommonStatusEnum.class, message = "修改状态必须是 {value}")
    private Integer status;
    @ApiModelProperty(value = "备注", example = "这个活动很牛逼")
    @Length(max = 255, message = "备注最大长度为 255 位")
    private String memo;

}
