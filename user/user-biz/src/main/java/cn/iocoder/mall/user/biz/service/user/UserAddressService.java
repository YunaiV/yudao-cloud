package cn.iocoder.mall.user.biz.service.user;

import cn.iocoder.mall.user.biz.bo.user.UserAddressBO;
import cn.iocoder.mall.user.biz.dto.user.UserAddressAddDTO;
import cn.iocoder.mall.user.biz.dto.user.UserAddressUpdateDTO;

import java.util.List;

/**
 * 用户地址
 *
 * @author Sin
 * @time 2019-04-06 13:24
 */
public interface UserAddressService {

    /**
     * 添加地址
     *
     * @param userAddressAddDTO
     */
    void addAddress(UserAddressAddDTO userAddressAddDTO);

    /**
     * 更新 - 根据id 更新
     *
     * @param userAddressAddDTO
     */
    void updateAddress(UserAddressUpdateDTO userAddressAddDTO);

    /**
     * 删除 - 更新id 删除
     *
     * @param userId
     * @param addressId
     */
    void removeAddress(Integer userId, Integer addressId);

    /**
     * 获取 - 用户所有地址
     *
     * @param userId
     * @return
     */
    List<UserAddressBO> listAddress(Integer userId);

    /**
     * 获取 - 根据id 获取地址
     *
     * @param id
     * @return
     */
    UserAddressBO getAddress(Integer id);

    /**
     * 获取 - 获取用户 default 地址
     *
     * @param userId
     * @return
     */
    UserAddressBO getDefaultAddress(Integer userId);
}
