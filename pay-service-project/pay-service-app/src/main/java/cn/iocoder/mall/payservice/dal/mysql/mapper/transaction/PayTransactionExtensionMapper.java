package cn.iocoder.mall.payservice.dal.mysql.mapper.transaction;

import cn.iocoder.mall.payservice.dal.mysql.dataobject.transaction.PayTransactionExtensionDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface PayTransactionExtensionMapper extends BaseMapper<PayTransactionExtensionDO> {

    default int update(PayTransactionExtensionDO entity, Integer whereStatus) {
        return update(entity, new QueryWrapper<PayTransactionExtensionDO>()
                .eq("id", entity.getId()).eq("status", whereStatus));
    }

    default PayTransactionExtensionDO selectByTransactionCode(String transactionCode) {
        return selectOne(new QueryWrapper<PayTransactionExtensionDO>()
                .eq("transaction_code", transactionCode));
    }

}
