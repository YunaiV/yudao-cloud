package cn.iocoder.mall.user.rpc.convert.user;

import cn.iocoder.mall.user.biz.bo.user.UserAddressBO;
import cn.iocoder.mall.user.rpc.response.user.UserAddressResponse;
import org.mapstruct.Mapper;
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

    UserAddressResponse convert(UserAddressBO userAddressBO);

}
