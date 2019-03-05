package cn.iocoder.mall.product.dao;

import cn.iocoder.mall.product.dataobject.ProductSpuDO;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSpuMapper {

    ProductSpuDO selectById(Integer id);

    void insert(ProductSpuDO productSpuDO);

    void update(ProductSpuDO productSpuDO);

}