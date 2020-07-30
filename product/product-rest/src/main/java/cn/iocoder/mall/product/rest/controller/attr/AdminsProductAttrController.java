package cn.iocoder.mall.product.rest.controller.attr;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.product.biz.bo.attr.ProductAttrBO;
import cn.iocoder.mall.product.biz.bo.attr.ProductAttrSimpleWithValueBO;
import cn.iocoder.mall.product.biz.bo.attr.ProductAttrValueBO;
import cn.iocoder.mall.product.biz.bo.attr.ProductAttrWithValueBO;
import cn.iocoder.mall.product.biz.dto.attr.*;
import cn.iocoder.mall.product.biz.service.attr.ProductAttrService;
import cn.iocoder.mall.product.rest.convert.attr.ProductAttrConvert;
import cn.iocoder.mall.product.rest.request.attr.ProductAttrPageRequest;
import cn.iocoder.mall.product.rest.request.attr.ProductAttrAddRequest;
import cn.iocoder.mall.product.rest.request.attr.ProductAttrUpdateRequest;
import cn.iocoder.mall.product.rest.request.attr.ProductAttrValueAddRequest;
import cn.iocoder.mall.product.rest.response.attr.AdminsProductAttrPageResponse;
import cn.iocoder.mall.product.rest.response.attr.AdminsProductAttrSimpleResponse;
import cn.iocoder.mall.product.rest.response.attr.AdminsProdutAttrResponse;
import cn.iocoder.mall.product.rest.response.attr.AdminsProductAttrValueResponse;
import cn.iocoder.mall.security.core.context.AdminSecurityContextHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品规格
 *
 * @author lanmao
 * @version 1.0
 * @date 2020/05/06 16:36
 */
@RestController
@RequestMapping("admins")
@Api("商品规格")
public class AdminsProductAttrController {

    @Autowired
    private ProductAttrService productAttrService;

    @GetMapping("/attr/tree")
    @ApiOperation(value = "获得规格树结构", notes = "该接口返回的信息更为精简。一般用于前端缓存数据字典到本地。")
    public CommonResult<List<AdminsProductAttrSimpleResponse>> tree() {
        // 查询全列表
        List<ProductAttrSimpleWithValueBO> result = productAttrService.getProductAttrList();
        return CommonResult.success(ProductAttrConvert.INSTANCE.convertSimple(result));
    }

}
