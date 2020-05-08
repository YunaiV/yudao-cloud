package cn.iocoder.mall.user.biz.convert.user;

import cn.iocoder.mall.user.biz.bo.user.UserAddressBO;
import cn.iocoder.mall.user.biz.dataobject.user.UsersUserAddressDO;
import cn.iocoder.mall.user.biz.dto.user.UserAddressAddDTO;
import cn.iocoder.mall.user.biz.dto.user.UserAddressUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 用户地址 convert
 *
 * @author Sin
 * @time 2019-04-06 13:38
 */
@Mapper
public interface UserAddressConvert {

    UserAddressConvert INSTANCE = Mappers.getMapper(UserAddressConvert.class);

    @Mappings({})
    UsersUserAddressDO convert(UserAddressAddDTO userAddressAddDTO);

    @Mappings({})
    UsersUserAddressDO convert(UserAddressUpdateDTO userAddressUpdateDTO);

    @Mappings({})
    UserAddressBO convert(UsersUserAddressDO userAddressDO);

    @Mappings({})
    List<UserAddressBO> convertUserAddressBOList(List<UsersUserAddressDO> userAddressDOList);
}
