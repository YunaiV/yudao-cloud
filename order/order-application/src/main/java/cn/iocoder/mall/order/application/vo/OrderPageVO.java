package cn.iocoder.mall.order.application.vo;

import cn.iocoder.mall.order.api.dto.OrderBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 订单分页 vo
 *
 * @author Sin
 * @time 2019-03-27 21:23
 */
@ApiModel("订单VO")
public class OrderPageVO implements Serializable {

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

    @Override
    public String toString() {
        return "OrderPageVO{" +
                "pageNo=" + pageNo +
                ", total=" + total +
                ", orders=" + orders +
                '}';
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public OrderPageVO setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
        return this;
    }

    public Integer getTotal() {
        return total;
    }

    public OrderPageVO setTotal(Integer total) {
        this.total = total;
        return this;
    }

    public List<OrderBO> getOrders() {
        return orders;
    }

    public OrderPageVO setOrders(List<OrderBO> orders) {
        this.orders = orders;
        return this;
    }
}
