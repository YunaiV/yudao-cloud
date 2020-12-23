package cn.iocoder.mall.shopweb.service.user;

import cn.iocoder.common.framework.exception.GlobalException;
import cn.iocoder.common.framework.util.CollectionUtils;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.shopweb.controller.user.vo.address.UserAddressCreateReqVO;
import cn.iocoder.mall.shopweb.controller.user.vo.address.UserAddressRespVO;
import cn.iocoder.mall.shopweb.controller.user.vo.address.UserAddressUpdateReqVO;
import cn.iocoder.mall.shopweb.convert.user.UserAddressConvert;
import cn.iocoder.mall.userservice.enums.address.UserAddressType;
import cn.iocoder.mall.userservice.rpc.address.UserAddressRpc;
import cn.iocoder.mall.userservice.rpc.address.dto.UserAddressRespDTO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.List;

import static cn.iocoder.common.framework.exception.enums.GlobalErrorCodeConstants.FORBIDDEN;

/**
* 用户收件地址 Manager
*/
@Service
public class UserAddressManager {

    @DubboReference(version = "${dubbo.consumer.UserAddressRpc.version}")
    private UserAddressRpc userAddressRpc;

    /**
    * 创建用户收件地址
    *
    * @param userId 用户编号
    * @param createVO 创建用户收件地址 VO
    * @return 用户收件地址
    */
    public Integer createUserAddress(Integer userId, UserAddressCreateReqVO createVO) {
        CommonResult<Integer> createUserAddressResult = userAddressRpc.createUserAddress(
                UserAddressConvert.INSTANCE.convert(createVO).setUserId(userId));
        createUserAddressResult.checkError();
        return createUserAddressResult.getData();
    }

    /**
    * 更新用户收件地址
    *
    * @param userId 用户编号
    * @param updateVO 更新用户收件地址 VO
    */
    public void updateUserAddress(Integer userId, UserAddressUpdateReqVO updateVO) {
        // 校验是否能够操作
        check(userId, updateVO.getId());
        // 执行更新
        CommonResult<Boolean> updateUserAddressResult = userAddressRpc.updateUserAddress(UserAddressConvert.INSTANCE.convert(updateVO)
            .setUserId(userId));
        updateUserAddressResult.checkError();
    }

    /**
    * 删除用户收件地址
    *
    * @param userId 用户编号
    * @param userAddressId 用户收件地址编号
    */
    public void deleteUserAddress(Integer userId, Integer userAddressId) {
        // 校验是否能够操作
        check(userId, userAddressId);
        // 执行删除
        CommonResult<Boolean> deleteUserAddressResult = userAddressRpc.deleteUserAddress(userAddressId);
        deleteUserAddressResult.checkError();
    }

    /**
    * 获得用户收件地址
    *
    * @param userId 用户编号
    * @param userAddressId 用户收件地址编号
    * @return 用户收件地址
    */
    public UserAddressRespVO getUserAddress(Integer userId, Integer userAddressId) {
        CommonResult<UserAddressRespDTO> getUserAddressResult = userAddressRpc.getUserAddress(userAddressId);
        getUserAddressResult.checkError();
        // 校验是否能够操作
        this.check(userId, userAddressId);
        return UserAddressConvert.INSTANCE.convert(getUserAddressResult.getData());
    }

    /**
    * 获得用户收件地址列表
    *
    * @param userId 用户编号
    * @return 用户收件地址列表
    */
    public List<UserAddressRespVO> listUserAddresses(Integer userId) {
        CommonResult<List<UserAddressRespDTO>> listUserAddressResult = userAddressRpc.listUserAddresses(userId, null);
        listUserAddressResult.checkError();
        return UserAddressConvert.INSTANCE.convertList(listUserAddressResult.getData());
    }

    /**
     * 获得用户的默认收件地址
     *
     * @param userId 用户编号
     * @return 用户收件地址
     */
    public UserAddressRespVO getDefaultUserAddress(Integer userId) {
        CommonResult<List<UserAddressRespDTO>> listUserAddressResult = userAddressRpc.listUserAddresses(userId, UserAddressType.DEFAULT.getType());
        listUserAddressResult.checkError();
        return !CollectionUtils.isEmpty(listUserAddressResult.getData()) ?
                UserAddressConvert.INSTANCE.convert(listUserAddressResult.getData().get(0)) : null;
    }

    /**
     * 校验用户收件地址是不是属于该用户
     *
     * @param userId 用户编号
     * @param userAddressId 用户收件地址
     */
    private void check(Integer userId, Integer userAddressId) {
        CommonResult<UserAddressRespDTO> getUserAddressResult = userAddressRpc.getUserAddress(userAddressId);
        getUserAddressResult.checkError();
        this.check(userId, getUserAddressResult.getData());
    }

    private void check(Integer userId, UserAddressRespDTO userAddressRespDTO) {
        if (!userAddressRespDTO.getUserId().equals(userId)) {
            throw new GlobalException(FORBIDDEN);
        }
    }

}
