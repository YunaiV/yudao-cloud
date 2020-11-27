package cn.iocoder.mall.payservice.dal.mysql.mapper.transaction;

import cn.iocoder.mall.payservice.dal.mysql.dataobject.transaction.PayTransactionDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface PayTransactionMapper extends BaseMapper<PayTransactionDO> {

//    default IPage<PayTransactionDO> selectPage(TransactionPageBO pageBO) {
//        return selectPage(new Page<>(pageBO.getPageNo(), pageBO.getPageSize()),
//            new QueryWrapperX<PayTransactionDO>());
//    }

}
