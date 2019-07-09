package cn.iocoder.mall.user.application.controller.users;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.user.api.UserProductSpuCollectionsService;
import cn.iocoder.mall.user.api.bo.UserProductSpuCollectionsPageBO;
import cn.iocoder.mall.user.api.dto.UserProductSpuCollectionsPageDTO;
import cn.iocoder.mall.user.sdk.annotation.RequiresLogin;
import cn.iocoder.mall.user.sdk.context.UserSecurityContextHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 用户收藏
 * @author xiaofeng
 * @date 2019/07/07 11:06
 * @version 1.0
 */
@RestController
@RequestMapping("users/favorite")
@Api("用户收藏")
public class UserFavoriteController {

    @Reference(validation = "true", version = "${dubbo.provider.UserProductSpuCollectionsService.version}")
    private UserProductSpuCollectionsService userProductSpuCollectionsService;

    @GetMapping("page")
    @RequiresLogin
    @ApiOperation("用户商品收藏列表")
    public CommonResult<UserProductSpuCollectionsPageBO> getUserProductSpuCollectionsPage(
            @Validated UserProductSpuCollectionsPageDTO userProductSpuCollectionsPageDTO) {
        final Integer userId = UserSecurityContextHolder.getContext().getUserId();
        userProductSpuCollectionsPageDTO.setUserId(userId);
        return userProductSpuCollectionsService.getUserProductSpuCollectionsPage(userProductSpuCollectionsPageDTO);
    }

    @DeleteMapping("remove")
    @RequiresLogin
    @ApiOperation(value = "用户商品收藏-删除")
    public CommonResult<Boolean> removeFavorite(@RequestParam("spuId") final Integer spuId) {
        final Integer userId = UserSecurityContextHolder.getContext().getUserId();
        return userProductSpuCollectionsService.deleteUserProductSpuCollections(userId, spuId);
    }
}
