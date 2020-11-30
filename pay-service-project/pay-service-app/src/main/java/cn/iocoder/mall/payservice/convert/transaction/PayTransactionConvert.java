package cn.iocoder.mall.payservice.convert.transaction;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.payservice.dal.mysql.dataobject.transaction.PayTransactionDO;
import cn.iocoder.mall.payservice.dal.mysql.dataobject.transaction.PayTransactionExtensionDO;
import cn.iocoder.mall.payservice.rpc.transaction.dto.PayTransactionCreateReqDTO;
import cn.iocoder.mall.payservice.rpc.transaction.dto.PayTransactionRespDTO;
import cn.iocoder.mall.payservice.rpc.transaction.dto.PayTransactionSubmitReqDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PayTransactionConvert {

    PayTransactionConvert INSTANCE = Mappers.getMapper(PayTransactionConvert.class);

    PayTransactionDO convert(PayTransactionCreateReqDTO bean);

    PayTransactionExtensionDO convert(PayTransactionSubmitReqDTO bean);

    PayTransactionRespDTO convert(PayTransactionDO bean);

    @Mapping(source = "records", target = "list")
    PageResult<PayTransactionRespDTO> convertPage(IPage<PayTransactionDO> bean);

}
