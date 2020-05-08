package cn.iocoder.mall.user.rest.convert;

import cn.iocoder.mall.user.biz.bo.user.UserAddressBO;
import cn.iocoder.mall.user.biz.dto.user.UserAddressAddDTO;
import cn.iocoder.mall.user.biz.dto.user.UserAddressUpdateDTO;
import cn.iocoder.mall.user.rest.request.user.UserAddressAddRequest;
import cn.iocoder.mall.user.rest.request.user.UserAddressUpdateRequest;
import cn.iocoder.mall.user.rest.response.user.UserAddressResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 用户地址 convert
 *
 * author: sin
 * time: 2020/5/8 10:01
 */
@Mapper
public interface UserAddressConvert {

    UserAddressConvert INSTANCE = Mappers.getMapper(UserAddressConvert.class);

    @Mappings({})
    List<UserAddressResponse> convert(List<UserAddressBO> userAddressBOList);

    @Mappings({})
    UserAddressResponse convert(UserAddressBO userAddressBO);

    @Mappings({})
    UserAddressAddDTO convert(UserAddressAddRequest userAddressAddRequest);

    @Mappings({})
    UserAddressUpdateDTO convert(UserAddressUpdateRequest userAddressUpdateRequest);
}
