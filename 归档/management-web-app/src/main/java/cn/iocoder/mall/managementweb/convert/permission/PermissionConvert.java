package cn.iocoder.mall.managementweb.convert.permission;

import cn.iocoder.mall.systemservice.rpc.permission.dto.PermissionAssignAdminRoleDTO;
import cn.iocoder.mall.systemservice.rpc.permission.dto.PermissionAssignRoleResourceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PermissionConvert {

    PermissionConvert INSTANCE = Mappers.getMapper(PermissionConvert.class);

    PermissionAssignRoleResourceDTO convert(cn.iocoder.mall.managementweb.controller.permission.dto.PermissionAssignRoleResourceDTO bean);

    PermissionAssignAdminRoleDTO convert(cn.iocoder.mall.managementweb.controller.permission.dto.PermissionAssignAdminRoleDTO bean);

}
