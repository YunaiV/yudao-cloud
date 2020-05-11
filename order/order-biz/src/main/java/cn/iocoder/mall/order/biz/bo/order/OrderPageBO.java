package cn.iocoder.mall.order.biz.bo.order;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 订单分页
 *
 * @author Sin
 * @time 2019-03-27 21:27
 */
@Data
@Accessors(chain = true)
public class OrderPageBO implements Serializable {

    /**
     * 总条数
     */
    private Integer total;
    /**
     * 订单列表
     */
    private List<OrderBO> orders;

}
