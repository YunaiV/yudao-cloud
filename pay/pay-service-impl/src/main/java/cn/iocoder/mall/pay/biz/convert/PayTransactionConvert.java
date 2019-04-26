package cn.iocoder.mall.pay.biz.convert;

import cn.iocoder.mall.pay.api.bo.PayTransactionBO;
import cn.iocoder.mall.pay.api.dto.PayRefundSubmitDTO;
import cn.iocoder.mall.pay.api.dto.PayTransactionCreateDTO;
import cn.iocoder.mall.pay.api.dto.PayTransactionSubmitDTO;
import cn.iocoder.mall.pay.api.message.PayRefundSuccessMessage;
import cn.iocoder.mall.pay.api.message.PayTransactionSuccessMessage;
import cn.iocoder.mall.pay.biz.dataobject.PayRefundDO;
import cn.iocoder.mall.pay.biz.dataobject.PayTransactionDO;
import cn.iocoder.mall.pay.biz.dataobject.PayTransactionExtensionDO;
import cn.iocoder.mall.pay.biz.dataobject.PayNotifyTaskDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PayTransactionConvert {

    PayTransactionConvert INSTANCE = Mappers.getMapper(PayTransactionConvert.class);

    @Mappings({})
    PayTransactionDO convert(PayTransactionCreateDTO payTransactionCreateDTO);

    @Mappings({})
    PayTransactionBO convert(PayTransactionDO payTransactionDO);

    @Mappings({})
    PayTransactionExtensionDO convert(PayTransactionSubmitDTO payTransactionSubmitDTO);

    @Mappings({
            @Mapping(source = "transaction.transactionId", target = "transactionId"),
            @Mapping(source = "transaction.orderId", target = "orderId"),
    })
    PayTransactionSuccessMessage convert(PayNotifyTaskDO payTransactionNotifyTaskDO);

    @Mappings({
            @Mapping(source = "refund.transactionId", target = "transactionId"),
            @Mapping(source = "refund.orderId", target = "orderId"),
            @Mapping(source = "refund.refundId", target = "refundId"),
    })
    PayRefundSuccessMessage convert2(PayNotifyTaskDO payTransactionNotifyTaskDO);

    @Mappings({})
    PayRefundDO convert(PayRefundSubmitDTO payRefundSubmitDTO);

}
