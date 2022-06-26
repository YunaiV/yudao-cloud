package cn.iocoder.mall.shopweb.controller.product;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.shopweb.controller.product.vo.product.ProductSpuDetailRespVO;
import cn.iocoder.mall.shopweb.controller.product.vo.product.ProductSpuPageReqVO;
import cn.iocoder.mall.shopweb.controller.product.vo.product.ProductSpuRespVO;
import cn.iocoder.mall.shopweb.controller.product.vo.product.ProductSpuSearchConditionRespVO;
import cn.iocoder.mall.shopweb.service.product.ProductSpuManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static cn.iocoder.common.framework.vo.CommonResult.success;

@Api(tags = "商品 SPU API")
@RestController
@RequestMapping("/product-spu")
@Validated
public class ProductSpuController {

    @Autowired
    private ProductSpuManager productSpuManager;

    @GetMapping("/page")
    @ApiOperation("获得商品 SPU 的分页")
    public CommonResult<PageResult<ProductSpuRespVO>> pageProductSpu(ProductSpuPageReqVO pageReqVO) {
        return success(productSpuManager.pageProductSpu(pageReqVO));
    }

    @GetMapping("/search-condition")
    @ApiOperation("获得商品的搜索条件")
    @ApiImplicitParam(name = "keyword", value = "关键字", example = "芋艿")
    public CommonResult<ProductSpuSearchConditionRespVO> getProductSpuSearchCondition(
            @RequestParam(value = "keyword", required = false) String keyword) {
        return success(productSpuManager.getProductSpuSearchCondition(keyword));
    }

    @GetMapping("/get-detail")
    @ApiOperation("获得商品 SPU 的明细，包括 SKU 等等信息")
    @ApiImplicitParam(name = "id", required = true, value = "商品 SPU 编号", example = "1024")
    public CommonResult<ProductSpuDetailRespVO> getProductSpuDetail(@RequestParam("id") Integer id) {
        return success(productSpuManager.getProductSpuDetail(id));
    }

}
