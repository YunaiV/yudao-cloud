package cn.iocoder.mall.userservice.convert.user;

import cn.iocoder.mall.userservice.service.user.bo.UserBO;
import cn.iocoder.mall.userservice.dal.mysql.dataobject.user.UserDO;
import cn.iocoder.mall.userservice.rpc.user.vo.UserVO;
import cn.iocoder.mall.userservice.service.user.bo.UserCreateBO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    UserVO convert(UserBO bean);

    UserBO convert(UserDO bean);

    @Mapping(source = "ip", target = "createIp")
    UserDO convert(UserCreateBO bean);

}
