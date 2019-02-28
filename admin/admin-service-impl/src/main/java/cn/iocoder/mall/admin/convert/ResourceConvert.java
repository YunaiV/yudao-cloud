package cn.iocoder.mall.admin.convert;

import cn.iocoder.mall.admin.api.bo.ResourceBO;
import cn.iocoder.mall.admin.dataobject.ResourceDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ResourceConvert {

    ResourceConvert INSTANCE = Mappers.getMapper(ResourceConvert.class);

    @Mappings({})
    ResourceBO convert(ResourceDO resourceDO);

    @Mappings({})
    List<ResourceBO> convert(List<ResourceDO> resourceDOs);

}