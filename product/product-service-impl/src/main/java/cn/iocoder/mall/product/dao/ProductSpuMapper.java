package cn.iocoder.mall.product.dao;

import cn.iocoder.mall.product.dataobject.ProductSpuDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSpuMapper {

    ProductSpuDO selectById(Integer id);

    void insert(ProductSpuDO productSpuDO);

    void update(ProductSpuDO productSpuDO);

    List<ProductSpuDO> selectListByNameLikeOrderBySortAsc(@Param("name") String name,
                                                          @Param("offset") Integer offset,
                                                          @Param("limit") Integer limit);

    Integer selectCountByNameLike(@Param("name") String name);

}