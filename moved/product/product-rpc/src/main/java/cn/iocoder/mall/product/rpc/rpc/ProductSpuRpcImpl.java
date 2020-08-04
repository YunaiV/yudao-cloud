package cn.iocoder.mall.product.rpc.rpc;

import cn.iocoder.mall.product.biz.bo.product.ProductSpuDetailBO;
import cn.iocoder.mall.product.biz.service.spu.ProductSpuService;
import cn.iocoder.mall.product.rpc.api.ProductSpuRpc;
import cn.iocoder.mall.product.rpc.convert.ProductSpuConvert;
import cn.iocoder.mall.product.rpc.response.ProductSpuDetailResponse;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service(validation = "true", version = "${dubbo.provider.ProductSpuService.version}")
public class ProductSpuRpcImpl implements ProductSpuRpc {

    @Autowired
    private ProductSpuService productSpuService;

    @Override
    public ProductSpuDetailResponse getProductSpuDetail(Integer spuId) {
        ProductSpuDetailBO productSpuDetail = productSpuService.getProductSpuDetail(spuId);
        return ProductSpuConvert.INSTANCE.convertDetail(productSpuDetail);
    }


}
