package cn.iocoder.mall.pay.application.vo.admins;

import cn.iocoder.mall.pay.api.bo.refund.PayRefundBO;
import cn.iocoder.mall.pay.api.bo.transaction.PayTransactionBO;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 支付退款详细 VO
 */
@Data
@Accessors(chain = true)
public class AdminsPayRefundDetailVO extends PayRefundBO { // TODO 芋艿，暂时偷懒下

    /**
     * 支付交易
     */
    private PayTransactionBO transaction;

}

