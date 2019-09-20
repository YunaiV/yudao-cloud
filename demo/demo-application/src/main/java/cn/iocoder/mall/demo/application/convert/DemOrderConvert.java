package cn.iocoder.mall.demo.application.convert;

import cn.iocoder.mall.demo.application.dto.DemoOrderAddDTO;
import cn.iocoder.mall.demo.business.bo.order.DemoOrderAddBO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DemOrderConvert {

    DemOrderConvert INSTANCE = Mappers.getMapper(DemOrderConvert.class);

    @Mappings({})
    DemoOrderAddBO convert(DemoOrderAddDTO addDTO);

}
