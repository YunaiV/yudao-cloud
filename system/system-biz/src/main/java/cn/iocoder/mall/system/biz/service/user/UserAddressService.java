package cn.iocoder.mall.system.biz.service.user;

import cn.iocoder.mall.system.biz.bo.user.UserAddressBO;
import cn.iocoder.mall.system.biz.dto.user.UserAddressAddDTO;
import cn.iocoder.mall.system.biz.dto.user.UserAddressUpdateDTO;

import java.util.List;

/**
 * 用户地址
 *
 * @author Sin
 * @time 2019-04-06 13:24
 */
public interface UserAddressService {

    void addAddress(UserAddressAddDTO userAddressAddDTO);

    void updateAddress(UserAddressUpdateDTO userAddressAddDTO);

    void removeAddress(Integer userId, Integer addressId);

    List<UserAddressBO> addressList(Integer userId);

    UserAddressBO getAddress(Integer userId, Integer id);

    UserAddressBO getDefaultAddress(Integer userId);
}
