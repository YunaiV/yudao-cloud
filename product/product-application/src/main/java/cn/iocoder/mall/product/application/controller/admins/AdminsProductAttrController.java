package cn.iocoder.mall.product.application.controller.admins;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.product.api.ProductAttrService;
import cn.iocoder.mall.product.api.bo.ProductAttrPageBO;
import cn.iocoder.mall.product.api.bo.ProductAttrSimpleBO;
import cn.iocoder.mall.product.api.dto.ProductAttrPageDTO;
import cn.iocoder.mall.product.application.convert.ProductAttrConvert;
import cn.iocoder.mall.product.application.vo.admins.AdminsProductAttrPageVO;
import cn.iocoder.mall.product.application.vo.admins.AdminsProductAttrSimpleVO;
import com.alibaba.dubbo.config.annotation.Reference;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admins")
@Api("商品规格")
public class AdminsProductAttrController {

    @Reference(validation = "true")
    private ProductAttrService productAttrService;

    @GetMapping("/attr/page")
    public CommonResult<AdminsProductAttrPageVO> attrPage(@RequestParam(value = "name", required = false) String name,
                                                          @RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
                                                          @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        // 创建 ProductAttrPageDTO 对象
        ProductAttrPageDTO productAttrPageDTO = new ProductAttrPageDTO().setName(name).setPageNo(pageNo).setPageSize(pageSize);
        // 查询分页
        CommonResult<ProductAttrPageBO> result = productAttrService.getProductAttrPage(productAttrPageDTO);
        // 返回结果
        return ProductAttrConvert.INSTANCE.convert2(result);
    }

    @GetMapping("/attr/tree")
    public CommonResult<List<AdminsProductAttrSimpleVO>> tree() {
        // 查询全列表
        CommonResult<List<ProductAttrSimpleBO>> result = productAttrService.getProductAttrList();
        // 返回结果
        return ProductAttrConvert.INSTANCE.convert(result);
    }

    @PostMapping("/attr/add")
    public CommonResult addAttr() {
        return null;
    }

    public CommonResult<Boolean> updateAttr() {
        return null;
    }

    public CommonResult<Boolean> updateAttrStatus() {
        return null;
    }

    // TODO 芋艿 暂时不考虑 delete Attr 。因为关联逻辑比较多

    public CommonResult addAttrValue() {
        return null;
    }

    public CommonResult<Boolean> updateAttrValue() {
        return null;
    }

    public CommonResult<Boolean> updateAttrValueStr() {
        return null;
    }

    // TODO 芋艿 暂时不考虑 delete Attr Value 。因为关联逻辑比较多

}