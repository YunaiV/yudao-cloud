package cn.iocoder.mall.product.application.controller.users;

import cn.iocoder.mall.product.api.ProductSpuService;
import cn.iocoder.mall.product.api.bo.ProductSpuBO;
import cn.iocoder.mall.product.application.vo.ProductSpuListVO;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user/product/spu")
public class ProductSpuController {

    @Reference(validation = "true")
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