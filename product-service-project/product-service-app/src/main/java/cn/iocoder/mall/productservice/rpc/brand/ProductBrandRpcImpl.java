package cn.iocoder.mall.productservice.rpc.brand;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.productservice.manager.brand.ProductBrandManager;
import cn.iocoder.mall.productservice.rpc.brand.dto.ProductBrandCreateReqDTO;
import cn.iocoder.mall.productservice.rpc.brand.dto.ProductBrandPageReqDTO;
import cn.iocoder.mall.productservice.rpc.brand.dto.ProductBrandRespDTO;
import cn.iocoder.mall.productservice.rpc.brand.dto.ProductBrandUpdateReqDTO;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static cn.iocoder.common.framework.vo.CommonResult.success;

/**
* 商品品牌 Rpc 实现类
*/
@DubboService
public class ProductBrandRpcImpl implements ProductBrandRpc {

    @Autowired
    private ProductBrandManager productBrandManager;

    @Override
    public CommonResult<Integer> createProductBrand(ProductBrandCreateReqDTO createDTO) {
        return success(productBrandManager.createProductBrand(createDTO));
    }

    @Override
    public CommonResult<Boolean> updateProductBrand(ProductBrandUpdateReqDTO updateDTO) {
        productBrandManager.updateProductBrand(updateDTO);
        return success(true);
    }

    @Override
    public CommonResult<Boolean> deleteProductBrand(Integer productBrandId) {
        productBrandManager.deleteProductBrand(productBrandId);
        return success(true);
    }

    @Override
    public CommonResult<ProductBrandRespDTO> getProductBrand(Integer productBrandId) {
        return success(productBrandManager.getProductBrand(productBrandId));
    }

    @Override
    public CommonResult<List<ProductBrandRespDTO>> listProductBrands(List<Integer> productBrandIds) {
        return success(productBrandManager.listProductBrands(productBrandIds));
    }

    @Override
    public CommonResult<PageResult<ProductBrandRespDTO>> pageProductBrand(ProductBrandPageReqDTO pageDTO) {
        return success(productBrandManager.pageProductBrand(pageDTO));
    }

}
