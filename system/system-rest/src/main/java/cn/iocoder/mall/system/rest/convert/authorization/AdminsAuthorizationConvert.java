package cn.iocoder.mall.system.rest.convert.authorization;

import cn.iocoder.mall.system.biz.bo.authorization.ResourceBO;
import cn.iocoder.mall.system.biz.bo.authorization.ResourceTreeNodeBO;
import cn.iocoder.mall.system.rest.response.authorization.AdminsAuthorizationMenuTreeResponse;
import cn.iocoder.mall.system.rest.response.authorization.AdminsAuthorizationRoleResourceTreeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Mapper
public interface AdminsAuthorizationConvert {

    AdminsAuthorizationConvert INSTANCE = Mappers.getMapper(AdminsAuthorizationConvert.class);

    AdminsAuthorizationMenuTreeResponse convert(ResourceBO bean);

    @Mapping(source = "node.id", target = "id")
    @Mapping(source = "node.name", target = "name")
    @Mapping(source = "node.route", target = "route")
    @Mapping(source = "node.icon", target = "icon")
    AdminsAuthorizationMenuTreeResponse convert(ResourceTreeNodeBO bean);

    @Mapping(source = "node.id", target = "id")
    @Mapping(source = "node.name", target = "name")
    AdminsAuthorizationRoleResourceTreeResponse convert2(ResourceTreeNodeBO bean);

    List<AdminsAuthorizationMenuTreeResponse> convertList(List<ResourceTreeNodeBO> beans);

    default List<AdminsAuthorizationRoleResourceTreeResponse> convertList(List<ResourceTreeNodeBO> beans, Set<Integer> roleResourceIds) {
        List<AdminsAuthorizationRoleResourceTreeResponse> responses = new ArrayList<>(beans.size());
        for (ResourceTreeNodeBO bean : beans) {
            // 转换
            AdminsAuthorizationRoleResourceTreeResponse response = this.convert2(bean);
            response.setAssign(roleResourceIds.contains(bean.getNode().getId()));
            // 递归子节点
            this.convertList(bean.getChildren(), roleResourceIds);
            // 添加到结果
            responses.add(response);
        }
        return responses;
    }


}
