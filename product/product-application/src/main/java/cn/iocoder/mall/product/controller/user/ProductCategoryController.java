package cn.iocoder.mall.product.controller.user;

import cn.iocoder.mall.product.convert.ProductCategoryConvert;
import cn.iocoder.mall.product.service.ProductCategoryService;
import cn.iocoder.mall.product.vo.ProductCategoryVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user/product/category")
@Api("商品分类")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping
    @ApiOperation("获得指定编号下的子分类的数组")
    @ApiImplicitParam(name = "pid", value = "指定分类编号", required = true)
    public List<ProductCategoryVO> list(@RequestParam("pid") Integer pid) {
        return ProductCategoryConvert.INSTANCE.convertToVO(
                productCategoryService.getListByPid(pid)
        );
    }

}