package cn.iocoder.mall.demo.application.convert;

import cn.iocoder.mall.demo.application.vo.DemoProductVO;
import cn.iocoder.mall.demo.business.bo.product.DemoProductBO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DemoProductConvert {

    DemoProductConvert INSTANCE = Mappers.getMapper(DemoProductConvert.class);

    @Mappings({})
    DemoProductVO convert(DemoProductBO object);

}
