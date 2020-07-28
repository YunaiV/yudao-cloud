package cn.iocoder.mall.productservice.dal.mysql.mapper.attr;

import cn.iocoder.mall.productservice.dal.mysql.dataobject.attr.ProductAttrValueDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductAttrValueMapper extends BaseMapper<ProductAttrValueDO> {

    default ProductAttrValueDO selectByAttrKeyIdAndName(Integer attrKeyId, String name) {
        return selectOne(new QueryWrapper<ProductAttrValueDO>().eq("attrKeyId", attrKeyId).eq("name", name));
    }

}
