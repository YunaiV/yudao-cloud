package cn.iocoder.mall.product.application.controller.users;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.product.api.UserProductSpuCollectionsService;
import cn.iocoder.mall.product.api.bo.UserProductSpuCollectionsPageBO;
import cn.iocoder.mall.product.api.dto.UserProductSpuCollectionsPageDTO;
import cn.iocoder.mall.security.core.context.UserSecurityContextHolder;
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
    @ApiOperation("用户商品收藏列表")
    public CommonResult<UserProductSpuCollectionsPageBO> getUserProductSpuCollectionsPage(
            @Validated UserProductSpuCollectionsPageDTO userProductSpuCollectionsPageDTO) {
        final Integer userId = UserSecurityContextHolder.getContext().getUserId();
        userProductSpuCollectionsPageDTO.setUserId(userId);
        return userProductSpuCollectionsService.getUserProductSpuCollectionsPage(userProductSpuCollectionsPageDTO);
    }

    @DeleteMapping("remove")
    @ApiOperation(value = "用户商品收藏-删除")
    public CommonResult<Boolean> removeUserFavorite(@RequestParam("spuId") final Integer spuId) {
        final Integer userId = UserSecurityContextHolder.getContext().getUserId();
        return userProductSpuCollectionsService.deleteUserProductSpuCollections(userId, spuId);
    }

    @GetMapping("hasUserFavorite")
    @ApiOperation(value = "用户商品收藏-是否收藏")
    public CommonResult<Boolean> hasUserSpuFavorite(@RequestParam("spuId") final Integer spuId) {
        final Integer userId = UserSecurityContextHolder.getContext().getUserId();
        return userProductSpuCollectionsService.hasUserSpuFavorite(spuId, userId);
    }
}
