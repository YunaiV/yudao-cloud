package cn.iocoder.mall.tradeservice.client.product;

import cn.iocoder.common.framework.util.CollectionUtils;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.productservice.rpc.sku.ProductSkuFeign;
import cn.iocoder.mall.productservice.rpc.sku.dto.ProductSkuListQueryReqDTO;
import cn.iocoder.mall.productservice.rpc.sku.dto.ProductSkuRespDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class ProductSkuClient {
    @Autowired
    private ProductSkuFeign productSkuFeign;

    public List<ProductSkuRespDTO> listProductSkus(Collection<Integer> productSkuIds, String... fields) {
        if (CollectionUtils.isEmpty(productSkuIds)) {
            return Collections.emptyList();
        }
        CommonResult<List<ProductSkuRespDTO>> listProductSkusResult = productSkuFeign.listProductSkus(
                new ProductSkuListQueryReqDTO().setProductSkuIds(productSkuIds).setFields(Arrays.asList(fields)));
        listProductSkusResult.checkError();
        return listProductSkusResult.getData();
    }

}
