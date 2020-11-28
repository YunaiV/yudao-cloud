package cn.iocoder.mall.pay.biz.dao;

import cn.iocoder.mall.pay.biz.dataobject.PayNotifyLogDO;
import org.springframework.stereotype.Repository;

@Repository
public interface PayNotifyLogMapper {

    void insert(PayNotifyLogDO entity);

}
