package cn.iocoder.mall.userservice.convert.user;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.userservice.rpc.user.dto.UserCreateReqDTO;
import cn.iocoder.mall.userservice.rpc.user.dto.UserPageReqDTO;
import cn.iocoder.mall.userservice.rpc.user.dto.UserUpdateReqDTO;
import cn.iocoder.mall.userservice.service.user.bo.UserBO;
import cn.iocoder.mall.userservice.dal.mysql.dataobject.user.UserDO;
import cn.iocoder.mall.userservice.rpc.user.dto.UserRespDTO;
import cn.iocoder.mall.userservice.service.user.bo.UserCreateBO;
import cn.iocoder.mall.userservice.service.user.bo.UserPageBO;
import cn.iocoder.mall.userservice.service.user.bo.UserUpdateBO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    UserRespDTO convert(UserBO bean);

    UserBO convert(UserDO bean);

    UserDO convert(UserCreateBO bean);

    @Mapping(source = "ip", target = "createIp")
    UserCreateBO convert(UserCreateReqDTO bean);

    UserDO convert(UserUpdateBO bean);

    @Mapping(source = "records", target = "list")
    PageResult<UserBO> convertPage(IPage<UserDO> page);

    UserUpdateBO convert(UserUpdateReqDTO bean);

    List<UserBO> convertList(List<UserDO> list);

    UserPageBO convert(UserPageReqDTO bean);

    PageResult<UserRespDTO> convertPage(PageResult<UserBO> page);

    List<UserRespDTO> convertList02(List<UserBO> list);

}
