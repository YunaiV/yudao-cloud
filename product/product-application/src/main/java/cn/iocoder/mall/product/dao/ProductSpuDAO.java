package cn.iocoder.mall.product.dao;

import cn.iocoder.mall.product.dataobject.ProductSpuDO;
import org.springframework.stereotype.Repository;

@Repository
public class ProductSpuDAO {

    public ProductSpuDO selectById(Integer id) {
        ProductSpuDO spu = new ProductSpuDO();
        spu.setId(id);
        return spu;
    }

}