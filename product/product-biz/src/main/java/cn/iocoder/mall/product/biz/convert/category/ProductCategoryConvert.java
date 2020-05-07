package cn.iocoder.mall.product.biz.convert.category;

import cn.iocoder.mall.product.biz.bo.category.ProductCategoryAddBO;
import cn.iocoder.mall.product.biz.bo.category.ProductCategoryAllListBO;
import cn.iocoder.mall.product.biz.dataobject.product.ProductCategoryDO;
import cn.iocoder.mall.product.biz.dto.category.ProductCategoryAddDTO;
import cn.iocoder.mall.product.biz.dto.category.ProductCategoryUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import java.util.List;

/**
 * @Author: jiangweifan
 * @Date: 2020/5/6
 * @Description: 商品分类 - 服务层数据转换
 */
@Mapper
public interface ProductCategoryConvert {

    ProductCategoryConvert INSTANCE = Mappers.getMapper(ProductCategoryConvert.class);

    /**
     * 商品分类列表 - DO转换BO 单实体
     * @param category
     * @return
     */
    ProductCategoryAllListBO convertToAllListBO(ProductCategoryDO category);


    /**
     * 商品分类列表 - DO转换BO {@link #convertToAllListBO(ProductCategoryDO)}
     * @param category
     * @return
     */
    List<ProductCategoryAllListBO> convertToAllListBO(List<ProductCategoryDO> category);

    /**
     * 新增商品分类 - DTO转换DO
     * @param productCategoryAddDTO
     * @return
     */
    ProductCategoryDO convertToDO(ProductCategoryAddDTO productCategoryAddDTO);

    /**
     * 新增商品分类 - DO转换BO
     * @param category
     * @return
     */
    ProductCategoryAddBO convertToAddBO(ProductCategoryDO category);

    /**
     * 更新商品分类 - DTO转换DO
     * @param productCategoryUpdateDTO
     * @return
     */
    ProductCategoryDO convertToDO(ProductCategoryUpdateDTO productCategoryUpdateDTO);

}