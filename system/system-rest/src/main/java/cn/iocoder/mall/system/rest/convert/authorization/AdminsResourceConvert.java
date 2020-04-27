package cn.iocoder.mall.system.rest.convert.authorization;

import cn.iocoder.mall.system.biz.bo.authorization.ResourceTreeNodeBO;
import cn.iocoder.mall.system.biz.dto.authorization.ResourceAddDTO;
import cn.iocoder.mall.system.biz.dto.authorization.ResourceUpdateDTO;
import cn.iocoder.mall.system.rest.request.authorization.AdminsResourceAddRequest;
import cn.iocoder.mall.system.rest.request.authorization.AdminsResourceUpdateRequest;
import cn.iocoder.mall.system.rest.response.authorization.AdminsResourceTreeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AdminsResourceConvert {

    AdminsResourceConvert INSTANCE = Mappers.getMapper(AdminsResourceConvert.class);

    @Mapping(source = "node.id", target = "id")
    @Mapping(source = "node.name", target = "name")
    @Mapping(source = "node.permission", target = "permission")
    @Mapping(source = "node.type", target = "type")
    @Mapping(source = "node.sort", target = "sort")
    @Mapping(source = "node.pid", target = "pid")
    @Mapping(source = "node.route", target = "route")
    @Mapping(source = "node.icon", target = "icon")
    AdminsResourceTreeResponse convert(ResourceTreeNodeBO bean);

    List<AdminsResourceTreeResponse> convertList(List<ResourceTreeNodeBO> beans);

    ResourceAddDTO convert(AdminsResourceAddRequest bean);

    ResourceUpdateDTO convert(AdminsResourceUpdateRequest bean);

}
