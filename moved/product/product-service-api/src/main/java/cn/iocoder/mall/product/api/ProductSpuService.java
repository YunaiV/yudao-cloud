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

    /**
     * 增量获得商品列表，按照 lastId 递增获得
     *
     * @param lastId 最后查询的编号
     * @param limit 大小
     * @return 商品列表
     */
    List<ProductSpuDetailBO> getProductSpuDetailListForSync(Integer lastId, Integer limit);

    List<ProductSpuBO> getProductSpuSearchList(ProductSpuSearchListDTO productSpuSearchListDTO);

    List<ProductSpuBO> getProductSpuList(Collection<Integer> ids);

    ProductSkuBO getProductSku(Integer id);

    List<ProductSkuDetailBO> getProductSkuDetailList(Collection<Integer> ids);

    Boolean updateProductSpuSort(Integer adminId, Integer spuId, Integer sort);

}
