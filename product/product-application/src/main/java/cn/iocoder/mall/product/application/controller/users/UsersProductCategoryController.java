package cn.iocoder.mall.product.application.controller.users;

import cn.iocoder.mall.product.api.ProductCategoryService;
import cn.iocoder.mall.product.api.bo.ProductCategoryBO;
import cn.iocoder.mall.product.application.convert.ProductCategoryConvert;
import cn.iocoder.mall.product.application.vo.users.UsersProductCategoryVO;
import com.alibaba.dubbo.config.annotation.Reference;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("productCategoryController_users")
@RequestMapping("users/product/category")
@Api("商品分类")
public class UsersProductCategoryController {

    @Reference(validation = "true")
    private ProductCategoryService productCategoryService;

    @GetMapping
    @ApiOperation("获得指定编号下的子分类的数组")
    @ApiImplicitParam(name = "pid", value = "指定分类编号", required = true, example = "0")
    public List<UsersProductCategoryVO> list(@RequestParam("pid") Integer pid) {
        List<ProductCategoryBO> result = productCategoryService.getListByPid(pid);
        return ProductCategoryConvert.INSTANCE.convertToVO(result);
    }

}