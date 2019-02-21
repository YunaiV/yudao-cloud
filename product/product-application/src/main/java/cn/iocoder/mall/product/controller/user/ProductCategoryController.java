package cn.iocoder.mall.product.controller.user;

import cn.iocoder.mall.product.vo.ProductCategoryVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("user/product/category")
public class ProductCategoryController {

    // TODO 获得父编号为 id 的分类们 后面，使用 swagger 注释
    @GetMapping
    public List<ProductCategoryVO> list(@RequestParam("id") Integer id) {
        return new ArrayList<>();
    }

}