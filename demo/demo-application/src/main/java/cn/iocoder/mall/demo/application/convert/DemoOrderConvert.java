package cn.iocoder.mall.demo.application.convert;

import cn.iocoder.mall.demo.application.dto.DemoOrderAddDTO;
import cn.iocoder.mall.demo.business.bo.order.DemoOrderAddBO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DemoOrderConvert {

    DemoOrderConvert INSTANCE = Mappers.getMapper(DemoOrderConvert.class);

    @Mappings({})
    DemoOrderAddBO convert(DemoOrderAddDTO addDTO);

}
