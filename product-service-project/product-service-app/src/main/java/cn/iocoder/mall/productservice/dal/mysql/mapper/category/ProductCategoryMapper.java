package cn.iocoder.mall.productservice.dal.mysql.mapper.category;

import cn.iocoder.mall.productservice.dal.mysql.dataobject.category.ProductCategoryDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryMapper extends BaseMapper<ProductCategoryDO> {

    default Integer selectCountByPid(Integer pid) {
        return selectCount(new QueryWrapper<ProductCategoryDO>().eq("pid", pid));
    }

}
