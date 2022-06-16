package cn.iocoder.mall.product.application.controller.admins;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.product.api.ProductAttrService;
import cn.iocoder.mall.product.api.bo.ProductAttrBO;
import cn.iocoder.mall.product.api.bo.ProductAttrPageBO;
import cn.iocoder.mall.product.api.bo.ProductAttrSimpleBO;
import cn.iocoder.mall.product.api.bo.ProductAttrValueBO;
import cn.iocoder.mall.product.api.dto.*;
import cn.iocoder.mall.product.application.convert.ProductAttrConvert;
import cn.iocoder.mall.product.application.vo.admins.AdminsProductAttrPageVO;
import cn.iocoder.mall.product.application.vo.admins.AdminsProductAttrSimpleVO;
import cn.iocoder.mall.product.application.vo.admins.AdminsProductAttrVO;
import cn.iocoder.mall.product.application.vo.admins.AdminsProductAttrValueVO;
import cn.iocoder.mall.security.core.context.AdminSecurityContextHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cn.iocoder.common.framework.vo.CommonResult.success;

@RestController
@RequestMapping("admins")
@Api("商品规格")
public class AdminsProductAttrController {

    @Reference(validation = "true", version = "${dubbo.provider.ProductAttrService.version}")
    private ProductAttrService productAttrService;

    @GetMapping("/attr/tree")
    @ApiOperation(value = "获得规格树结构", notes = "该接口返回的信息更为精简。一般用于前端缓存数据字典到本地。")
    public CommonResult<List<AdminsProductAttrSimpleVO>> tree() {
        // 查询全列表
        List<ProductAttrSimpleBO> result = productAttrService.getProductAttrList();
        // 返回结果
        return success(ProductAttrConvert.INSTANCE.convert(result));
    }

}
