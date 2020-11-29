package cn.iocoder.mall.shopweb.controller.user.vo.address;

import cn.iocoder.common.framework.validator.InEnum;
import cn.iocoder.mall.userservice.enums.address.UserAddressType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@ApiModel("用户收件地址更新 Request VO")
@Data
public class UserAddressUpdateReqVO {

    @ApiModelProperty(value = "收件地址编号", required = true, example = "1024")
    @NotNull(message = "收件地址编号不能为空")
    private Integer id;
    @ApiModelProperty(value = "收件人名称", required = true, example = "帅艿艿")
    @NotEmpty(message = "收件人名称不能为空")
    private String name;
    @ApiModelProperty(value = "手机号", required = true, example = "15601691300")
    @NotEmpty(message = "手机号不能为空")
    private String mobile;
    @ApiModelProperty(value = "区域编号", required = true, example = "610632")
    @NotNull(message = "地区编码不能为空")
    private Integer areaCode;
    @ApiModelProperty(value = "收件详细地址", required = true, example = "芋道源码 233 号 666 室")
    @NotEmpty(message = "收件详细地址不能为空")
    private String detailAddress;
    @ApiModelProperty(value = "地址类型", required = true, example = "1", notes = "参见 UserAddressType 枚举类")
    @NotNull(message = "地址类型不能为空")
    @InEnum(value = UserAddressType.class, message = "地址类型必须是 {value}")
    private Integer type;

}
