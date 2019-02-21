package cn.iocoder.mall.product.controller.user;

import cn.iocoder.mall.product.bo.ProductSpuBO;
import cn.iocoder.mall.product.service.ProductSpuService;
import cn.iocoder.mall.product.vo.ProductSpuListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user/product/spu")
public class ProductSpuController {

    @Autowired
    private ProductSpuService productSpuService;

    // TODO 详情
    @GetMapping("/info")
    public ProductSpuBO info(@RequestParam("id") Integer id) {
        return productSpuService.getProductSpu(id);
    }

    // TODO 分页
    @GetMapping("/list")
    public ProductSpuListVO list() {
        return null;
    }

}