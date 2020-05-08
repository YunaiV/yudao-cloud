package cn.iocoder.mall.user.rest.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 用户地址 add
 *
 * @author Sin
 * @time 2019-04-06 13:25
 */
@Data
@Accessors(chain = true)
@ApiModel("用户地址(添加)")
public class UserAddressAddRequest implements Serializable {

    @NotNull
    @ApiModelProperty("收件区域编号")
    private String areaNo;

    @NotNull
    @ApiModelProperty("收件人名称")
    private String name;

    @NotNull
    @ApiModelProperty("收件手机号")
    private String mobile;

    @NotNull
    @ApiModelProperty("收件详细地址")
    private String address;

    @NotNull
    @ApiModelProperty("是否默认 1 不是 2 是")
    private Integer hasDefault;
}
