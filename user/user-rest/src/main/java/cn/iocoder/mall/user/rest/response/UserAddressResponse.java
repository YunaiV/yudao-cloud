package cn.iocoder.mall.user.rest.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 用户地址
 *
 * @author Sin
 * @time 2019-04-06 13:28
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "用户地址")
public class UserAddressResponse implements Serializable {

    @ApiModelProperty("编号")
    private Integer id;

    @ApiModelProperty("用户编号")
    private Integer userId;

    @ApiModelProperty("收件区域编号")
    private String areaNo;

    @ApiModelProperty("收件人名称")
    private String name;

    @ApiModelProperty("收件手机号")
    private String mobile;

    @ApiModelProperty("收件详细地址")
    private String address;

    @ApiModelProperty("是否默认")
    private Integer hasDefault;
}
