package cn.iocoder.mall.pay.biz.dao;

import cn.iocoder.mall.pay.biz.dataobject.PayTransactionNotifyTaskDO;
import org.springframework.stereotype.Repository;

@Repository
public interface PayTransactionNotifyTaskMapper {

    void insert(PayTransactionNotifyTaskDO entity);

    int update(PayTransactionNotifyTaskDO entity);

}