package cn.iocoder.mall.product.api;

/**
 * 商品收藏
 * @author xiaofeng
 * @date 2019/07/01 23:13
 * @version 1.0
 */
public interface ProductSpuCollectionService {

    /**
     * 商品收藏
     * @param spuId
     * @param hasCollectionType 1 商品收藏 2 取消收藏
     * @param userId
     * @return
     */
    boolean productSpuCollection(Integer spuId,Integer hasCollectionType,Integer userId);
}
