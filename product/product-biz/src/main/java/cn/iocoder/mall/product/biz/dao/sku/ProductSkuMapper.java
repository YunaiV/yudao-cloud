package cn.iocoder.mall.product.biz.dao.sku;

import cn.iocoder.mall.product.biz.dataobject.spu.ProductSkuDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSkuMapper extends BaseMapper<ProductSkuDO> {

    default List<ProductSkuDO> selectListBySpuIdAndStatus(Integer spuId, Integer status) {
        return selectList(Wrappers.<ProductSkuDO>query().lambda()
                .eq(ProductSkuDO::getSpuId, spuId)
                .eq(ProductSkuDO::getStatus, status)
                .eq(ProductSkuDO::getDeleted, false));
    }

    void insertList(@Param("productSkuDOs") List<ProductSkuDO> productSkuDOs);
}
