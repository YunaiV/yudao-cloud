package cn.iocoder.mall.demo.rpc.convert;

import cn.iocoder.mall.demo.business.bo.product.DemoProductBO;
import cn.iocoder.mall.demo.rpc.dto.DemoProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DemoProductConvert {

    DemoProductConvert INSTANCE = Mappers.getMapper(DemoProductConvert.class);

    @Mappings({})
    DemoProductDTO convert(DemoProductBO object);

}
