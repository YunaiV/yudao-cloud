package cn.iocoder.mall.system.rest.controller.admin;

import cn.iocoder.common.framework.enums.MallConstants;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.system.biz.bo.user.UserBO;
import cn.iocoder.mall.system.biz.service.user.UserService;
import cn.iocoder.mall.system.rest.convert.admin.AdminsUserConvert;
import cn.iocoder.mall.system.rest.request.admin.AdminsUserPageRequest;
import cn.iocoder.mall.system.rest.request.admin.AdminsUserUpdateRequest;
import cn.iocoder.mall.system.rest.request.admin.AdminsUserUpdateStatusRequest;
import cn.iocoder.mall.system.rest.response.admin.AdminsUserPageResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static cn.iocoder.common.framework.vo.CommonResult.success;

/**
 * @Author: jiangweifan
 * @Date: 2020/5/12
 * @Description: 管理员 - 用户信息
 */
@RestController
@RequestMapping(MallConstants.ROOT_PATH_ADMIN + "/user-info")
@Api(tags = "管理员 - 用户信息 API")
public class AdminsUserController {

    @Autowired
    private UserService userService;

    @GetMapping("/page")
    @ApiOperation(value = "用户分页列表")
    public CommonResult<PageResult<AdminsUserPageResponse>> page(AdminsUserPageRequest adminsUserPageRequest) {
        PageResult<UserBO> userPage = userService.getUserPage(AdminsUserConvert.INSTANCE.convertToPageDTO(adminsUserPageRequest));
        return success(AdminsUserConvert.INSTANCE.convertToPageResponse(userPage));
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新用户基本信息")
    public CommonResult<Boolean> update(AdminsUserUpdateRequest adminsUserUpdateRequest) {
        return success(userService.updateUserInfo(AdminsUserConvert.INSTANCE.convertToUpdateDTO(adminsUserUpdateRequest)));
    }

    @PostMapping("/update_status")
    @ApiOperation(value = "更新用户状态")
    public CommonResult<Boolean> updateStatus(AdminsUserUpdateStatusRequest adminsUserUpdateStatusRequest) {
        return success(userService.updateUserStatus(AdminsUserConvert.INSTANCE.convertToUpdateStatusDTO(adminsUserUpdateStatusRequest)));
    }
}
