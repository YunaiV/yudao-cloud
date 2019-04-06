package cn.iocoder.mall.user.application.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 用户地址 add
 *
 * @author Sin
 * @time 2019-04-06 14:13
 */
@ApiModel("用户地址")
@Data
@Accessors(chain = true)
public class UserAddressAddPO implements Serializable {

    /**
     * 收件区域编号
     */
    @ApiModelProperty("区域编号")
    @NotNull(message = "区域编号不能为空!")
    private String areaNo;
    /**
     * 收件人名称
     */
    @ApiModelProperty("收件人名称")
    @NotNull(message = "请填写收人信息!")
    private String name;
    /**
     * 收件手机号
     */
    @ApiModelProperty("收件手机号")
    @NotNull(message = "手机号为不能为空!")
    @Size(min = 11, max = 11, message = "手机号为 11 位!")
    private String mobile;
    /**
     * 收件详细地址
     */
    @ApiModelProperty("收件详细地址")
    @NotNull(message = "详细地址不能为空")
    @Size(min = 10, max = 100, message = "地址在 10 ~ 100 字之间!")
    private String address;

}
