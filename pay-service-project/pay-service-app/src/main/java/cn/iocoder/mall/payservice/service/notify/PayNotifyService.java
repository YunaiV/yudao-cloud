package cn.iocoder.mall.payservice.service.notify;

import cn.iocoder.mall.payservice.dal.mysql.dataobject.notify.PayNotifyTaskDO;
import cn.iocoder.mall.payservice.dal.mysql.dataobject.refund.PayRefundDO;
import cn.iocoder.mall.payservice.dal.mysql.dataobject.transaction.PayTransactionDO;
import cn.iocoder.mall.payservice.dal.mysql.dataobject.transaction.PayTransactionExtensionDO;

/**
 * 支付通知 Service 接口
 */
public interface PayNotifyService {

    // TODO 芋艿：后续优化下，不要暴露 entity 出来
    void addPayRefundNotifyTask(PayRefundDO refund);

    // TODO 芋艿：后续优化下，不要暴露 entity 出来
    void addPayTransactionNotifyTask(PayTransactionDO transaction, PayTransactionExtensionDO extension);

    // TODO 芋艿：后续优化下，不要暴露 entity 出来
    void sendNotifyMessage(PayNotifyTaskDO notifyTask);

}
