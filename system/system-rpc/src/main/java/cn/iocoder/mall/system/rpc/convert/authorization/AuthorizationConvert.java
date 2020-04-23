package cn.iocoder.mall.system.rpc.convert.authorization;

import cn.iocoder.mall.system.biz.dto.authorization.AuthorizationCheckPermissionsDTO;
import cn.iocoder.mall.system.rpc.request.authorization.AuthorizationCheckPermissionsRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthorizationConvert {

    AuthorizationConvert INSTANCE = Mappers.getMapper(AuthorizationConvert.class);

    AuthorizationCheckPermissionsDTO convert(AuthorizationCheckPermissionsRequest bean);

}
