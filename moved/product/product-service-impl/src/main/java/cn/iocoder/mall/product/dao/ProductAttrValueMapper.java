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

    List<ProductAttrValueDO> selectListByStatus(@Param("status") Integer status);

    List<ProductAttrValueDO> selectListByAttrIds(@Param("attrIds") Collection<Integer> attrIds);

    ProductAttrValueDO selectByAttrIdAndName(@Param("attrId") Integer attrId,
                                             @Param("name") String name);


    void insert(ProductAttrValueDO productAttrValueDO);

    void update(ProductAttrValueDO productAttrValueDO);

}