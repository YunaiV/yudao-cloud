package cn.iocoder.mall.user.biz.service;

import cn.iocoder.common.framework.constant.DeletedStatusEnum;
import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.user.api.constant.UserErrorCodeEnum;
import cn.iocoder.mall.user.biz.convert.UserAddressConvert;
import cn.iocoder.mall.user.biz.dao.UserAddressMapper;
import cn.iocoder.mall.user.biz.dataobject.UserAddressDO;
import cn.iocoder.mall.user.api.UserAddressService;
import cn.iocoder.mall.user.api.bo.UserAddressBO;
import cn.iocoder.mall.user.api.dto.UserAddressAddDTO;
import cn.iocoder.mall.user.api.dto.UserAddressUpdateDTO;
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
@com.alibaba.dubbo.config.annotation.Service(validation = "true")
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
            return ServiceExceptionUtil.error(UserErrorCodeEnum.USER_ADDRESS_IS_DELETED.getCode());
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

    @Override
    public CommonResult<UserAddressBO> getAddress(Integer userId, Integer id) {
        UserAddressDO userAddress = userAddressMapper.selectByUserIdAndId(userId, id);
        if (userAddress == null) {
            return ServiceExceptionUtil.error(UserErrorCodeEnum.USER_GET_ADDRESS_NOT_EXISTS.getCode());
        }

        if (DeletedStatusEnum.DELETED_YES.getValue().equals(userAddress.getDeleted())) {
            return ServiceExceptionUtil.error(UserErrorCodeEnum.USER_ADDRESS_IS_DELETED.getCode());
        }

        UserAddressBO userAddressBO = UserAddressConvert.INSTANCE.convert(userAddress);
        return CommonResult.success(userAddressBO);
    }
}
