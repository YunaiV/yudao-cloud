package cn.iocoder.mall.product.application.controller.admins;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.product.api.ProductSpuService;
import cn.iocoder.mall.product.api.bo.ProductSpuBO;
import cn.iocoder.mall.product.api.bo.ProductSpuDetailBO;
import cn.iocoder.mall.product.api.bo.ProductSpuPageBO;
import cn.iocoder.mall.product.api.dto.*;
import cn.iocoder.mall.product.application.convert.ProductSpuConvert;
import cn.iocoder.mall.product.application.vo.admins.AdminsProductSpuDetailVO;
import cn.iocoder.mall.product.application.vo.admins.AdminsProductSpuPageVO;
import cn.iocoder.mall.product.application.vo.admins.AdminsProductSpuVO;
import cn.iocoder.mall.security.core.context.AdminSecurityContextHolder;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

import static cn.iocoder.common.framework.vo.CommonResult.success;

@RestController
@RequestMapping("admins")
@Api("商品 SPU + SKU")
public class AdminsProductSpuController {

    @PostMapping("/spu/update_sort")
    @ApiOperation("更新商品的排序")
    public CommonResult<Boolean> updateSort(@RequestParam("id") Integer id,
                                            @RequestParam("sort") Integer sort) {
        return success(productSpuService.updateProductSpuSort(AdminSecurityContextHolder.getContext().getAdminId(), id, sort));
    }

    @GetMapping("/spu/search_list")
    @ApiOperation("商品 SPU 搜索列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "商品名称，模糊匹配", example = "小王"),
    })
    public CommonResult<List<AdminsProductSpuVO>> spuSearchList(@RequestParam(value = "name", required = false) String name) {
        // 创建 ProductSpuSearchListDTO 对象
        ProductSpuSearchListDTO productSpuSearchListDTO = new ProductSpuSearchListDTO().setName(name);
        // 执行搜索
        List<ProductSpuBO> list = productSpuService.getProductSpuSearchList(productSpuSearchListDTO);
        // 转换返回
        return success(ProductSpuConvert.INSTANCE.convert3(list));
    }

    @GetMapping("/spu/info")
    @ApiOperation("商品 SPU 明细")
    @ApiImplicitParam(name = "id", value = "SPU 编号", required = true, example = "100")
    public CommonResult<AdminsProductSpuDetailVO> spuInfo(@RequestParam("id") Integer id) {
        return success(ProductSpuConvert.INSTANCE.convert(productSpuService.getProductSpuDetail(id)));
    }

    @GetMapping("/spu/list")
    @ApiOperation("商品 SPU 列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "商品 SPU 编号数组", example = "1,2,3"),
    })
    public CommonResult<List<AdminsProductSpuVO>> spuList(@RequestParam("ids") Collection<Integer> ids) {
        List<ProductSpuBO> list = productSpuService.getProductSpuList(ids);
        // 转换返回
        return success(ProductSpuConvert.INSTANCE.convert3(list));
    }

}
