package cn.iocoder.mall.managementweb.convert.user;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.managementweb.controller.user.vo.UserPageReqVO;
import cn.iocoder.mall.managementweb.controller.user.vo.UserRespVO;
import cn.iocoder.mall.managementweb.controller.user.vo.UserUpdateInfoReqVO;
import cn.iocoder.mall.managementweb.controller.user.vo.UserUpdateStatusReqVO;
import cn.iocoder.mall.userservice.rpc.user.dto.UserPageReqDTO;
import cn.iocoder.mall.userservice.rpc.user.dto.UserRespDTO;
import cn.iocoder.mall.userservice.rpc.user.dto.UserUpdateReqDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    UserUpdateReqDTO convert(UserUpdateInfoReqVO bean);

    UserRespVO convert(UserRespDTO bean);

    List<UserRespVO> convertList(List<UserRespDTO> list);

    UserPageReqDTO convert(UserPageReqVO bean);

    PageResult<UserRespVO> convertPage(PageResult<UserRespDTO> bean);

    @Mapping(source = "userId", target = "id")
    UserUpdateReqDTO convert(UserUpdateStatusReqVO bean);

}
