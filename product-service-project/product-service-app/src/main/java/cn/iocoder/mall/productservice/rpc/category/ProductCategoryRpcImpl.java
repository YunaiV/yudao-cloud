package cn.iocoder.mall.productservice.rpc.category;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.productservice.manager.category.ProductCategoryManager;
import cn.iocoder.mall.productservice.rpc.category.dto.ProductCategoryCreateReqDTO;
import cn.iocoder.mall.productservice.rpc.category.dto.ProductCategoryListQueryReqDTO;
import cn.iocoder.mall.productservice.rpc.category.dto.ProductCategoryRespDTO;
import cn.iocoder.mall.productservice.rpc.category.dto.ProductCategoryUpdateReqDTO;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;

import static cn.iocoder.common.framework.vo.CommonResult.success;

/**
* 商品分类 Rpc 实现类
*/
@DubboService
public class ProductCategoryRpcImpl implements ProductCategoryRpc {

    @Autowired
    private ProductCategoryManager productCategoryManager;

    @Override
    public CommonResult<Integer> createProductCategory(ProductCategoryCreateReqDTO createDTO) {
        return success(productCategoryManager.createProductCategory(createDTO));
    }

    @Override
    public CommonResult<Boolean> updateProductCategory(ProductCategoryUpdateReqDTO updateDTO) {
        productCategoryManager.updateProductCategory(updateDTO);
        return success(true);
    }

    @Override
    public CommonResult<Boolean> deleteProductCategory(Integer productCategoryId) {
        productCategoryManager.deleteProductCategory(productCategoryId);
        return success(true);
    }

    @Override
    public CommonResult<ProductCategoryRespDTO> getProductCategory(Integer productCategoryId) {
        return success(productCategoryManager.getProductCategory(productCategoryId));
    }

    @Override
    public CommonResult<List<ProductCategoryRespDTO>> listProductCategories(Collection<Integer> productCategoryIds) {
        return success(productCategoryManager.listProductCategories(productCategoryIds));
    }

    @Override
    public CommonResult<List<ProductCategoryRespDTO>> listProductCategories(ProductCategoryListQueryReqDTO listQueryReqDTO) {
        return success(productCategoryManager.listProductCategories(listQueryReqDTO));
    }

}
