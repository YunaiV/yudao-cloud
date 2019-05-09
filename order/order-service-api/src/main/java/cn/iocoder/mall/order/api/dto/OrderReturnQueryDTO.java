package cn.iocoder.mall.order.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单退货 查询 po
 *
 * @author Sin
 * @time 2019-05-06 21:36
 */
@Data
@Accessors(chain = true)
public class OrderReturnQueryDTO implements Serializable {

    /**
     *
     * 订单id
     */
    private Integer orderId;
    /**
     * 订单编号
     */
    private Integer orderNo;
    /**
     * 服务号
     */
    private String serviceNumber;
    /**
     * 创建时间 - 开始
     */
    private Date startCreateTime;
    /**
     * 创建时间 - 结束
     */
    private Date endCreateTime;
    /**
     * 状态
     */
    private Integer status;

    ///
    /// 分页信息

    /**
     * 分页 index
     */
    private Integer index;
    /**
     * 分页大小
     */
    private Integer pageSize;
}
