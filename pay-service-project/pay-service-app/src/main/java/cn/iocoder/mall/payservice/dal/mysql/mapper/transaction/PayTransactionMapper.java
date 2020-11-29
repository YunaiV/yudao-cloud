package cn.iocoder.mall.payservice.dal.mysql.mapper.transaction;

import cn.iocoder.mall.payservice.dal.mysql.dataobject.transaction.PayTransactionDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface PayTransactionMapper extends BaseMapper<PayTransactionDO> {
//
//    UPDATE `transaction`
//    SET refund_total = refund_total + ${refundTotalIncr}
//    WHERE price >= refund_total + ${refundTotalIncr}
//

//    int updateForRefundTotal(@Param("id") Integer id,
//                             @Param("refundTotalIncr") Integer refundTotalIncr);

//                <if test="createBeginTime != null">
//    AND create_time >= #{createBeginTime}
//            </if>
//            <if test="createEndTime != null">
//    AND #{createEndTime} >= create_time
//            </if>
//            <if test="paymentBeginTime != null">
//    AND payment_time >= #{paymentBeginTime}
//            </if>
//            <if test="paymentEndTime != null">
//    AND #{paymentEndTime} >= payment_time
//            </if>
//            <if test="status != null">
//    AND status = #{status}
//            </if>
//            <if test="hasRefund == true">
//    AND refund_total > 0
//            </if>
//            <if test="hasRefund == false">
//    AND refund_total = 0
//            </if>
//            <if test="payChannel != null">
//    AND pay_channel = #{payChannel}
//            </if>
//            <if test="orderSubject != null">
//    order_subject LIKE "%"#{orderSubject}"%"
//            </if>

//    default IPage<PayTransactionDO> selectPage(TransactionPageBO pageBO) {
//        return selectPage(new Page<>(pageBO.getPageNo(), pageBO.getPageSize()),
//            new QueryWrapperX<PayTransactionDO>());
//    }

    default int update(PayTransactionDO entity, Integer whereStatus) {
        return update(entity, new QueryWrapper<PayTransactionDO>()
                .eq("id", entity.getId()).eq("status", whereStatus));
    }

    default PayTransactionDO selectByAppIdAndOrderId(String appId, String orderId) {
        return selectOne(new QueryWrapper<PayTransactionDO>().eq("app_id", appId)
                .eq("order_id", orderId));
    }

}
