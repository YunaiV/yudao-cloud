package cn.iocoder.mall.userservice.service.address;

import cn.iocoder.common.framework.exception.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.util.CollectionUtils;
import cn.iocoder.mall.userservice.convert.address.UserAddressConvert;
import cn.iocoder.mall.userservice.dal.mysql.dataobject.address.UserAddressDO;
import cn.iocoder.mall.userservice.dal.mysql.mapper.address.UserAddressMapper;
import cn.iocoder.mall.userservice.enums.address.UserAddressType;
import cn.iocoder.mall.userservice.service.address.bo.UserAddressBO;
import cn.iocoder.mall.userservice.service.address.bo.UserAddressCreateBO;
import cn.iocoder.mall.userservice.service.address.bo.UserAddressUpdateBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

import static cn.iocoder.mall.userservice.enums.UserErrorCodeConstants.USER_ADDRESS_NOT_FOUND;

/**
* 用户收件地址 Service
*/
@Service
@Validated
public class UserAddressService {

    @Autowired
    private UserAddressMapper userAddressMapper;

    /**
    * 创建用户收件地址
    *
    * @param createBO 创建用户收件地址 BO
    * @return 用户收件地址
    */
    @Transactional
    public UserAddressBO createUserAddress(@Valid UserAddressCreateBO createBO) {
        // 如果添加的是默认收件地址，则将原默认地址修改为非默认
        if (UserAddressType.DEFAULT.getType().equals(createBO.getType())) {
            List<UserAddressDO> addressDOs = userAddressMapper.selectListByUserIdAndType(
                    createBO.getUserId(), UserAddressType.DEFAULT.getType());
            if (!CollectionUtils.isEmpty(addressDOs)) {
                addressDOs.forEach(userAddressDO -> userAddressMapper.updateById(new UserAddressDO()
                        .setId(userAddressDO.getId()).setType(UserAddressType.DEFAULT.getType())));
            }
        }
        // 插入到数据库
        UserAddressDO userAddressDO = UserAddressConvert.INSTANCE.convert(createBO);
        userAddressMapper.insert(userAddressDO);
        // 返回
        return UserAddressConvert.INSTANCE.convert(userAddressDO);
    }

    /**
    * 更新用户收件地址
    *
    * @param updateBO 更新用户收件地址 BO
    */
    @Transactional
    public void updateUserAddress(@Valid UserAddressUpdateBO updateBO) {
        // 校验更新的用户收件地址是否存在
        if (userAddressMapper.selectById(updateBO.getId()) == null) {
            throw ServiceExceptionUtil.exception(USER_ADDRESS_NOT_FOUND);
        }
        // 如果修改的是默认收件地址，则将原默认地址修改为非默认
        if (UserAddressType.DEFAULT.getType().equals(updateBO.getType())) {
            List<UserAddressDO> addressDOs = userAddressMapper.selectListByUserIdAndType(
                    updateBO.getUserId(), UserAddressType.DEFAULT.getType());
            if (!CollectionUtils.isEmpty(addressDOs)) {
                addressDOs.stream().filter(userAddressDO -> userAddressDO.getId().equals(updateBO.getId())) // 过滤掉更新的收件地址
                        .forEach(userAddressDO -> userAddressMapper.updateById(new UserAddressDO()
                                .setId(userAddressDO.getId()).setType(UserAddressType.DEFAULT.getType())));
            }
        }
        // 更新到数据库
        UserAddressDO updateObject = UserAddressConvert.INSTANCE.convert(updateBO);
        userAddressMapper.updateById(updateObject);
    }

    /**
    * 删除用户收件地址
    *
    * @param userAddressId 用户收件地址编号
    */
    public void deleteUserAddress(Integer userAddressId) {
        // 校验删除的用户收件地址是否存在
        if (userAddressMapper.selectById(userAddressId) == null) {
            throw ServiceExceptionUtil.exception(USER_ADDRESS_NOT_FOUND);
        }
        // 标记删除
        userAddressMapper.deleteById(userAddressId);
    }

    /**
    * 获得用户收件地址
    *
    * @param userAddressId 用户收件地址编号
    * @return 用户收件地址
    */
    public UserAddressBO getUserAddress(Integer userAddressId) {
        UserAddressDO userAddressDO = userAddressMapper.selectById(userAddressId);
        return UserAddressConvert.INSTANCE.convert(userAddressDO);
    }

    /**
    * 获得用户收件地址列表
    *
    * @param userAddressIds 用户收件地址编号列表
    * @return 用户收件地址列表
    */
    public List<UserAddressBO> listUserAddresses(List<Integer> userAddressIds) {
        List<UserAddressDO> userAddressDOs = userAddressMapper.selectBatchIds(userAddressIds);
        return UserAddressConvert.INSTANCE.convertList(userAddressDOs);
    }

    /**
     * 获取指定用户的收件地址列表
     *
     * @param userId 用户编号
     * @param type 地址类型
     * @return 收件地址列表
     */
    public List<UserAddressBO> listUserAddresses(Integer userId, Integer type) {
        List<UserAddressDO> userAddressDOs = userAddressMapper.selectListByUserIdAndType(userId, type);
        return UserAddressConvert.INSTANCE.convertList(userAddressDOs);
    }

}
