package cn.iocoder.mall.product.rest.convert.category;

import cn.iocoder.mall.product.biz.bo.category.ProductCategoryAddBO;
import cn.iocoder.mall.product.biz.bo.category.ProductCategoryAllListBO;
import cn.iocoder.mall.product.biz.dto.category.ProductCategoryAddDTO;
import cn.iocoder.mall.product.rest.request.category.AdminsProductCategoryAddRequest;
import cn.iocoder.mall.product.rest.response.category.AdminsProductCategoryAddResponse;
import cn.iocoder.mall.product.rest.response.category.AdminsProductCategoryTreeNodeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @Author: jiangweifan
 * @Date: 2020/5/6
 * @Description: 商品分类 - API层数据转换
 */
@Mapper
public interface ProductCategoryConvert {

    ProductCategoryConvert INSTANCE = Mappers.getMapper(ProductCategoryConvert.class);

    /**
     * 商品分类列表 - BO转换Response
     * @param productCategoryAllListBO
     * @return
     */
    @Mappings({})
    AdminsProductCategoryTreeNodeResponse convertToTreeNodeResponse(ProductCategoryAllListBO productCategoryAllListBO);


    /**
     * 新增商品分类 - Request转DTO
     * @param adminsProductCategoryAddRequest
     * @return
     */
    @Mappings({})
    ProductCategoryAddDTO convertToAddDTO(Integer adminId, AdminsProductCategoryAddRequest adminsProductCategoryAddRequest);

    /**
     * 新增商品分类 - BO转Response
     * @param productCategoryAddBO
     * @return
     */
    @Mappings({})
    AdminsProductCategoryAddResponse convertToAddResponse(ProductCategoryAddBO productCategoryAddBO);
}
