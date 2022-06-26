package cn.iocoder.mall.shopweb.convert.pay;

import cn.iocoder.mall.payservice.rpc.transaction.dto.PayTransactionRespDTO;
import cn.iocoder.mall.payservice.rpc.transaction.dto.PayTransactionSubmitReqDTO;
import cn.iocoder.mall.payservice.rpc.transaction.dto.PayTransactionSubmitRespDTO;
import cn.iocoder.mall.shopweb.controller.pay.vo.transaction.PayTransactionRespVO;
import cn.iocoder.mall.shopweb.controller.pay.vo.transaction.PayTransactionSubmitReqVO;
import cn.iocoder.mall.shopweb.controller.pay.vo.transaction.PayTransactionSubmitRespVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PayTransactionConvert {

    PayTransactionConvert INSTANCE = Mappers.getMapper(PayTransactionConvert.class);

    PayTransactionSubmitReqDTO convert(PayTransactionSubmitReqVO bean);

    PayTransactionSubmitRespVO convert(PayTransactionSubmitRespDTO bean);

    PayTransactionRespVO convert(PayTransactionRespDTO bean);

}
