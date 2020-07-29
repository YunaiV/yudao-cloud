package cn.iocoder.mall.productservice.dal.mysql.mapper.attr;

import cn.iocoder.mall.mybatis.core.query.QueryWrapperX;
import cn.iocoder.mall.productservice.dal.mysql.dataobject.attr.ProductAttrValueDO;
import cn.iocoder.mall.productservice.service.attr.bo.ProductAttrValueListQueryBO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductAttrValueMapper extends BaseMapper<ProductAttrValueDO> {

    default ProductAttrValueDO selectByAttrKeyIdAndName(Integer attrKeyId, String name) {
        return selectOne(new QueryWrapper<ProductAttrValueDO>().eq("attr_key_id", attrKeyId).eq("name", name));
    }

    default List<ProductAttrValueDO> selectList(ProductAttrValueListQueryBO queryBO) {
        return selectList(new QueryWrapperX<ProductAttrValueDO>().inIfPresent("id", queryBO.getProductAttrValueIds())
                .eqIfPresent("attr_key_id", queryBO.getProductAttrKeyId()));
    }

}
