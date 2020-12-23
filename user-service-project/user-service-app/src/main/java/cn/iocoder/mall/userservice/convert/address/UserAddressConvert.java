package cn.iocoder.mall.userservice.convert.address;

import cn.iocoder.mall.userservice.dal.mysql.dataobject.address.UserAddressDO;
import cn.iocoder.mall.userservice.rpc.address.dto.UserAddressCreateReqDTO;
import cn.iocoder.mall.userservice.rpc.address.dto.UserAddressRespDTO;
import cn.iocoder.mall.userservice.rpc.address.dto.UserAddressUpdateReqDTO;
import cn.iocoder.mall.userservice.service.address.bo.UserAddressBO;
import cn.iocoder.mall.userservice.service.address.bo.UserAddressCreateBO;
import cn.iocoder.mall.userservice.service.address.bo.UserAddressUpdateBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserAddressConvert {

    UserAddressConvert INSTANCE = Mappers.getMapper(UserAddressConvert.class);

    UserAddressDO convert(UserAddressCreateBO bean);

    UserAddressBO convert(UserAddressDO bean);

    UserAddressDO convert(UserAddressUpdateBO bean);

    List<UserAddressBO> convertList(List<UserAddressDO> list);

    UserAddressCreateBO convert(UserAddressCreateReqDTO bean);

    UserAddressUpdateBO convert(UserAddressUpdateReqDTO bean);

    UserAddressRespDTO convert(UserAddressBO bean);

    List<UserAddressRespDTO> convertList02(List<UserAddressBO> list);

}
