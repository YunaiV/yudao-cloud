package cn.iocoder.mall.productservice.rpc.attr;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.productservice.manager.attr.ProductAttrManager;
import cn.iocoder.mall.productservice.rpc.attr.dto.*;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static cn.iocoder.common.framework.vo.CommonResult.success;

/**
 * 商品规格 Rpc 实现类
 */
@DubboService
public class ProductAttrRpcImpl implements ProductAttrRpc {

    @Autowired
    private ProductAttrManager productAttrManager;

    @Override
    public CommonResult<Integer> createProductAttrKey(ProductAttrKeyCreateReqDTO createDTO) {
        return success(productAttrManager.createProductAttrKey(createDTO));
    }

    @Override
    public CommonResult<Boolean> updateProductAttrKey(ProductAttrKeyUpdateReqDTO updateDTO) {
        productAttrManager.updateProductAttrKey(updateDTO);
        return success(true);
    }

    @Override
    public CommonResult<ProductAttrKeyRespDTO> getProductAttrKey(Integer productAttrKeyId) {
        return success(productAttrManager.getProductAttrKey(productAttrKeyId));
    }

    @Override
    public CommonResult<List<ProductAttrKeyRespDTO>> listProductAttrKeys(List<Integer> productAttrKeyIds) {
        return success(productAttrManager.listProductAttrKeys(productAttrKeyIds));
    }

    @Override
    public CommonResult<PageResult<ProductAttrKeyRespDTO>> pageProductAttrKey(ProductAttrKeyPageReqDTO pageDTO) {
        return success(productAttrManager.pageProductAttrKey(pageDTO));
    }

    @Override
    public CommonResult<Integer> createProductAttrValue(ProductAttrValueCreateReqDTO createDTO) {
        return success(productAttrManager.createProductAttrValue(createDTO));
    }

    @Override
    public CommonResult<Boolean> updateProductAttrValue(ProductAttrValueUpdateReqDTO updateDTO) {
        productAttrManager.updateProductAttrValue(updateDTO);
        return success(true);
    }

    @Override
    public CommonResult<ProductAttrValueRespDTO> getProductAttrValue(Integer productAttrValueId) {
        return success(productAttrManager.getProductAttrValue(productAttrValueId));
    }

    @Override
    public CommonResult<List<ProductAttrValueRespDTO>> listProductAttrValues(ProductAttrValueListQueryReqDTO queryDTO) {
        return success(productAttrManager.listProductAttrValues(queryDTO));
    }

}
