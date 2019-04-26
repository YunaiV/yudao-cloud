package cn.iocoder.mall.pay.biz.dao;

import cn.iocoder.mall.pay.biz.dataobject.PayNotifyLogDO;
import org.springframework.stereotype.Repository;

@Repository
public interface PayTransactionNotifyLogMapper {

    void insert(PayNotifyLogDO entity);

}
