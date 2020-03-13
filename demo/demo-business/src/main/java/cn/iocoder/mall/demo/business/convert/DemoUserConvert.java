package cn.iocoder.mall.demo.business.convert;

import cn.iocoder.mall.demo.business.bo.user.DemoUserBO;
import cn.iocoder.mall.demo.business.cacheobject.user.DemoUserCacheObject;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DemoUserConvert {

    DemoUserConvert INSTANCE = Mappers.getMapper(DemoUserConvert.class);

    @Mappings({})
    DemoUserBO convert(DemoUserCacheObject object);

}
