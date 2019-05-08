package cn.iocoder.mall.pay.biz.dao;

import cn.iocoder.mall.pay.biz.dataobject.PayRefundDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PayRefundMapper {

    void insert(PayRefundDO entity);

    int update(@Param("entity") PayRefundDO entity,
               @Param("whereStatus") Integer whereStatus);

    PayRefundDO selectById(@Param("id") Integer id);

    PayRefundDO selectByRefundCode(@Param("refundCode") String refundCode);

    List<PayRefundDO> selectListByPage(@Param("createBeginTime") Date createBeginTime,
                                       @Param("createEndTime") Date createEndTime,
                                       @Param("finishBeginTime") Date finishBeginTime,
                                       @Param("finishEndTime") Date finishEndTime,
                                       @Param("status") Integer status,
                                       @Param("payChannel") Integer payChannel,
                                       @Param("offset") Integer offset,
                                       @Param("limit") Integer limit);

    Integer selectCountByPage(@Param("createBeginTime") Date createBeginTime,
                              @Param("createEndTime") Date createEndTime,
                              @Param("finishBeginTime") Date finishBeginTime,
                              @Param("finishEndTime") Date finishEndTime,
                              @Param("status") Integer status,
                              @Param("payChannel") Integer payChannel);

}
