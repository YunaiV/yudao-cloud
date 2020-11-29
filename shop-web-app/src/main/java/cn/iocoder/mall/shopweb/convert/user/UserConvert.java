package cn.iocoder.mall.shopweb.convert.user;

import cn.iocoder.mall.shopweb.controller.user.vo.user.UserRespVO;
import cn.iocoder.mall.userservice.rpc.user.dto.UserRespDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    UserRespVO convert(UserRespDTO bean);

}
