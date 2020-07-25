package cn.iocoder.mall.payservice.dal.mysql.mapper.transaction;

import cn.iocoder.mall.mybatis.core.query.QueryWrapperX;
import cn.iocoder.mall.payservice.dal.mysql.dataobject.transaction.TransactionDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionMapper extends BaseMapper<TransactionDO> {

    default IPage<TransactionDO> selectPage(TransactionPageBO pageBO) {
        return selectPage(new Page<>(pageBO.getPageNo(), pageBO.getPageSize()),
            new QueryWrapperX<TransactionDO>());
    }

}