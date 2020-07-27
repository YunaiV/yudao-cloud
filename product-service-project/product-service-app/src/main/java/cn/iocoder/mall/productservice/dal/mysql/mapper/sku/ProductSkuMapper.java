package cn.iocoder.mall.productservice.dal.mysql.mapper.sku;

import cn.iocoder.mall.mybatis.core.query.QueryWrapperX;
import cn.iocoder.mall.productservice.dal.mysql.dataobject.spu.ProductSkuDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSkuMapper extends BaseMapper<ProductSkuDO> {

    default List<ProductSkuDO> selectListBySpuIdAndStatus(Integer spuId, Integer status) {
        return selectList(new QueryWrapperX<ProductSkuDO>().eq("spu_id", spuId)
                .eq("status", status));
    }

    void insertList(@Param("productSkuDOs") List<ProductSkuDO> productSkuDOs);

}
