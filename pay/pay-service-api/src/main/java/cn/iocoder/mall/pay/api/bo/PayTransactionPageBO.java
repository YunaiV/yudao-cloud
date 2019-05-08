package cn.iocoder.mall.pay.api.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 支付交易 Page BO
 */
@Data
@Accessors(chain = true)
public class PayTransactionPageBO implements Serializable {

    /**
     * 支付交易数组
     */
    private List<PayTransactionBO> list;
    /**
     * 总量
     */
    private Integer total;

}
