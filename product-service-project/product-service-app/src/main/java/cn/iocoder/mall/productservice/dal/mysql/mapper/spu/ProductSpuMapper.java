package cn.iocoder.mall.productservice.dal.mysql.mapper.spu;

import cn.iocoder.mall.mybatis.core.query.QueryWrapperX;
import cn.iocoder.mall.productservice.dal.mysql.dataobject.spu.ProductSpuDO;
import cn.iocoder.mall.productservice.service.spu.bo.ProductSpuPageBO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSpuMapper extends BaseMapper<ProductSpuDO> {

    default IPage<ProductSpuDO> selectPage(ProductSpuPageBO pageBO) {
        QueryWrapperX<ProductSpuDO> query = new QueryWrapperX<ProductSpuDO>().likeIfPresent("name", pageBO.getName())
                .eqIfPresent("cid", pageBO.getCid()).eqIfPresent("visible", pageBO.getVisible());
        // 库存过滤
        if (pageBO.getHasQuantity() != null) {
            if (pageBO.getHasQuantity()) {
                query.gt("quantity", 0);
            } else {
                query.eq("quantity", 0);
            }
        }
        return selectPage(new Page<>(pageBO.getPageNo(), pageBO.getPageSize()), query);
    }

    default List<ProductSpuDO> selectListByIdGt(Integer id, Integer limit) {
        return selectList(new QueryWrapperX<ProductSpuDO>().gtIfPresent("id", id)
                .orderByAsc("id").last("LIMIT " + limit));
    }

}
