package cn.iocoder.mall.order.rest.request.admin;

import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 订单退货 查询 po
 *
 * @author Sin
 * @time 2019-05-06 21:36
 */
@Data
@Accessors(chain = true)
public class AdminsOrderReturnQueryRequest implements Serializable {

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
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startCreateTime;
    /**
     * 创建时间 - 结束
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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
    @NotNull
    private Integer index;
    /**
     * 分页大小
     */
    @NotNull
    private Integer pageSize;
}
