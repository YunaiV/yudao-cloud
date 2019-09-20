package cn.iocoder.mall.demo.business.convert;

import cn.iocoder.mall.demo.business.bo.order.DemoOrderAddBO;
import cn.iocoder.mall.demo.business.dataobject.order.DemoOrderDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DemoOrderConvert {

    DemoOrderConvert INSTANCE = Mappers.getMapper(DemoOrderConvert.class);

    @Mappings({})
    DemoOrderDO convert(DemoOrderAddBO object);

}
