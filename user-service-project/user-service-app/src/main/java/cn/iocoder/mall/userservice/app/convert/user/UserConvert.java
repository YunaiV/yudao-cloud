package cn.iocoder.mall.userservice.app.convert.user;

import cn.iocoder.mall.userservice.app.service.user.bo.UserBO;
import cn.iocoder.mall.userservice.app.dal.mysql.dataobject.user.UserDO;
import cn.iocoder.mall.userservice.rpc.user.vo.UserVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    UserVO convert(UserBO bean);

    UserBO convert(UserDO bean);

}
