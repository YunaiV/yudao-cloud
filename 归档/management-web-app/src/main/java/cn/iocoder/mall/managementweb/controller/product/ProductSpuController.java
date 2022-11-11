package cn.iocoder.mall.managementweb.controller.product;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.managementweb.controller.product.vo.spu.ProductSpuCreateReqVO;
import cn.iocoder.mall.managementweb.controller.product.vo.spu.ProductSpuPageReqVO;
import cn.iocoder.mall.managementweb.controller.product.vo.spu.ProductSpuRespVO;
import cn.iocoder.mall.managementweb.controller.product.vo.spu.ProductSpuUpdateReqVO;
import cn.iocoder.mall.managementweb.manager.product.ProductSpuManager;
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
* 商品 SPU Controller
*/
@RestController
@RequestMapping("/product-spu")
@Api(tags = "商品 SPU")
@Validated
public class ProductSpuController {

    @Autowired
    private ProductSpuManager productSpuManager;

    @GetMapping("/page")
    @ApiOperation("获得商品 SPU 分页")
    public CommonResult<PageResult<ProductSpuRespVO>> pageProductSpu(ProductSpuPageReqVO pageVO) {
        // 全部：无搜索条件
        // 在售中：visible = true && hasQuantity = true
        // 已售罄：visible  = true && hasQuantity = false
        // 仓库中：visible = false
        return success(productSpuManager.pageProductSpu(pageVO));
    }

    // TODO 芋艿，删除功能暂时不做。主要原因是，关联的数据太多。删除带来的问题会比较大


}
