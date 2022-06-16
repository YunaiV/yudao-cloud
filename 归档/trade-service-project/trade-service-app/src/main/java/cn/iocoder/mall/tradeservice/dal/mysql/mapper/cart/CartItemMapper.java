package cn.iocoder.mall.tradeservice.dal.mysql.mapper.cart;

import cn.iocoder.mall.mybatis.core.query.QueryWrapperX;
import cn.iocoder.mall.tradeservice.dal.mysql.dataobject.cart.CartItemDO;
import cn.iocoder.mall.tradeservice.service.cart.bo.CartItemListQueryBO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
public interface CartItemMapper extends BaseMapper<CartItemDO> {

    default CartItemDO selectByUserIdAndSkuId(Integer userId, Integer skuId) {
        return selectOne(new QueryWrapper<CartItemDO>().eq("user_id", userId)
            .eq("sku_id", skuId));
    }

    default List<CartItemDO> selectListByUserIdAndSkuIds(Integer userId, Collection<Integer> skuIds) {
        return selectList(new QueryWrapperX<CartItemDO>().eq("user_id", userId)
                .inIfPresent("sku_id", skuIds));
    }

   default void updateByIds(@Param("ids") Set<Integer> ids, @Param("updateObject") CartItemDO updateObject) {
        // TODO 芋艿：batch update ，在 mybatis plus 做拓展，这里先临时处理
       ids.forEach(id -> updateById(updateObject.setId(id)));
   }

    default Integer selectSumQuantityByUserId(Integer userId) {
        // SQL sum 查询
        List<Map<String, Object>> result = selectMaps(new QueryWrapper<CartItemDO>()
                .select("SUM(quantity) AS sumQuantity")
                .eq("user_id", userId));
        // 获得数量
        return ((BigDecimal) result.get(0).get("sumQuantity")).intValue();
    }

    default List<CartItemDO> selectList(CartItemListQueryBO queryBO) {
        return selectList(new QueryWrapperX<CartItemDO>().eq("user_id", queryBO.getUserId())
            .eqIfPresent("selected", queryBO.getSelected()));
    }

}
