package cn.iocoder.mall.user.rpc.convert.user;

import cn.iocoder.mall.user.biz.bo.user.UserAddressBO;
import cn.iocoder.mall.user.rpc.response.user.UserAddressResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * user address convert
 *
 * author: sin
 * time: 2020/5/1 10:30 上午
 */
@Mapper
public interface UserAddressRPCConvert {

    UserAddressRPCConvert INSTANCE = Mappers.getMapper(UserAddressRPCConvert.class);

    // TODO DONE FROM 芋艿 to 小范：如果不用映射，可以不用 @Mappings 哈
    @Mappings({})
    UserAddressResponse convert(UserAddressBO userAddressBO);
}
