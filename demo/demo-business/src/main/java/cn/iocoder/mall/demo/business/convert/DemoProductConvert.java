package cn.iocoder.mall.demo.business.convert;

import cn.iocoder.mall.demo.business.bo.DemoProductBO;
import cn.iocoder.mall.demo.business.dataobject.product.DemoProductDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DemoProductConvert {

    DemoProductConvert INSTANCE = Mappers.getMapper(DemoProductConvert.class);

    @Mappings({})
    DemoProductBO convert(DemoProductDO object);

}
