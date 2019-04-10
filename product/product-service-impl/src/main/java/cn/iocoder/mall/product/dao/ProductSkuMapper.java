package cn.iocoder.mall.product.dao;

import cn.iocoder.mall.product.dataobject.ProductSkuDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProductSkuMapper {

    ProductSkuDO selectById(Integer id);

    List<ProductSkuDO> selectByIds(@Param("ids") Collection<Integer> ids);

    List<ProductSkuDO> selectListBySpuIdAndStatus(@Param("spuId") Integer spuId,
                                                  @Param("status") Integer status);

    void insertList(@Param("productSkuDOs") List<ProductSkuDO> productSkuDOs);

    int update(ProductSkuDO productSkuDO);

    int updateToDeleted(@Param("ids") List<Integer> ids);

}
