package cn.iocoder.mall.payservice.dal.mysql.mapper.transaction;

import cn.iocoder.mall.payservice.dal.mysql.dataobject.transaction.PayTransactionDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface PayTransactionMapper extends BaseMapper<PayTransactionDO> {

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
