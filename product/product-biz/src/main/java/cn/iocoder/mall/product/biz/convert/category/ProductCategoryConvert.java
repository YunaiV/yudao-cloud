package cn.iocoder.mall.product.biz.convert.category;

import cn.iocoder.mall.product.biz.bo.category.ProductCategoryBO;
import cn.iocoder.mall.product.biz.dataobject.category.ProductCategoryDO;
import cn.iocoder.mall.product.biz.dto.category.ProductCategoryAddDTO;
import cn.iocoder.mall.product.biz.dto.category.ProductCategoryUpdateDTO;
import cn.iocoder.mall.product.biz.dto.category.ProductCategoryUpdateStatusDTO;
import org.mapstruct.Mapper;
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
     * 商品分类统一DO转BO
     * @param category
     * @return
     */
    ProductCategoryBO convertToBO(ProductCategoryDO category);


    /**
     * 商品分类列表 - DO转换BO {@link #convertToBO(ProductCategoryDO)}
     * @param category
     * @return
     */
    List<ProductCategoryBO> convertToAllListBO(List<ProductCategoryDO> category);

    /**
     * 新增商品分类 - DTO转换DO
     * @param productCategoryAddDTO
     * @return
     */
    ProductCategoryDO convertToDO(ProductCategoryAddDTO productCategoryAddDTO);

    /**
     * 更新商品分类 - DTO转换DO
     * @param productCategoryUpdateDTO
     * @return
     */
    ProductCategoryDO convertToDO(ProductCategoryUpdateDTO productCategoryUpdateDTO);

    /**
     * 更新商品分类状态 - DTO转换DO
     * @param productCategoryUpdateStatusDTO
     * @return
     */
    ProductCategoryDO convertToDO(ProductCategoryUpdateStatusDTO productCategoryUpdateStatusDTO);

}