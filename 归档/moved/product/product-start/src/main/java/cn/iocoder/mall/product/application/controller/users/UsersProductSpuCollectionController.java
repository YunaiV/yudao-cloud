package cn.iocoder.mall.product.application.controller.users;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.product.api.ProductSpuCollectionService;
import cn.iocoder.mall.security.core.context.UserSecurityContextHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static cn.iocoder.common.framework.vo.CommonResult.success;

/**
 * 商品收藏接口
 * @author xiaofeng
 * @date 2019/07/01 23:21
 * @version 1.0
 */
@RestController
@RequestMapping("users/spu")
@Api("商品收藏")
public class UsersProductSpuCollectionController {

    @Reference(validation = "true", version = "${dubbo.provider.ProductSpuCollectionService.version}")
    private ProductSpuCollectionService productSpuCollectionService;

    @PostMapping("/collection/{spuId}/{hasCollectionType}")
    @ApiOperation("商品收藏")
    public CommonResult<Boolean> productSpuCollection(@PathVariable("spuId") Integer spuId,
            @PathVariable("hasCollectionType") Integer hasCollectionType) {
        final Integer userId = UserSecurityContextHolder.getContext().getUserId();
        return success(productSpuCollectionService.productSpuCollection(spuId, hasCollectionType,userId));
    }
}
