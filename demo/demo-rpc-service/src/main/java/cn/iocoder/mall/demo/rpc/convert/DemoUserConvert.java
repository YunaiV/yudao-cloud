package cn.iocoder.mall.demo.rpc.convert;

import cn.iocoder.mall.demo.business.bo.user.DemoUserBO;
import cn.iocoder.mall.demo.rpc.dto.DemoUserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DemoUserConvert {

    DemoUserConvert INSTANCE = Mappers.getMapper(DemoUserConvert.class);

    @Mappings({})
    DemoUserDTO convert(DemoUserBO object);

}
