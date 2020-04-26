package cn.iocoder.mall.system.rest.convert.authorization;

import cn.iocoder.mall.system.biz.bo.authorization.ResourceBO;
import cn.iocoder.mall.system.rest.response.authorization.AdminsAuthorizationMenuTreeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AdminsAuthorizationConvert {

    AdminsAuthorizationConvert INSTANCE = Mappers.getMapper(AdminsAuthorizationConvert.class);

    AdminsAuthorizationMenuTreeResponse convert(ResourceBO bean);

}
