package cn.iocoder.mall.user.biz.dao;

import cn.iocoder.mall.user.biz.dataobject.UserProductSpuCollectionsDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 用户_商品_收藏记录表
 *
 * @author xiaofeng
 * @date 2019-07-01 20:23:30
 */
public interface UserProductSpuCollectionsMapper extends BaseMapper<UserProductSpuCollectionsDO> {

    /**
     * 根据用户id 和 spuId 查找用户商品收藏
     * @param userId
     * @param spuId
     * @return
     */
    default UserProductSpuCollectionsDO getUserSpuCollectionsByUserIdAndSpuId(final Integer userId,
            final Integer spuId) {
        QueryWrapper<UserProductSpuCollectionsDO> query = new QueryWrapper<UserProductSpuCollectionsDO>()
                .eq("user_id", userId).eq("spu_id", spuId);
        return selectOne(query);
    }

}
