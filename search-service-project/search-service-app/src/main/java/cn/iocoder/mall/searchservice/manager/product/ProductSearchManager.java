package cn.iocoder.mall.searchservice.manager.product;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.productservice.rpc.category.ProductCategoryRpc;
import cn.iocoder.mall.productservice.rpc.sku.ProductSkuRpc;
import cn.iocoder.mall.productservice.rpc.sku.dto.ProductSkuListQueryReqDTO;
import cn.iocoder.mall.productservice.rpc.sku.dto.ProductSkuRespDTO;
import cn.iocoder.mall.productservice.rpc.spu.ProductSpuRpc;
import cn.iocoder.mall.productservice.rpc.spu.dto.ProductSpuRespDTO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductSearchManager {

    @DubboReference(version = "${dubbo.consumer.ProductSpuRpc.version}")
    private ProductSpuRpc productSpuRpc;
    @DubboReference(version = "${dubbo.consumer.ProductSkuRpc.version}")
    private ProductSkuRpc productSkuRpc;
    @DubboReference(version = "${dubbo.consumer.ProductCategoryRpc.version}")
    private ProductCategoryRpc productCategoryRpc;

//    @DubboReference( version = "${dubbo.consumer.CartService.version}")
//    private CartService cartService;

    public Boolean saveProduct(Integer id) {
        // 获得商品 SPU
        CommonResult<ProductSpuRespDTO> productSpuResult = productSpuRpc.getProductSpu(id);
        productSpuResult.checkError();
        // 获得商品 SKU
        CommonResult<List<ProductSkuRespDTO>> listProductSkusResult =
                productSkuRpc.listProductSkus(new ProductSkuListQueryReqDTO().setProductSpuId(id));
        listProductSkusResult.checkError();

        return true;
    }

}
