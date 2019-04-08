package cn.iocoder.mall.order.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 订单收件人信息
 *
 * @author Sin
 * @time 2019-03-17 20:22
 */
@Data
@Accessors(chain = true)
public class OrderLogisticsUpdateDTO implements Serializable {

    /**
     * 订单 id
     */
    private Integer id;
    /**
     * 收件区域编号
     */
    @NotNull
    private String areaNo;
    /**
     * 收件人名称
     */
    @NotNull
    private String name;
    /**
     * 收件手机号
     */
    @NotNull
    @Size(max = 11, min = 11)
    // TODO: 2019-03-17 Sin 此处需要添加 手机号校验，需要添加新的注解
    private String mobile;
    /**
     * 收件详细地址
     */
    @NotNull
    @Size(max = 250, min = 10, message = "收件地址应该在 10 ~ 250 个字符之间")
    private String address;
    /**
     * 物流编号
     */
    private String logisticsNo;
}
