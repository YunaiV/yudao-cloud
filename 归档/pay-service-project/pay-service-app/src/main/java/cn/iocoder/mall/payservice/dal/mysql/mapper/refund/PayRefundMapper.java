package cn.iocoder.mall.payservice.dal.mysql.mapper.refund;

import cn.iocoder.mall.payservice.dal.mysql.dataobject.refund.PayRefundDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface PayRefundMapper extends BaseMapper<PayRefundDO> {

    default int update(PayRefundDO entity, Integer whereStatus) {
        return update(entity, new QueryWrapper<PayRefundDO>()
                .eq("id", entity.getId()).eq("status", whereStatus));
    }

    default PayRefundDO selectByRefundCode(String refundCode) {
        return selectOne(new QueryWrapper<PayRefundDO>()
                .eq("refund_code", refundCode));
    }


//    <if test="createBeginTime != null">
//    AND create_time >= #{createBeginTime}
//            </if>
//            <if test="createEndTime != null">
//    AND #{createEndTime} >= create_time
//            </if>
//            <if test="finishBeginTime != null">
//    AND finish_time >= #{finishBeginTime}
//            </if>
//            <if test="finishEndTime != null">
//    AND #{finishEndTime} >= finish_time
//            </if>
//            <if test="status != null">
//    AND status = #{status}
//            </if>
//            <if test="payChannel != null">
//    AND pay_channel = #{payChannel}
//            </if>

//    List<PayRefundDO> selectListByPage(@Param("createBeginTime") Date createBeginTime,
//                                       @Param("createEndTime") Date createEndTime,
//                                       @Param("finishBeginTime") Date finishBeginTime,
//                                       @Param("finishEndTime") Date finishEndTime,
//                                       @Param("status") Integer status,
//                                       @Param("payChannel") Integer payChannel,
//                                       @Param("offset") Integer offset,
//                                       @Param("limit") Integer limit);
//
//    Integer selectCountByPage(@Param("createBeginTime") Date createBeginTime,
//                              @Param("createEndTime") Date createEndTime,
//                              @Param("finishBeginTime") Date finishBeginTime,
//                              @Param("finishEndTime") Date finishEndTime,
//                              @Param("status") Integer status,
//                              @Param("payChannel") Integer payChannel);

}
