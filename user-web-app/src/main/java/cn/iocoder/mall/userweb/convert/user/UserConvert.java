package cn.iocoder.mall.userweb.convert.user;

import cn.iocoder.mall.userservice.rpc.user.vo.UserVO;
import cn.iocoder.mall.userweb.controller.user.vo.UserInfoVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    UserInfoVO convert(UserVO bean);

}
