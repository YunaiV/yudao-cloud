package cn.iocoder.mall.product.dao;

import cn.iocoder.mall.product.dataobject.ProductBrandDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductBrandMapper {

    /**
     * 根据 id 获取数据
     * @param id
     * @return
     */
    ProductBrandDO selectById(@Param("id") Integer id);

    /**
     * 根据 name 获取数据
     * @param name
     * @return
     */
    ProductBrandDO selectByName(@Param("name") String name);


    /**
     * 分页查询
     * @param name 名称
     * @param description 描述
     * @param status 状态 1开启 2禁用
     * @param offset 偏移量
     * @param limit 数量
     * @return
     */
    List<ProductBrandDO> selectListByParams(@Param("name") String name,
                                              @Param("description") String description,
                                              @Param("status") Integer status,
                                             @Param("offset") Integer offset,
                                             @Param("limit") Integer limit);

    /**
     * 分页数量统计
     * @param name 名称
     * @param description 描述
     * @param status 状态 1开启 2禁用
     * @return
     */
    Integer selectListCountByParams(@Param("name") String name,
                                  @Param("description") String description,
                                  @Param("status") Integer status);

    /**
     * 新增数据
     * @param productBrandDO
     */
    void insert(ProductBrandDO productBrandDO);

    /**
     * 更新数据
     * @param productBrandDO
     */
    void update(ProductBrandDO productBrandDO);

}