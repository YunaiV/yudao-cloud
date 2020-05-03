package cn.iocoder.mall.user.biz.service.user;

import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.mall.mybatis.enums.DeletedStatusEnum;
import cn.iocoder.mall.system.biz.enums.SystemErrorCodeEnum;
import cn.iocoder.mall.user.biz.bo.user.UserAddressBO;
import cn.iocoder.mall.user.biz.convert.user.UserAddressConvert;
import cn.iocoder.mall.user.biz.dao.user.UserAddressMapper;
import cn.iocoder.mall.user.biz.dataobject.user.UserAddressDO;
import cn.iocoder.mall.user.biz.dto.user.UserAddressAddDTO;
import cn.iocoder.mall.user.biz.dto.user.UserAddressUpdateDTO;
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
        UserAddressDO userAddressDO = UserAddressConvert.INSTANCE.convert(userAddressAddDTO);
        userAddressDO.setCreateTime(new Date());
        userAddressDO.setDeleted(DeletedStatusEnum.DELETED_NO.getValue());

        // 检查是否设置为默认地址
        if (UserAddressHasDefaultEnum.DEFAULT_ADDRESS_YES.getValue() == userAddressAddDTO.getHasDefault()) {
            UserAddressDO defaultUserAddress = userAddressMapper.selectHasDefault(userAddressAddDTO.getUserId());

            if (defaultUserAddress != null) {
                userAddressMapper.updateById(
                        new UserAddressDO()
                                .setId(defaultUserAddress.getId())
                                .setHasDefault(UserAddressHasDefaultEnum.DEFAULT_ADDRESS_NO.getValue())
                );
            }
        }

        userAddressMapper.insert(userAddressDO);
    }

    @Override
    public void updateAddress(UserAddressUpdateDTO userAddressAddDTO) {
        UserAddressDO userAddress = userAddressMapper.selectById(userAddressAddDTO.getId());
        if (DeletedStatusEnum.DELETED_YES.getValue().equals(userAddress.getDeleted())) {
            throw ServiceExceptionUtil.exception(SystemErrorCodeEnum.USER_ADDRESS_IS_DELETED.getCode());
        }

        if (userAddress == null) {
            throw ServiceExceptionUtil.exception(SystemErrorCodeEnum.USER_ADDRESS_NOT_EXISTENT.getCode());
        }

        // 检查是否设置为默认地址
        if (UserAddressHasDefaultEnum.DEFAULT_ADDRESS_YES.getValue() == userAddressAddDTO.getHasDefault()) {
            UserAddressDO defaultUserAddress = userAddressMapper.selectHasDefault(userAddressAddDTO.getUserId());

            if (defaultUserAddress != null && !userAddressAddDTO.getId().equals(defaultUserAddress.getId())) {
                userAddressMapper.updateById(
                        new UserAddressDO()
                                .setId(defaultUserAddress.getId())
                                .setHasDefault(UserAddressHasDefaultEnum.DEFAULT_ADDRESS_NO.getValue())
                );
            }
        }

        UserAddressDO defaultUserAddress = userAddressMapper.selectHasDefault(userAddressAddDTO.getUserId());
        if (defaultUserAddress != null && !userAddressAddDTO.getId().equals(defaultUserAddress.getId())) {
            userAddressMapper.updateById(
                    new UserAddressDO()
                            .setId(defaultUserAddress.getId())
                            .setHasDefault(UserAddressHasDefaultEnum.DEFAULT_ADDRESS_NO.getValue())
            );
        }

        UserAddressDO userAddressDO = UserAddressConvert.INSTANCE.convert(userAddressAddDTO);
        userAddressDO.setUpdateTime(new Date());
        userAddressMapper.updateById(userAddressDO);
    }

    @Override
    public void removeAddress(Integer userId, Integer addressId) {
        UserAddressDO userAddress = userAddressMapper.selectById(addressId);

        if (DeletedStatusEnum.DELETED_YES.getValue().equals(userAddress.getDeleted())) {
            // skip
            return;
        }

        if (userAddress == null) {
            throw ServiceExceptionUtil.exception(SystemErrorCodeEnum.USER_ADDRESS_NOT_EXISTENT.getCode());
        }

        userAddressMapper.updateById(
                (UserAddressDO) new UserAddressDO()
                        .setId(addressId)
                        .setDeleted(DeletedStatusEnum.DELETED_YES.getValue())
        );
    }

    @Override
    public List<UserAddressBO> addressList(Integer userId) {
        List<UserAddressDO> userAddressDOList = userAddressMapper.selectByUserId(userId);

        List<UserAddressBO> userAddressBOList = UserAddressConvert
                .INSTANCE.convertUserAddressBOList(userAddressDOList);

        return userAddressBOList;
    }

    @Override
    public UserAddressBO getAddress(Integer userId, Integer id) {
        UserAddressDO userAddress = userAddressMapper.selectById(id);
        if (userAddress == null) {
            throw ServiceExceptionUtil.exception(SystemErrorCodeEnum.USER_GET_ADDRESS_NOT_EXISTS.getCode());
        }

        if (DeletedStatusEnum.DELETED_YES.getValue().equals(userAddress.getDeleted())) {
            throw ServiceExceptionUtil.exception(SystemErrorCodeEnum.USER_ADDRESS_IS_DELETED.getCode());
        }

        UserAddressBO userAddressBO = UserAddressConvert.INSTANCE.convert(userAddress);
        return userAddressBO;
    }

    @Override
    public UserAddressBO getDefaultAddress(Integer userId) {
        UserAddressDO defaultUserAddress = userAddressMapper.selectHasDefault(userId);
        return UserAddressConvert.INSTANCE.convert(defaultUserAddress);
    }
}
