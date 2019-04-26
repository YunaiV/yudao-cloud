package cn.iocoder.mall.pay.biz.dao;

import cn.iocoder.mall.pay.biz.dataobject.PayRefundDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PayRefundMapper {

    void insert(PayRefundDO entity);

    int update(@Param("entity") PayRefundDO entity,
               @Param("whereStatus") Integer whereStatus);

    PayRefundDO selectById(@Param("id") Integer id);

    PayRefundDO selectByRefundCode(@Param("refundCode") String refundCode);

}
