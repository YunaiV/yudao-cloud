package cn.iocoder.mall.order.rest.request.admin;

import cn.iocoder.mall.order.biz.bo.order.OrderBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 订单分页 vo
 *
 * @author Sin
 * @time 2019-03-27 21:23
 */
@Data
@Accessors(chain = true)
@ApiModel("订单VO")
public class OrderPageRequest implements Serializable {

    /**
     * 分页
     */
    @ApiModelProperty("分页下表当前")
    private Integer pageNo;
    /**
     * 总条数
     */
    @ApiModelProperty("总条数")
    private Integer total;
    /**
     * 订单 list
     */
    @ApiModelProperty("订单信息")
    private List<OrderBO> orders;
}
