package cn.iocoder.mall.product.dao;

import cn.iocoder.mall.product.dataobject.ProductSpuDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProductSpuMapper {

    ProductSpuDO selectById(Integer id);

    List<ProductSpuDO> selectByIds(@Param("ids") Collection<Integer> ids);

    /**
     * 获得大于 id 的商品编号数组
     *
     * @param id 商品编号
     * @param limit 数量
     * @return 商品编号数组
     */
    List<Integer> selectIdListByIdGt(@Param("id") Integer id,
                                     @Param("limit") Integer limit);

    void insert(ProductSpuDO productSpuDO);

    void update(ProductSpuDO productSpuDO);

    // TODO 芋艿，需要捉摸下，怎么优化下。参数有点多
    List<ProductSpuDO> selectListByNameLikeOrderBySortAsc(@Param("name") String name,
                                                          @Param("cid") Integer cid,
                                                          @Param("visible") Boolean visible,
                                                          @Param("offset") Integer offset,
                                                          @Param("limit") Integer limit);

    Integer selectCountByNameLike(@Param("name") String name,
                                  @Param("cid") Integer cid,
                                  @Param("visible") Boolean visible);

}
