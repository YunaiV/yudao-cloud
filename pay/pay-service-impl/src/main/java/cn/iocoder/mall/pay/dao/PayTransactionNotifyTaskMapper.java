package cn.iocoder.mall.pay.dao;

import cn.iocoder.mall.pay.dataobject.PayTransactionNotifyTaskDO;
import org.springframework.stereotype.Repository;

@Repository
public interface PayTransactionNotifyTaskMapper {

    void insert(PayTransactionNotifyTaskDO entity);

    int update(PayTransactionNotifyTaskDO entity);

}