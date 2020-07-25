package cn.iocoder.mall.payservice.convert.transaction;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.payservice.dal.mysql.dataobject.transaction.TransactionDO;
import cn.iocoder.mall.payservice.service.transaction.bo.TransactionBO;
import cn.iocoder.mall.payservice.service.transaction.bo.TransactionCreateBO;
import cn.iocoder.mall.payservice.service.transaction.bo.TransactionUpdateBO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TransactionConvert {

    TransactionConvert INSTANCE = Mappers.getMapper(TransactionConvert.class);



    TransactionDO convert(TransactionUpdateBO updateBO);

    List<TransactionBO> convertList(List<TransactionDO> transactionDOs);

    PageResult<TransactionBO> convertPage(IPage<TransactionDO> transactionDOPage);

    TransactionDO convert(TransactionCreateBO createBO);

    TransactionBO convert(TransactionDO transactionDO);
}
