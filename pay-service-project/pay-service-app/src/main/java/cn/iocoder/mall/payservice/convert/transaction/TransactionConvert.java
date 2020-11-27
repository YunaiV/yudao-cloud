package cn.iocoder.mall.payservice.convert.transaction;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransactionConvert {

    TransactionConvert INSTANCE = Mappers.getMapper(TransactionConvert.class);


}
