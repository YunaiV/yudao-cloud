package cn.iocoder.mall.productservice.dal.mysql.mapper.brand;

import cn.iocoder.mall.mybatis.core.query.QueryWrapperX;
import cn.iocoder.mall.productservice.dal.mysql.dataobject.brand.ProductBrandDO;
import cn.iocoder.mall.productservice.service.brand.bo.ProductBrandPageBO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductBrandMapper extends BaseMapper<ProductBrandDO> {

    default IPage<ProductBrandDO> selectPage(ProductBrandPageBO pageBO) {
        return selectPage(new Page<>(pageBO.getPageNo(), pageBO.getPageSize()),
            new QueryWrapperX<ProductBrandDO>().likeIfPresent("name", pageBO.getName())
                .eqIfPresent("status", pageBO.getStatus()));
    }

    default ProductBrandDO selectByName(String name) {
        return selectOne(new QueryWrapper<ProductBrandDO>().eq("name", name));
    }

}
