package cn.iocoder.mall.userservice.manager.address;

import cn.iocoder.mall.userservice.convert.address.UserAddressConvert;
import cn.iocoder.mall.userservice.rpc.address.dto.UserAddressCreateReqDTO;
import cn.iocoder.mall.userservice.rpc.address.dto.UserAddressRespDTO;
import cn.iocoder.mall.userservice.rpc.address.dto.UserAddressUpdateReqDTO;
import cn.iocoder.mall.userservice.service.address.UserAddressService;
import cn.iocoder.mall.userservice.service.address.bo.UserAddressBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 用户收件地址 Manager
*/
@Service
public class UserAddressManager {

    @Autowired
    private UserAddressService userAddressService;

    /**
    * 创建用户收件地址
    *
    * @param createDTO 创建用户收件地址 DTO
    * @return 用户收件地址
    */
    public Integer createUserAddress(UserAddressCreateReqDTO createDTO) {
        UserAddressBO userAddressBO = userAddressService.createUserAddress(UserAddressConvert.INSTANCE.convert(createDTO));
        return userAddressBO.getId();
    }

    /**
    * 更新用户收件地址
    *
    * @param updateDTO 更新用户收件地址 DTO
    */
    public void updateUserAddress(UserAddressUpdateReqDTO updateDTO) {
        userAddressService.updateUserAddress(UserAddressConvert.INSTANCE.convert(updateDTO));
    }

    /**
    * 删除用户收件地址
    *
    * @param userAddressId 用户收件地址编号
    */
    public void deleteUserAddress(Integer userAddressId) {
        userAddressService.deleteUserAddress(userAddressId);
    }

    /**
    * 获得用户收件地址
    *
    * @param userAddressId 用户收件地址编号
    * @return 用户收件地址
    */
    public UserAddressRespDTO getUserAddress(Integer userAddressId) {
        UserAddressBO userAddressBO = userAddressService.getUserAddress(userAddressId);
        return UserAddressConvert.INSTANCE.convert(userAddressBO);
    }

    /**
    * 获得用户收件地址列表
    *
    * @param userAddressIds 用户收件地址编号列表
    * @return 用户收件地址列表
    */
    public List<UserAddressRespDTO> listUserAddresses(List<Integer> userAddressIds) {
        List<UserAddressBO> userAddressBOs = userAddressService.listUserAddresses(userAddressIds);
        return UserAddressConvert.INSTANCE.convertList02(userAddressBOs);
    }

    /**
     * 获取指定用户的收件地址列表
     *
     * @param userId 用户编号
     * @param type 地址类型
     * @return 收件地址列表
     */
    public List<UserAddressRespDTO> listUserAddresses(Integer userId, Integer type) {
        List<UserAddressBO> userAddressBOs = userAddressService.listUserAddresses(userId, type);
        return UserAddressConvert.INSTANCE.convertList02(userAddressBOs);
    }

}
