package cn.iocoder.mall.pay.biz.dao;

import cn.iocoder.mall.pay.biz.dataobject.PayTransactionDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Repository
public interface PayTransactionMapper {

    void insert(PayTransactionDO entity);

    int update(@Param("entity") PayTransactionDO entity,
               @Param("whereStatus") Integer whereStatus);

    int updateForRefundTotal(@Param("id") Integer id,
                             @Param("refundTotalIncr") Integer refundTotalIncr);

    PayTransactionDO selectByAppIdAndOrderId(@Param("appId") String appId,
                                             @Param("orderId") String orderId);

    PayTransactionDO selectById(@Param("id") Integer id);

    List<PayTransactionDO> selectListByIds(@Param("ids") Collection<Integer> ids);

    List<PayTransactionDO> selectListByPage(@Param("createBeginTime") Date createBeginTime,
                                            @Param("createEndTime") Date createEndTime,
                                            @Param("paymentBeginTime") Date paymentBeginTime,
                                            @Param("paymentEndTime") Date paymentEndTime,
                                            @Param("status") Integer status,
                                            @Param("hasRefund") Boolean hasRefund,
                                            @Param("payChannel") Integer payChannel,
                                            @Param("orderSubject") String orderSubject,
                                            @Param("offset") Integer offset,
                                            @Param("limit") Integer limit);

    Integer selectCountByPage(@Param("createBeginTime") Date createBeginTime,
                              @Param("createEndTime") Date createEndTime,
                              @Param("paymentBeginTime") Date paymentBeginTime,
                              @Param("paymentEndTime") Date paymentEndTime,
                              @Param("status") Integer status,
                              @Param("hasRefund") Boolean hasRefund,
                              @Param("payChannel") Integer payChannel,
                              @Param("orderSubject") String orderSubject);

}
