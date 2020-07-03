package cn.iocoder.mall.product.dao;

import cn.iocoder.mall.product.dataobject.UserProductSpuCollectionsDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户_商品_收藏记录表
 *
 * @author xiaofeng
 * @date 2019-07-01 20:23:30
 */
@Repository
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


    /**
     * 查询用户收藏列表
     * @param userId
     * @param offset
     * @param limit
     * @return
     */
    List<UserProductSpuCollectionsDO> selectListByUser(@Param("userId") Integer userId, @Param("offset") Integer offset,
            @Param("limit") Integer limit);

    /**
     * 根据用户ID 查找总数
     * @param userId
     * @return
     */
    Integer selectCountByUser(Integer userId);

}
