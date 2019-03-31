package cn.iocoder.mall.order.biz.mock;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.product.api.ProductSpuService;
import cn.iocoder.mall.product.api.bo.ProductSpuBO;
import cn.iocoder.mall.product.api.bo.ProductSpuDetailBO;
import cn.iocoder.mall.product.api.bo.ProductSpuPageBO;
import cn.iocoder.mall.product.api.dto.ProductSpuAddDTO;
import cn.iocoder.mall.product.api.dto.ProductSpuPageDTO;
import cn.iocoder.mall.product.api.dto.ProductSpuUpdateDTO;

import java.util.Collection;
import java.util.List;

/**
 * @author Sin
 * @time 2019-03-24 15:24
 */
public class ProductSpuServiceMock implements ProductSpuService {
    @Override
    public CommonResult<ProductSpuDetailBO> getProductSpu(Integer id) {
        return null;
    }

    @Override
    public CommonResult<ProductSpuDetailBO> addProductSpu(Integer adminId, ProductSpuAddDTO productSpuAddDTO) {
        return null;
    }

    @Override
    public CommonResult<Boolean> updateProductSpu(Integer adminId, ProductSpuUpdateDTO productSpuUpdateDTO) {
        return null;
    }

    @Override
    public CommonResult<Boolean> updateProductSpuSort(Integer adminId, Integer spuId, Integer sort) {
        return null;
    }

    @Override
    public CommonResult<ProductSpuPageBO> getProductSpuPage(ProductSpuPageDTO productSpuPageDTO) {
        return null;
    }

    @Override
    public CommonResult<List<ProductSpuBO>> getProductSpuList(Collection<Integer> ids) {
        return null;
    }
}
