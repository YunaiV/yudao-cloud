package cn.iocoder.mall.product.api;

import cn.iocoder.mall.product.api.bo.*;
import cn.iocoder.mall.product.api.dto.ProductSpuAddDTO;
import cn.iocoder.mall.product.api.dto.ProductSpuPageDTO;
import cn.iocoder.mall.product.api.dto.ProductSpuSearchListDTO;
import cn.iocoder.mall.product.api.dto.ProductSpuUpdateDTO;

import java.util.Collection;
import java.util.List;

public interface ProductSpuService {

    ProductSpuDetailBO getProductSpuDetail(Integer id);

    List<ProductSpuBO> getProductSpuSearchList(ProductSpuSearchListDTO productSpuSearchListDTO);

    List<ProductSpuBO> getProductSpuList(Collection<Integer> ids);

    ProductSkuBO getProductSku(Integer id);

    List<ProductSkuDetailBO> getProductSkuDetailList(Collection<Integer> ids);

    Boolean updateProductSpuSort(Integer adminId, Integer spuId, Integer sort);

}
