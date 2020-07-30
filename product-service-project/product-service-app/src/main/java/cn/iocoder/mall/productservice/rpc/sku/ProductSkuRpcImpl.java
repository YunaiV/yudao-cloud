package cn.iocoder.mall.productservice.rpc.sku;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.productservice.manager.sku.ProductSkuManager;
import cn.iocoder.mall.productservice.rpc.sku.dto.ProductSkuListQueryReqDTO;
import cn.iocoder.mall.productservice.rpc.sku.dto.ProductSkuRespDTO;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static cn.iocoder.common.framework.vo.CommonResult.success;

/**
 * 商品 SKU Rpc 实现类
 */
@DubboService
public class ProductSkuRpcImpl implements ProductSkuRpc {

    @Autowired
    private ProductSkuManager productSkuManager;

    @Override
    public CommonResult<ProductSkuRespDTO> getProductSku(Integer productSkuId) {
        return success(productSkuManager.getProductSku(productSkuId));
    }

    @Override
    public CommonResult<List<ProductSkuRespDTO>> listProductSkus(ProductSkuListQueryReqDTO queryReqDTO) {
        return success(productSkuManager.listProductSkus(queryReqDTO));
    }

}
