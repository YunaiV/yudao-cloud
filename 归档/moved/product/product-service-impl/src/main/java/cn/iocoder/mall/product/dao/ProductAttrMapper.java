package cn.iocoder.mall.product.dao;

import cn.iocoder.mall.product.dataobject.ProductAttrDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProductAttrMapper {

    ProductAttrDO selectById(@Param("id") Integer id);

    ProductAttrDO selectByName(@Param("name") String name);

    List<ProductAttrDO> selectListByIds(@Param("ids") Collection<Integer> ids);

    List<ProductAttrDO> selectListByStatus(@Param("status") Integer status);

    List<ProductAttrDO> selectListByNameLike(@Param("name") String name,
                                             @Param("offset") Integer offset,
                                             @Param("limit") Integer limit);

    Integer selectCountByNameLike(@Param("name") String name);

    void insert(ProductAttrDO productAttrDO);

    void update(ProductAttrDO productAttrDO);

}