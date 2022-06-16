package cn.iocoder.mall.productservice.dal.mysql.mapper.category;

import cn.iocoder.mall.mybatis.core.query.QueryWrapperX;
import cn.iocoder.mall.productservice.dal.mysql.dataobject.category.ProductCategoryDO;
import cn.iocoder.mall.productservice.service.category.bo.ProductCategoryListQueryBO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCategoryMapper extends BaseMapper<ProductCategoryDO> {

    default Integer selectCountByPid(Integer pid) {
        return selectCount(new QueryWrapper<ProductCategoryDO>().eq("pid", pid));
    }

    default List<ProductCategoryDO> selectList(ProductCategoryListQueryBO listQueryBO) {
        return selectList(new QueryWrapperX<ProductCategoryDO>().eqIfPresent("pid", listQueryBO.getPid())
            .eqIfPresent("status", listQueryBO.getStatus()));
    }

}
