package cn.iocoder.mall.order.rest.request.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 订单物流
 *
 * @author Sin
 * @time 2019-03-24 11:01
 */
@Data
@Accessors(chain = true)
@ApiModel("订单物流信息")
public class AdminsOrderLogisticsRequest implements Serializable {

    /**
     * 订单 id
     */
    @NotNull(message = "订单id不能为空!")
    @ApiModelProperty("物流id")
    private Integer id;
    /**
     * 收件区域编号
     */
    @NotNull(message = "区域编号不能为空!")
    @ApiModelProperty("区域编号")
    private String areaNo;
    /**
     * 收件人名称
     */
    @NotNull(message = "收件人姓名不能为空!")
    @ApiModelProperty("收件人姓名")
    private String name;
    /**
     * 收件手机号
     */
    @NotNull(message = "手机号不能为空!")
    @Size(max = 11, min = 11, message = "手机号在11位!")
    @ApiModelProperty("手机号")
    private String mobile;
    /**
     * 收件详细地址
     */
    @NotNull(message = "收件详细地址不能为空")
    @Size(max = 250, min = 5, message = "收件地址应该在 5 ~ 250 个字符之间")
    @ApiModelProperty("手机地址")
    private String address;
    /**
     * 物流编号
     */
    @ApiModelProperty("物流订号")
    private String logisticsNo;

}
