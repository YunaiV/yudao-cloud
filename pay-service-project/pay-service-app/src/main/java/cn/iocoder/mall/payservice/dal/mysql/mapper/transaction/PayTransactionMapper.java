package cn.iocoder.mall.payservice.dal.mysql.mapper.transaction;

import cn.iocoder.mall.mybatis.core.query.QueryWrapperX;
import cn.iocoder.mall.mybatis.core.util.PageUtil;
import cn.iocoder.mall.payservice.dal.mysql.dataobject.transaction.PayTransactionDO;
import cn.iocoder.mall.payservice.rpc.transaction.dto.PayTransactionPageReqDTO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PayTransactionMapper extends BaseMapper<PayTransactionDO> {



    default IPage<PayTransactionDO> selectPage(PayTransactionPageReqDTO pageReqDTO) {
        QueryWrapperX<PayTransactionDO> query = new QueryWrapperX<PayTransactionDO>()
                .betweenIfPresent("create_time", pageReqDTO.getCreateBeginTime(), pageReqDTO.getPaymentEndTime())
                .betweenIfPresent("payment_time", pageReqDTO.getPaymentBeginTime(), pageReqDTO.getPaymentEndTime())
                .eqIfPresent("status", pageReqDTO.getStatus())
                .eqIfPresent("payChannel", pageReqDTO.getPayChannel())
                .likeIfPresent("order_subject", pageReqDTO.getOrderSubject());
        if (pageReqDTO.getHasRefund() != null) {
            if (pageReqDTO.getHasRefund()) {
                query.gt("refund_total", 0);
            } else {
                query.eq("refund_total", 0);
            }
        }
        return selectPage(PageUtil.build(pageReqDTO), query);
    }

    default int update(PayTransactionDO entity, Integer whereStatus) {
        return update(entity, new QueryWrapper<PayTransactionDO>()
                .eq("id", entity.getId()).eq("status", whereStatus));
    }

    default PayTransactionDO selectByAppIdAndOrderId(String appId, String orderId) {
        return selectOne(new QueryWrapper<PayTransactionDO>().eq("app_id", appId)
                .eq("order_id", orderId));
    }

    int updatePriceTotalIncr(@Param("id") Integer id, @Param("refundTotalIncr") Integer refundTotalIncr);

}
