package cn.iocoder.mall.product.api;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.product.api.bo.UserProductSpuCollectionsBO;
import cn.iocoder.mall.product.api.bo.UserProductSpuCollectionsPageBO;
import cn.iocoder.mall.product.api.dto.UserProductSpuCollectionsAddDTO;
import cn.iocoder.mall.product.api.dto.UserProductSpuCollectionsPageDTO;
import cn.iocoder.mall.product.api.dto.UserProductSpuCollectionsUpdateDTO;

/**
 * UserProductSpuCollectionsService
 * @author xiaofeng
 * @date 2019/07/01 20:27
 * @version 1.0
 */
public interface UserProductSpuCollectionsService {

    /**
     * 添加商品收藏
     * @return
     */
    int addUserSkuCollections(UserProductSpuCollectionsAddDTO userProductSpuCollectionsAddDTO);

    /**
     * 获取用户商品收藏
     * @param userId 用户ID
     * @param spuId 商品ID
     * @return
     */
    UserProductSpuCollectionsBO getUserSpuCollectionsByUserIdAndSpuId(Integer userId, Integer spuId);

    /**
     * 取消商品收藏
     * @param userProductSpuCollectionsUpdateDTO
     * @return
     */
    int updateUserProductSpuCollections(UserProductSpuCollectionsUpdateDTO userProductSpuCollectionsUpdateDTO);

    /**
     *  获取用户收藏列表数据
     * @param userProductSpuCollectionsPageDTO
     * @return
     */
    CommonResult<UserProductSpuCollectionsPageBO> getUserProductSpuCollectionsPage(
            UserProductSpuCollectionsPageDTO userProductSpuCollectionsPageDTO);

    /**
     * 删除收藏数据
     * @param userId
     * @param spuId
     * @return
     */
    CommonResult<Boolean> deleteUserProductSpuCollections(Integer userId, Integer spuId);


    /**
     * 检验用户商品是否收藏
     * @param spuId
     * @param userId
     * @return
     */
    CommonResult<Boolean> hasUserSpuFavorite(Integer spuId, Integer userId);

}
