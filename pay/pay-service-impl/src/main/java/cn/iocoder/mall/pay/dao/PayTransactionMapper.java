package cn.iocoder.mall.pay.dao;

import cn.iocoder.mall.pay.dataobject.PayTransactionDO;
import org.springframework.stereotype.Repository;

@Repository
public interface PayTransactionMapper {

    void insert(PayTransactionDO entity);

}