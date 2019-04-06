package cn.iocoder.mall.user.service;

import cn.iocoder.common.framework.constant.DeletedStatusEnum;
import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.user.convert.UserAddressConvert;
import cn.iocoder.mall.user.dao.UserAddressMapper;
import cn.iocoder.mall.user.dataobject.UserAddressDO;
import cn.iocoder.mall.user.service.api.UserAddressService;
import cn.iocoder.mall.user.service.api.bo.UserAddressBO;
import cn.iocoder.mall.user.service.api.constant.UserErrorCodeEnum;
import cn.iocoder.mall.user.service.api.dto.UserAddressAddDTO;
import cn.iocoder.mall.user.service.api.dto.UserAddressUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public CommonResult addAddress(UserAddressAddDTO userAddressAddDTO) {
        UserAddressDO userAddressDO = UserAddressConvert.INSTANCE.convert(userAddressAddDTO);
        userAddressDO.setCreateTime(new Date());
        userAddressDO.setDeleted(DeletedStatusEnum.DELETED_NO.getValue());
        userAddressMapper.insert(userAddressDO);
        return CommonResult.success(null);
    }

    @Override
    public CommonResult updateAddress(UserAddressUpdateDTO userAddressAddDTO) {
        UserAddressDO userAddress = userAddressMapper
                .selectByUserIdAndId(userAddressAddDTO.getUserId(), userAddressAddDTO.getId());

        if (DeletedStatusEnum.DELETED_YES.getValue().equals(userAddress.getDeleted())) {
            return CommonResult.success(UserErrorCodeEnum.USER_ADDRESS_IS_DELETED.getCode());
        }

        if (userAddress == null) {
            return ServiceExceptionUtil.error(UserErrorCodeEnum.USER_ADDRESS_NOT_EXISTENT.getCode());
        }

        UserAddressDO userAddressDO = UserAddressConvert.INSTANCE.convert(userAddressAddDTO);
        userAddressDO.setUpdateTime(new Date());
        userAddressMapper.updateById(userAddressDO.getId(), userAddressDO);
        return CommonResult.success(null);
    }

    @Override
    public CommonResult removeAddress(Integer userId, Integer addressId) {
        UserAddressDO userAddress = userAddressMapper.selectByUserIdAndId(userId, addressId);

        if (DeletedStatusEnum.DELETED_YES.getValue().equals(userAddress.getDeleted())) {
            // skip
            return CommonResult.success(null);
        }

        if (userAddress == null) {
            return ServiceExceptionUtil.error(UserErrorCodeEnum.USER_ADDRESS_NOT_EXISTENT.getCode());
        }

        userAddressMapper.updateById(
                addressId,
                (UserAddressDO) new UserAddressDO()
                        .setDeleted(DeletedStatusEnum.DELETED_YES.getValue())
        );
        return CommonResult.success(null);
    }

    @Override
    public CommonResult<List<UserAddressBO>> addressList(Integer userId) {

        List<UserAddressDO> userAddressDOList = userAddressMapper
                .selectByUserIdAndDeleted(DeletedStatusEnum.DELETED_NO.getValue(), userId);

        List<UserAddressBO> userAddressBOList = UserAddressConvert
                .INSTANCE.convertUserAddressBOList(userAddressDOList);

        return CommonResult.success(userAddressBOList);
    }
}
