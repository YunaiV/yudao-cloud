package cn.iocoder.mall.demo.application.convert;

import cn.iocoder.mall.demo.application.vo.DemoUserVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DemoUserConvert {

    DemoUserConvert INSTANCE = Mappers.getMapper(DemoUserConvert.class);

    @Mappings({})
    DemoUserVO convert(cn.iocoder.mall.demo.rpc.vo.DemoUserVO vo);

}
