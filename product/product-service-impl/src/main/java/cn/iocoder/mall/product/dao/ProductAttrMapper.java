package cn.iocoder.mall.product.dao;

import cn.iocoder.mall.product.dataobject.ProductAttrDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProductAttrMapper {

    ProductAttrDO selectById(@Param("id") Integer id);

    List<ProductAttrDO> selectListByIds(@Param("ids") Collection<Integer> ids);

}