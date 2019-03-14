package cn.iocoder.mall.pay.biz.dao;

import cn.iocoder.mall.pay.biz.dataobject.PayTransactionNotifyLogDO;
import org.springframework.stereotype.Repository;

@Repository
public interface PayTransactionNotifyLogMapper {

    void insert(PayTransactionNotifyLogDO entity);

}