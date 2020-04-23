package cn.iocoder.mall.system.rpc.convert.user;

import cn.iocoder.mall.system.biz.bo.user.UserBO;
import cn.iocoder.mall.system.rpc.response.user.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    UserResponse convert(UserBO bean);

}
