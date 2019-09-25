package cn.iocoder.mall.demo.application.convert;

import cn.iocoder.mall.demo.application.vo.DemoUserVO;
import cn.iocoder.mall.demo.rpc.dto.DemoUserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DemoUserConvert {

    DemoUserConvert INSTANCE = Mappers.getMapper(DemoUserConvert.class);

    @Mappings({})
    DemoUserVO convert(DemoUserDTO vo);

}
