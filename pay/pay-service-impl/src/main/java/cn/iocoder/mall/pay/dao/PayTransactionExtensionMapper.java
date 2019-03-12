package cn.iocoder.mall.pay.dao;

import cn.iocoder.mall.pay.dataobject.PayTransactionExtensionDO;
import org.springframework.stereotype.Repository;

@Repository
public interface PayTransactionExtensionMapper {

    void insert(PayTransactionExtensionDO payTransactionExtensionDO);

}