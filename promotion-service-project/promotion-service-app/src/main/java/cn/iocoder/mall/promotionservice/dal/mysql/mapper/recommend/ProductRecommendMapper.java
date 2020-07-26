package cn.iocoder.mall.promotionservice.dal.mysql.mapper.recommend;

import cn.iocoder.mall.promotionservice.dal.mysql.dataobject.recommend.ProductRecommendDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRecommendMapper {

    ProductRecommendDO selectById(@Param("id") Integer id);

    ProductRecommendDO selectByProductSpuIdAndType(@Param("productSpuId") Integer productSpuId,
                                                   @Param("type") Integer type);

    List<ProductRecommendDO> selectListByTypeAndStatus(@Param("type") Integer type,
                                                       @Param("status") Integer status);

    List<ProductRecommendDO> selectPageByType(@Param("type") Integer type,
                                              @Param("offset") Integer offset,
                                              @Param("limit") Integer limit);

    Integer selectCountByType(@Param("type") Integer type);

    void insert(ProductRecommendDO bannerDO);

    int update(ProductRecommendDO bannerDO);

}