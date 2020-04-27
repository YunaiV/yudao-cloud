package cn.iocoder.mall.system.rest.convert.authorization;

import cn.iocoder.mall.system.biz.bo.authorization.ResourceBO;
import cn.iocoder.mall.system.biz.bo.authorization.ResourceTreeNodeBO;
import cn.iocoder.mall.system.rest.response.authorization.AdminsAuthorizationMenuTreeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AdminsAuthorizationConvert {

    AdminsAuthorizationConvert INSTANCE = Mappers.getMapper(AdminsAuthorizationConvert.class);

    AdminsAuthorizationMenuTreeResponse convert(ResourceBO bean);

    @Mapping(source = "node.id", target = "id")
    @Mapping(source = "node.name", target = "name")
    @Mapping(source = "node.route", target = "route")
    @Mapping(source = "node.icon", target = "icon")
    AdminsAuthorizationMenuTreeResponse convert(ResourceTreeNodeBO bean);

    List<AdminsAuthorizationMenuTreeResponse> convertList(List<ResourceTreeNodeBO> beans);

}
