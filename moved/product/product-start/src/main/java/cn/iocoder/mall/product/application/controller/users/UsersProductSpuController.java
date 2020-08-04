package cn.iocoder.mall.product.application.controller.users;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.product.api.ProductSpuService;
import cn.iocoder.mall.product.api.bo.ProductSpuPageBO;
import cn.iocoder.mall.product.api.dto.ProductSpuPageDTO;
import cn.iocoder.mall.product.application.convert.ProductSpuConvert;
import cn.iocoder.mall.product.application.vo.users.UsersProductSpuDetailVO;
import cn.iocoder.mall.product.application.vo.users.UsersProductSpuPageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static cn.iocoder.common.framework.vo.CommonResult.success;

@RestController
@RequestMapping("users/spu")
@Api("商品 SPU + SKU")
public class UsersProductSpuController {

    @Reference(validation = "true", version = "${dubbo.provider.ProductSpuService.version}")
    private ProductSpuService productSpuService;

    @GetMapping("/info")
    @ApiOperation("商品 SPU 明细")
    @ApiImplicitParam(name = "id", value = "SPU 编号", required = true, example = "100")
    public CommonResult<UsersProductSpuDetailVO> info(@RequestParam("id") Integer id) {
        return success(ProductSpuConvert.INSTANCE.convert4(productSpuService.getProductSpuDetail(id)));
    }

}
