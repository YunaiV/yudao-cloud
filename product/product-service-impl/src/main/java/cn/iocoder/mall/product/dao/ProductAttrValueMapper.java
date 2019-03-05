package cn.iocoder.mall.product.dao;

import cn.iocoder.mall.product.dataobject.ProductAttrValueDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProductAttrValueMapper {

    ProductAttrValueDO selectById(@Param("id") Integer id);

    List<ProductAttrValueDO> selectListByIds(@Param("ids") Collection<Integer> ids);

}