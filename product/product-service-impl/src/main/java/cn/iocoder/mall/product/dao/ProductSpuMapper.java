package cn.iocoder.mall.product.dao;

import cn.iocoder.mall.product.dataobject.ProductSpuDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProductSpuMapper {

    /**
     * 获得大于 id 的商品编号数组
     *
     * @param id 商品编号
     * @param limit 数量
     * @return 商品编号数组
     */
    List<Integer> selectIdListByIdGt(@Param("id") Integer id,
                                     @Param("limit") Integer limit);

}
