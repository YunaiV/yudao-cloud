package cn.iocoder.mall.promotionservice.dal.mysql.mapper.recommend;

import cn.iocoder.mall.mybatis.core.query.QueryWrapperX;
import cn.iocoder.mall.promotion.api.rpc.recommend.dto.ProductRecommendListReqDTO;
import cn.iocoder.mall.promotion.api.rpc.recommend.dto.ProductRecommendPageReqDTO;
import cn.iocoder.mall.promotionservice.dal.mysql.dataobject.recommend.ProductRecommendDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRecommendMapper extends BaseMapper<ProductRecommendDO> {

    default ProductRecommendDO selectByProductSpuIdAndType(Integer productSpuId, Integer type) {
        return selectOne(new QueryWrapper<ProductRecommendDO>().eq("product_spu_id", productSpuId)
                .eq("type", type));
    }

    default List<ProductRecommendDO> selectList(ProductRecommendListReqDTO listReqDTO) {
        return selectList(new QueryWrapperX<ProductRecommendDO>().eqIfPresent("type", listReqDTO.getType())
                .eqIfPresent("status", listReqDTO.getStatus()));
    }

    default IPage<ProductRecommendDO> selectPage(ProductRecommendPageReqDTO pageReqDTO) {
        return selectPage(new Page<>(pageReqDTO.getPageNo(), pageReqDTO.getPageSize()),
                new QueryWrapperX<ProductRecommendDO>().eqIfPresent("type", pageReqDTO.getType()));
    }

}
