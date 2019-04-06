package cn.iocoder.mall.user.application.convert;

import cn.iocoder.mall.user.application.po.UserAddressAddPO;
import cn.iocoder.mall.user.application.po.UserAddressUpdatePO;
import cn.iocoder.mall.user.service.api.dto.UserAddressAddDTO;
import cn.iocoder.mall.user.service.api.dto.UserAddressUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author Sin
 * @time 2019-04-06 14:19
 */
@Mapper
public interface UserAddressConvert {

    UserAddressConvert INSTANCE = Mappers.getMapper(UserAddressConvert.class);

    @Mappings({})
    UserAddressAddDTO convert(UserAddressAddPO userAddressAddPO);

    @Mappings({})
    UserAddressUpdateDTO convert(UserAddressUpdatePO userAddressUpdatePO);
}
