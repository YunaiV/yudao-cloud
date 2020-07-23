package cn.iocoder.mall.userweb.controller.address.vo;

import lombok.*;
import io.swagger.annotations.*;
import java.util.*;

@ApiModel("用户收件地址 Response VO")
@Data
public class UserAddressRespVO {

    @ApiModelProperty(value = "收件地址编号", required = true)
    private Integer id;
    @ApiModelProperty(value = "用户编号", required = true)
    private Integer userId;
    @ApiModelProperty(value = "收件人名称", required = true)
    private String name;
    @ApiModelProperty(value = "手机号", required = true)
    private String mobile;
    @ApiModelProperty(value = "省份编号", required = true)
    private Integer provinceCode;
    @ApiModelProperty(value = "城市编号", required = true)
    private Integer cityCode;
    @ApiModelProperty(value = "区域编号", required = true)
    private Integer countyCode;
    @ApiModelProperty(value = "收件详细地址", required = true)
    private String detailAddress;
    @ApiModelProperty(value = "地址类型", required = true)
    private Integer type;
    @ApiModelProperty(value = "创建时间", required = true)
    private Date createTime;
    @ApiModelProperty(value = "最后更新时间", required = true)
    private Date updateTime;

}

