package cn.iocoder.mall.userweb.convert.address;

import cn.iocoder.mall.userservice.rpc.address.dto.UserAddressCreateReqDTO;
import cn.iocoder.mall.userservice.rpc.address.dto.UserAddressRespDTO;
import cn.iocoder.mall.userservice.rpc.address.dto.UserAddressUpdateReqDTO;
import cn.iocoder.mall.userweb.controller.address.vo.UserAddressCreateReqVO;
import cn.iocoder.mall.userweb.controller.address.vo.UserAddressRespVO;
import cn.iocoder.mall.userweb.controller.address.vo.UserAddressUpdateReqVO;
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
