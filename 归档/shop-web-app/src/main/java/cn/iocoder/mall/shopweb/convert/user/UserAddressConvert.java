package cn.iocoder.mall.shopweb.convert.user;

import cn.iocoder.mall.shopweb.controller.user.vo.address.UserAddressCreateReqVO;
import cn.iocoder.mall.shopweb.controller.user.vo.address.UserAddressRespVO;
import cn.iocoder.mall.shopweb.controller.user.vo.address.UserAddressUpdateReqVO;
import cn.iocoder.mall.userservice.rpc.address.dto.UserAddressCreateReqDTO;
import cn.iocoder.mall.userservice.rpc.address.dto.UserAddressRespDTO;
import cn.iocoder.mall.userservice.rpc.address.dto.UserAddressUpdateReqDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserAddressConvert {

    UserAddressConvert INSTANCE = Mappers.getMapper(UserAddressConvert.class);

    UserAddressUpdateReqDTO convert(UserAddressUpdateReqVO bean);

    UserAddressRespVO convert(UserAddressRespDTO bean);

    List<UserAddressRespVO> convertList(List<UserAddressRespDTO> list);

    UserAddressCreateReqDTO convert(UserAddressCreateReqVO bean);

}
