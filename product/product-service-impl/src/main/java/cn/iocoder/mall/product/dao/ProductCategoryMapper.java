package cn.iocoder.mall.product.dao;

import cn.iocoder.mall.product.dataobject.ProductCategoryDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProductCategoryMapper {

    List<ProductCategoryDO> selectListByPidAndStatusOrderBySort(@Param("pid") Integer pid,
                                                                @Param("status") Integer status);

    List<ProductCategoryDO> selectList();

    ProductCategoryDO selectById(@Param("id") Integer id);

    List<ProductCategoryDO> selectByIds(@Param("ids") Collection<Integer> ids);

    void insert(ProductCategoryDO productCategoryDO);

    int update(ProductCategoryDO productCategoryDO);

}
