package cn.iocoder.mall.userweb.convert.user;

import cn.iocoder.mall.userservice.rpc.user.dto.UserRespDTO;
import cn.iocoder.mall.userweb.controller.user.vo.UserRespVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    UserRespVO convert(UserRespDTO bean);

}
