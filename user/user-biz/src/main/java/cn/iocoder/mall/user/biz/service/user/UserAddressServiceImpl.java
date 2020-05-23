package cn.iocoder.mall.user.biz.service.user;

import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.mall.mybatis.enums.DeletedStatusEnum;
import cn.iocoder.mall.user.biz.bo.user.UserAddressBO;
import cn.iocoder.mall.user.biz.convert.user.UserAddressConvert;
import cn.iocoder.mall.user.biz.dao.user.UserAddressMapper;
import cn.iocoder.mall.user.biz.dataobject.user.UsersUserAddressDO;
import cn.iocoder.mall.user.biz.dto.user.UserAddressAddDTO;
import cn.iocoder.mall.user.biz.dto.user.UserAddressUpdateDTO;
import cn.iocoder.mall.user.biz.enums.UserErrorCodeEnum;
import cn.iocoder.mall.user.biz.enums.user.UserAddressHasDefaultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 用户地址
 *
 * @author Sin
 * @time 2019-04-06 13:26
 */
@Service
public class UserAddressServiceImpl implements UserAddressService {

    @Autowired
    private UserAddressMapper userAddressMapper;

    @Override
    @Transactional
    public void addAddress(UserAddressAddDTO userAddressAddDTO) {
        // 转换do，设置默认数据
        UsersUserAddressDO userAddressDO = UserAddressConvert.INSTANCE.convert(userAddressAddDTO);
        userAddressDO.setCreateTime(new Date());
        userAddressDO.setDeleted(DeletedStatusEnum.DELETED_NO.getValue());
        // 检查是否设置为默认地址
        if (UserAddressHasDefaultEnum.DEFAULT_ADDRESS_YES.getValue() == userAddressAddDTO.getHasDefault()) {
            UsersUserAddressDO defaultUserAddress = userAddressMapper.selectHasDefault(userAddressAddDTO.getUserId());
            if (defaultUserAddress != null) {
                userAddressMapper.updateById(
                        new UsersUserAddressDO()
                                .setId(defaultUserAddress.getId())
                                .setHasDefault(UserAddressHasDefaultEnum.DEFAULT_ADDRESS_NO.getValue())
                );
            }
        }
        // 保存地址
        userAddressMapper.insert(userAddressDO);
    }

    @Override
    @Transactional
    public void updateAddress(UserAddressUpdateDTO userAddressAddDTO) {
        // 检查地址
        UsersUserAddressDO userAddress = userAddressMapper.selectById(userAddressAddDTO.getId());
        if (userAddress == null) {
            throw ServiceExceptionUtil.exception(UserErrorCodeEnum.USER_ADDRESS_NOT_EXISTENT.getCode());
        }
        // 删除的地址不能更新
        if (DeletedStatusEnum.DELETED_YES.getValue().equals(userAddress.getDeleted())) {
            throw ServiceExceptionUtil.exception(UserErrorCodeEnum.USER_ADDRESS_IS_DELETED.getCode());
        }
        // 检查是否设置为默认地址
        // 是：将数据库 default address 设置为 no
        if (UserAddressHasDefaultEnum.DEFAULT_ADDRESS_YES.getValue() == userAddressAddDTO.getHasDefault()) {
            UsersUserAddressDO defaultUserAddress = userAddressMapper.selectHasDefault(userAddressAddDTO.getUserId());
            if (defaultUserAddress != null && !userAddressAddDTO.getId().equals(defaultUserAddress.getId())) {
                userAddressMapper.updateById(
                        new UsersUserAddressDO()
                                .setId(defaultUserAddress.getId())
                                .setHasDefault(UserAddressHasDefaultEnum.DEFAULT_ADDRESS_NO.getValue())
                );
            }
        }
        // 转换 vo, 并保存数据
        UsersUserAddressDO userAddressDO = UserAddressConvert.INSTANCE.convert(userAddressAddDTO);
        userAddressDO.setUpdateTime(new Date());
        userAddressMapper.updateById(userAddressDO);
    }

    @Override
    public void removeAddress(Integer userId, Integer addressId) {
        // checked address is exists.
        UsersUserAddressDO userAddress = userAddressMapper.selectById(addressId);
        if (userAddress == null) {
            throw ServiceExceptionUtil.exception(UserErrorCodeEnum.USER_ADDRESS_NOT_EXISTENT.getCode());
        }
        if (DeletedStatusEnum.DELETED_YES.getValue().equals(userAddress.getDeleted())) {
            // skip
            return;
        }
        // 更新状态为 remove
        userAddressMapper.updateById(
                (UsersUserAddressDO) new UsersUserAddressDO()
                        .setId(addressId)
                        .setDeleted(DeletedStatusEnum.DELETED_YES.getValue())
        );
    }

    @Override
    public List<UserAddressBO> listAddress(Integer userId) {
        List<UsersUserAddressDO> userAddressDOList = userAddressMapper.selectByUserId(userId);
        List<UserAddressBO> userAddressBOList = UserAddressConvert.INSTANCE.convertUserAddressBOList(userAddressDOList);
        return userAddressBOList;
    }

    @Override
    public UserAddressBO getAddress(Integer id) {
        UsersUserAddressDO userAddress = userAddressMapper.selectById(id);
        return UserAddressConvert.INSTANCE.convert(userAddress);
    }

    @Override
    public UserAddressBO getDefaultAddress(Integer userId) {
        UsersUserAddressDO defaultUserAddress = userAddressMapper.selectHasDefault(userId);
        return UserAddressConvert.INSTANCE.convert(defaultUserAddress);
    }
}
