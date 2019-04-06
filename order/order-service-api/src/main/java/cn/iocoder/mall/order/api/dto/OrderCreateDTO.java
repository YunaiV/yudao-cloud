package cn.iocoder.mall.order.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * 订单创建
 *
 * @author Sin
 * @time 2019-03-16 14:42
 */
@Data
@Accessors(chain = true)
public class OrderCreateDTO implements Serializable {

    /**
     * 用户id
     */
    private Integer userId;
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
    private String mobile;
    /**
     * 收件详细地址
     */
    @NotNull
    private String address;
    /**
     * 备注
     */
    private String remark;

    ///
    /// order item

    @NotNull
    @Size(max = 1000, min = 1)
    private List<OrderCreateItemDTO> orderItems;
}
