package cn.iocoder.mall.admin.convert;

import cn.iocoder.common.framework.util.StringUtil;
import cn.iocoder.mall.admin.api.bo.resource.ResourceBO;
import cn.iocoder.mall.admin.api.dto.resource.ResourceAddDTO;
import cn.iocoder.mall.admin.api.dto.resource.ResourceUpdateDTO;
import cn.iocoder.mall.admin.dataobject.ResourceDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ResourceConvert {

    ResourceConvert INSTANCE = Mappers.getMapper(ResourceConvert.class);

    @Mappings({
            @Mapping(source = "permissions", target = "permissions", qualifiedByName = "translateListFromString")
    })
    ResourceBO convert(ResourceDO resourceDO);

    @Mappings({})
    List<ResourceBO> convert(List<ResourceDO> resourceDOs);

    @Mappings({
            @Mapping(source = "permissions", target = "permissions", qualifiedByName = "translateStringFromList")
    })
    ResourceDO convert(ResourceAddDTO resourceAddDTO);

    @Mappings({
            @Mapping(source = "permissions", target = "permissions", qualifiedByName = "translateStringFromList")
    })
    ResourceDO convert(ResourceUpdateDTO resourceUpdateDTO);

    @Named("translateListFromString")
    default List<String> translateListFromString(String str) {
        return StringUtil.split(str, ",");
    }

    @Named("translateStringFromList")
    default String translateStringFromList(List<String> list) {
        return StringUtil.join(list, ",");
    }

}
