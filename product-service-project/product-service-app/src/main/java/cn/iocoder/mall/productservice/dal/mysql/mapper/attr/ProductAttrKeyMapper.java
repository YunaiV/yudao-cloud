package cn.iocoder.mall.productservice.dal.mysql.mapper.attr;

import cn.iocoder.mall.mybatis.core.query.QueryWrapperX;
import cn.iocoder.mall.productservice.dal.mysql.dataobject.attr.ProductAttrKeyDO;
import cn.iocoder.mall.productservice.service.attr.bo.ProductAttrKeyPageBO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductAttrKeyMapper extends BaseMapper<ProductAttrKeyDO> {

    default IPage<ProductAttrKeyDO> selectPage(ProductAttrKeyPageBO pageBO) {
        return selectPage(new Page<>(pageBO.getPageNo(), pageBO.getPageSize()),
                new QueryWrapperX<ProductAttrKeyDO>().likeIfPresent("name", pageBO.getName())
                    .eqIfPresent("status", pageBO.getStatus()));
    }

    default ProductAttrKeyDO selectByName(String name) {
        return selectOne(new QueryWrapper<ProductAttrKeyDO>().eq("name", name));
    }

}
