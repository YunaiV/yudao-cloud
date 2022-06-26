package cn.iocoder.mall.managementweb.manager.user;


import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.managementweb.controller.user.vo.UserPageReqVO;
import cn.iocoder.mall.managementweb.controller.user.vo.UserRespVO;
import cn.iocoder.mall.managementweb.controller.user.vo.UserUpdateInfoReqVO;
import cn.iocoder.mall.managementweb.controller.user.vo.UserUpdateStatusReqVO;
import cn.iocoder.mall.managementweb.convert.user.UserConvert;
import cn.iocoder.mall.userservice.rpc.user.UserFeign;
import cn.iocoder.mall.userservice.rpc.user.dto.UserRespDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户 Manager
 */
@Service
public class UserManager {


    @Autowired
    private UserFeign userFeign;
    /**
     * 更新用户信息
     *
     * @param updateInfoReqVO 更新用户信息 VO
     */
    public void updateUserInfo(UserUpdateInfoReqVO updateInfoReqVO) {
        CommonResult<Boolean> updateUserResult = userFeign.updateUser(UserConvert.INSTANCE.convert(updateInfoReqVO));
        updateUserResult.checkError();
    }

    /**
     * 更新用户状态
     *
     * @param updateStatusReqVO 更新用户状态 VO
     */
    public void updateUserStatus(UserUpdateStatusReqVO updateStatusReqVO) {
        CommonResult<Boolean> updateUserResult = userFeign.updateUser(UserConvert.INSTANCE.convert(updateStatusReqVO));
        updateUserResult.checkError();
    }

    /**
     * 获得用户
     *
     * @param userId 用户编号
     * @return 用户
     */
    public UserRespVO getUser(Integer userId) {
        CommonResult<UserRespDTO> getUserResult = userFeign.getUser(userId);
        getUserResult.checkError();
        return UserConvert.INSTANCE.convert(getUserResult.getData());
    }

    /**
     * 获得用户列表
     *
     * @param userIds 用户编号列表
     * @return 用户列表
     */
    public List<UserRespVO> listUsers(List<Integer> userIds) {
        CommonResult<List<UserRespDTO>> listUserResult = userFeign.listUsers(userIds);
        listUserResult.checkError();
        return UserConvert.INSTANCE.convertList(listUserResult.getData());
    }

    /**
     * 获得用户分页
     *
     * @param pageVO 用户分页查询
     * @return 用户分页结果
     */
    public PageResult<UserRespVO> pageUser(UserPageReqVO pageVO) {
        CommonResult<PageResult<UserRespDTO>> pageUserResult = userFeign.pageUser(UserConvert.INSTANCE.convert(pageVO));
        pageUserResult.checkError();
        return UserConvert.INSTANCE.convertPage(pageUserResult.getData());
    }

}
