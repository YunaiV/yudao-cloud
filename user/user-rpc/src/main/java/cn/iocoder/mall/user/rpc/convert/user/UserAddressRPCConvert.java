package cn.iocoder.mall.user.rpc.convert.user;

import cn.iocoder.mall.user.biz.bo.user.UserAddressBO;
import cn.iocoder.mall.user.biz.dto.user.UserAddressAddDTO;
import cn.iocoder.mall.user.biz.dto.user.UserAddressUpdateDTO;
import cn.iocoder.mall.user.rpc.request.user.UserAddressAddRequest;
import cn.iocoder.mall.user.rpc.request.user.UserAddressUpdateRequest;
import cn.iocoder.mall.user.rpc.response.user.UserAddressResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * user address convert
 *
 * author: sin
 * time: 2020/5/1 10:30 上午
 */
@Mapper
public interface UserAddressRPCConvert {

    UserAddressRPCConvert INSTANCE = Mappers.getMapper(UserAddressRPCConvert.class);


    @Mappings({})
    UserAddressAddDTO convert(UserAddressAddRequest userAddressAddRequest);

    @Mappings({})
    UserAddressUpdateDTO convert(UserAddressUpdateRequest userAddressUpdateRequest);

    @Mappings({})
    UserAddressResponse convert(UserAddressBO userAddressBO);

    @Mappings({})
    List<UserAddressResponse> convert(List<UserAddressBO> addressBOS);


}
