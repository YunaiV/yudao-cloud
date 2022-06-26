package cn.iocoder.mall.shopweb.controller.user.vo.address;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@ApiModel("用户收件地址 Response VO")
@Data
public class UserAddressRespVO {

    @ApiModelProperty(value = "收件地址编号", required = true, example = "1024")
    private Integer id;
    @ApiModelProperty(value = "用户编号", required = true, example = "2048")
    private Integer userId;
    @ApiModelProperty(value = "收件人名称", required = true, example = "帅艿艿")
    private String name;
    @ApiModelProperty(value = "手机号", required = true, example = "15601691300")
    private String mobile;
    @ApiModelProperty(value = "区域编号", required = true, example = "610632")
    private Integer areaCode;
    @ApiModelProperty(value = "收件详细地址", required = true, example = "芋道源码 233 号 666 室")
    private String detailAddress;
    @ApiModelProperty(value = "地址类型", required = true, example = "1", notes = "参见 UserAddressType 枚举类")
    private Integer type;
    @ApiModelProperty(value = "创建时间", required = true)
    private Date createTime;

}

