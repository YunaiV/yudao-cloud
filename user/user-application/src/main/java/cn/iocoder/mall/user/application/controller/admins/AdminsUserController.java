package cn.iocoder.mall.user.application.controller.admins;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.user.application.convert.UserConvert;
import cn.iocoder.mall.user.application.vo.admins.AdminsUserPageVO;
import cn.iocoder.mall.user.service.api.UserService;
import cn.iocoder.mall.user.service.api.bo.UserPageBO;
import cn.iocoder.mall.user.service.api.dto.UserPageDTO;
import com.alibaba.dubbo.config.annotation.Reference;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admins/user")
@Api("用户模块")
public class AdminsUserController {

    @Reference(validation = "true")
    private UserService userService;

    // 分页
    @GetMapping("/page")
    @ApiOperation(value = "管理员分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "nickname", value = "昵称，模糊匹配", example = "小王"),
            @ApiImplicitParam(name = "pageNo", value = "页码，从 0 开始", example = "0"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = true, example = "10"),
    })
    public CommonResult<AdminsUserPageVO> page(@RequestParam(value = "nickname", required = false) String nickname,
                                               @RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
                                               @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        // 创建 UserPageDTO
        UserPageDTO userPageDTO = new UserPageDTO().setNickname(nickname).setPageNo(pageNo).setPageSize(pageSize);
        // 查询分页
        CommonResult<UserPageBO> result = userService.getUserPage(userPageDTO);
        // 转换结果
        return UserConvert.INSTANCE.convert(result);
    }

    // 更新用户信息

    // 开启禁用

}