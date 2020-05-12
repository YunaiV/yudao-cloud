package cn.iocoder.mall.product.rest.controller.attr;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.product.biz.bo.attr.ProductAttrBO;
import cn.iocoder.mall.product.biz.bo.attr.ProductAttrSimpleWithValueBO;
import cn.iocoder.mall.product.biz.bo.attr.ProductAttrValueBO;
import cn.iocoder.mall.product.biz.bo.attr.ProductAttrWithValueBO;
import cn.iocoder.mall.product.biz.dto.attr.*;
import cn.iocoder.mall.product.biz.service.product.ProductAttrService;
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

    @GetMapping("/attr/page")
    @ApiOperation("获得规格分页")
    public CommonResult<PageResult<AdminsProductAttrPageResponse>> attrPage(ProductAttrPageRequest request) {
        ProductAttrPageDTO pageDTO = ProductAttrConvert.INSTANCE.convert(request);
        PageResult<ProductAttrWithValueBO> productAttrPage = productAttrService.getProductAttrPage(pageDTO);
        PageResult<AdminsProductAttrPageResponse> adminPageResponse = ProductAttrConvert.INSTANCE.convertPage(productAttrPage);
        return CommonResult.success(adminPageResponse);
    }

    @GetMapping("/attr/tree")
    @ApiOperation(value = "获得规格树结构", notes = "该接口返回的信息更为精简。一般用于前端缓存数据字典到本地。")
    public CommonResult<List<AdminsProductAttrSimpleResponse>> tree() {
        // 查询全列表
        List<ProductAttrSimpleWithValueBO> result = productAttrService.getProductAttrList();
        return CommonResult.success(ProductAttrConvert.INSTANCE.convertSimple(result));
    }

    @PostMapping("/attr/add")
    @ApiOperation(value = "创建商品规格")
    public CommonResult<AdminsProdutAttrResponse> addAttr(@Validated ProductAttrAddRequest addRequest) {
        // 创建 ProductAttrAddDTO 对象
        ProductAttrAddDTO productAttrAddDTO = new ProductAttrAddDTO().setName(addRequest.getName());
        // 添加
        ProductAttrBO result = productAttrService.addProductAttr(AdminSecurityContextHolder.getContext().getAdminId(), productAttrAddDTO);
        return CommonResult.success(ProductAttrConvert.INSTANCE.convertAttr(result));
    }

    @PostMapping("/attr/update")
    @ApiOperation(value = "修改商品规格")
    public CommonResult<Boolean> updateAttr(@Validated ProductAttrUpdateRequest updateRequest) {
        ProductAttrUpdateDTO productAttrUpdateDTO = ProductAttrConvert.INSTANCE.convertUpdate(updateRequest);
        // 更新
        return CommonResult.success(productAttrService.updateProductAttr(AdminSecurityContextHolder.getContext().getAdminId(), productAttrUpdateDTO));
    }

    @PostMapping("/attr/update_status")
    @ApiOperation(value = "修改商品规格状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "规格编号", required = true, example = "100"),
            @ApiImplicitParam(name = "status", value = "状态", required = true, example = "1")
    })
    public CommonResult<Boolean> updateAttrStatus(@RequestParam("id") Integer id,
                                                  @RequestParam("status") Integer status) {
        return CommonResult.success(productAttrService.updateProductAttrStatus(AdminSecurityContextHolder.getContext().getAdminId(), id, status));
    }

    // TODO 芋艿 暂时不考虑 delete Attr 。因为关联逻辑比较多

    @PostMapping("/attr_value/add")
    @ApiOperation(value = "创建商品规格值")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "attrId", value = "规格编号", required = true, example = "100"),
            @ApiImplicitParam(name = "name", value = "规格值", required = true, example = "蓝色")
    })
    public CommonResult<AdminsProductAttrValueResponse> addAttrValue(@Validated ProductAttrValueAddRequest addRequest) {
        // 创建 ProductAttrValueAddDTO 对象
        ProductAttrValueAddDTO productAttrValueAddDTO = new ProductAttrValueAddDTO().setAttrId(addRequest.getAttrId()).setName(addRequest.getName());
        // 添加
        ProductAttrValueBO result = productAttrService.addProductAttrValue(AdminSecurityContextHolder.getContext().getAdminId(), productAttrValueAddDTO);
        // 返回结果
        return CommonResult.success(ProductAttrConvert.INSTANCE.convertAddResponse(result));
    }

    @PostMapping("/attr_value/update")
    @ApiOperation(value = "修改商品规格值")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "规格值编号", required = true, example = "100"),
            @ApiImplicitParam(name = "name", value = "规格值", required = true, example = "蓝色")
    })
    public CommonResult<Boolean> updateAttrValue(@RequestParam("id") Integer id,
                                                 @RequestParam("name") String name) {
        // 创建 ProductAttrValueUpdateDTO 对象
        ProductAttrValueUpdateDTO productAttrValueUpdateDTO = new ProductAttrValueUpdateDTO().setId(id).setName(name);
        // 更新
        return CommonResult.success(productAttrService.updateProductAttrValue(AdminSecurityContextHolder.getContext().getAdminId(), productAttrValueUpdateDTO));
    }

    @PostMapping("/attr_value/update_status")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "规格编号", required = true, example = "100"),
            @ApiImplicitParam(name = "status", value = "状态", required = true, example = "1")
    })
    public CommonResult<Boolean> updateAttrValueStatus(@RequestParam("id") Integer id,
                                                       @RequestParam("status") Integer status) {
        return CommonResult.success(productAttrService.updateProductAttrValueStatus(AdminSecurityContextHolder.getContext().getAdminId(), id, status));
    }

    // TODO 芋艿 暂时不考虑 delete Attr Value 。因为关联逻辑比较多

}
