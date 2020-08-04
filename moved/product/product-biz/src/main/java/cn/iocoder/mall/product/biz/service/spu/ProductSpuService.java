package cn.iocoder.mall.product.biz.service.spu;


import cn.iocoder.mall.product.biz.bo.product.ProductSpuDetailBO;

public interface ProductSpuService {
    /**
     * 获取SPU明细
     *
     * @param id spuId
     * @return SPU明细
     */
    ProductSpuDetailBO getProductSpuDetail(Integer id);

}
