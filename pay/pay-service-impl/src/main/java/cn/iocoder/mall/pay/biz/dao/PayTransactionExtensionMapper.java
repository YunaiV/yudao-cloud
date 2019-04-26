package cn.iocoder.mall.pay.biz.dao;

import cn.iocoder.mall.pay.biz.dataobject.PayTransactionExtensionDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PayTransactionExtensionMapper {

    void insert(PayTransactionExtensionDO entity);

    int update(@Param("entity") PayTransactionExtensionDO entity,
               @Param("whereStatus") Integer whereStatus);

    PayTransactionExtensionDO selectByTransactionCode(@Param("transactionCode") String transactionCode);

    PayTransactionExtensionDO selectById(@Param("id") Integer id);

}
