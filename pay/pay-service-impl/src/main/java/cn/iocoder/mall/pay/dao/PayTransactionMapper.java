package cn.iocoder.mall.pay.dao;

import cn.iocoder.mall.pay.dataobject.PayTransactionDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PayTransactionMapper {

    void insert(PayTransactionDO entity);

    PayTransactionDO selectByAppIdAndOrderId(@Param("appId") String appId,
                                             @Param("orderId") String orderId);

}