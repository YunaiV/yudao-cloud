package cn.iocoder.mall.product.dao;

import cn.iocoder.mall.product.dataobject.ProductSkuDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSkuMapper {

    ProductSkuDO selectById(Integer id);

    void insertList(@Param("productSkuDOs") List<ProductSkuDO> productSkuDOs);

}