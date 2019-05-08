package cn.iocoder.mall.pay.application.vo.admins;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 支付退款 Page VO
 */
@Data
@Accessors(chain = true)
public class AdminsPayRefundPageVO implements Serializable {

    /**
     * 支付退款数组
     */
    private List<AdminsPayRefundDetailVO> list;
    /**
     * 总量
     */
    private Integer total;

}
