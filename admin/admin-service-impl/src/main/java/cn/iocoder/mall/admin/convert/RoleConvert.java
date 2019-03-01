package cn.iocoder.mall.admin.convert;

import cn.iocoder.mall.admin.api.bo.RoleBO;
import cn.iocoder.mall.admin.api.dto.RoleAddDTO;
import cn.iocoder.mall.admin.api.dto.RoleUpdateDTO;
import cn.iocoder.mall.admin.dataobject.RoleDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RoleConvert {

    RoleConvert INSTANCE = Mappers.getMapper(RoleConvert.class);

    @Mappings({})
    RoleDO convert(RoleAddDTO roleAddDTO);

    @Mappings({})
    RoleDO convert(RoleUpdateDTO roleUpdateDTO);

    @Mappings({})
    RoleBO convert(RoleDO roleDO);

    @Mappings({})
    List<RoleBO> convert(List<RoleDO> roleDOs);

}