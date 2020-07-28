package cn.iocoder.mall.managementweb.controller.product;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.managementweb.controller.product.vo.attr.ProductAttrKeyCreateReqVO;
import cn.iocoder.mall.managementweb.controller.product.vo.attr.ProductAttrKeyPageReqVO;
import cn.iocoder.mall.managementweb.controller.product.vo.attr.ProductAttrKeyRespVO;
import cn.iocoder.mall.managementweb.controller.product.vo.attr.ProductAttrKeyUpdateReqVO;
import cn.iocoder.mall.managementweb.manager.product.ProductAttrKeyManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static cn.iocoder.common.framework.vo.CommonResult.success;

/**
 * 商品规格键 Controller
 */
@RestController
@RequestMapping("/product-attr/")
@Api(tags = "商品规格键")
@Validated
public class ProductAttrController {

    @Autowired
    private ProductAttrKeyManager productAttrKeyManager;

    @PostMapping("/key/create")
    @ApiOperation("创建商品规格键")
    public CommonResult<Integer> createProductAttrKey(@Valid ProductAttrKeyCreateReqVO createVO) {
        return success(productAttrKeyManager.createProductAttrKey(createVO));
    }

    @PostMapping("/key/update")
    @ApiOperation("更新商品规格键")
    public CommonResult<Boolean> updateProductAttrKey(@Valid ProductAttrKeyUpdateReqVO updateVO) {
        productAttrKeyManager.updateProductAttrKey(updateVO);
        return success(true);
    }

    @GetMapping("/key/get")
    @ApiOperation("获得商品规格键")
    @ApiImplicitParam(name = "productAttrKeyId", value = "商品规格键编号", required = true)
    public CommonResult<ProductAttrKeyRespVO> getProductAttrKey(@RequestParam("productAttrKeyId") Integer productAttrKeyId) {
        return success(productAttrKeyManager.getProductAttrKey(productAttrKeyId));
    }

    @GetMapping("/key/list")
    @ApiOperation("获得商品规格键列表")
    @ApiImplicitParam(name = "productAttrKeyIds", value = "商品规格键编号列表", required = true)
    public CommonResult<List<ProductAttrKeyRespVO>> listProductAttrKeys(@RequestParam("productAttrKeyIds") List<Integer> productAttrKeyIds) {
        return success(productAttrKeyManager.listProductAttrKeys(productAttrKeyIds));
    }

    @GetMapping("/key/page")
    @ApiOperation("获得商品规格键分页")
    public CommonResult<PageResult<ProductAttrKeyRespVO>> pageProductAttrKey(ProductAttrKeyPageReqVO pageVO) {
        return success(productAttrKeyManager.pageProductAttrKey(pageVO));
    }

}
