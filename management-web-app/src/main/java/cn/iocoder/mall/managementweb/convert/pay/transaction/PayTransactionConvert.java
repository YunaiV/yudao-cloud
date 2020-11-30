package cn.iocoder.mall.managementweb.convert.pay.transaction;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.managementweb.controller.pay.vo.transaction.PayTransactionPageReqVO;
import cn.iocoder.mall.managementweb.controller.pay.vo.transaction.PayTransactionRespVO;
import cn.iocoder.mall.payservice.rpc.transaction.dto.PayTransactionPageReqDTO;
import cn.iocoder.mall.payservice.rpc.transaction.dto.PayTransactionRespDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PayTransactionConvert {

    PayTransactionConvert INSTANCE = Mappers.getMapper(PayTransactionConvert.class);

    PayTransactionPageReqDTO convert(PayTransactionPageReqVO bean);

    PageResult<PayTransactionRespVO> convertPage(PageResult<PayTransactionRespDTO> bean);

}
