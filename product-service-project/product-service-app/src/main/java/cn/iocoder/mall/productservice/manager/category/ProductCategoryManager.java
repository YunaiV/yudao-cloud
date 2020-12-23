package cn.iocoder.mall.productservice.manager.category;

import cn.iocoder.mall.productservice.convert.category.ProductCategoryConvert;
import cn.iocoder.mall.productservice.rpc.category.dto.ProductCategoryCreateReqDTO;
import cn.iocoder.mall.productservice.rpc.category.dto.ProductCategoryListQueryReqDTO;
import cn.iocoder.mall.productservice.rpc.category.dto.ProductCategoryRespDTO;
import cn.iocoder.mall.productservice.rpc.category.dto.ProductCategoryUpdateReqDTO;
import cn.iocoder.mall.productservice.service.category.ProductCategoryService;
import cn.iocoder.mall.productservice.service.category.bo.ProductCategoryBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
* 商品分类 Manager
*/
@Service
public class ProductCategoryManager {

    @Autowired
    private ProductCategoryService productCategoryService;

    /**
    * 创建商品分类
    *
    * @param createDTO 创建商品分类 DTO
    * @return 商品分类
    */
    public Integer createProductCategory(ProductCategoryCreateReqDTO createDTO) {
        ProductCategoryBO productCategoryBO = productCategoryService.createProductCategory(ProductCategoryConvert.INSTANCE.convert(createDTO));
        return productCategoryBO.getId();
    }

    /**
    * 更新商品分类
    *
    * @param updateDTO 更新商品分类 DTO
    */
    public void updateProductCategory(ProductCategoryUpdateReqDTO updateDTO) {
        productCategoryService.updateProductCategory(ProductCategoryConvert.INSTANCE.convert(updateDTO));
    }

    /**
    * 删除商品分类
    *
    * @param productCategoryId 商品分类编号
    */
    public void deleteProductCategory(Integer productCategoryId) {
        productCategoryService.deleteProductCategory(productCategoryId);
    }

    /**
    * 获得商品分类
    *
    * @param productCategoryId 商品分类编号
    * @return 商品分类
    */
    public ProductCategoryRespDTO getProductCategory(Integer productCategoryId) {
        ProductCategoryBO productCategoryBO = productCategoryService.getProductCategory(productCategoryId);
        return ProductCategoryConvert.INSTANCE.convert(productCategoryBO);
    }

    /**
    * 获得商品分类列表
    *
    * @param productCategoryIds 商品分类编号列表
    * @return 商品分类列表
    */
    public List<ProductCategoryRespDTO> listProductCategories(Collection<Integer> productCategoryIds) {
        List<ProductCategoryBO> productCategoryBOs = productCategoryService.listProductCategories(productCategoryIds);
        return ProductCategoryConvert.INSTANCE.convertList02(productCategoryBOs);
    }

    /**
     * 获得商品分类全列表
     *
     * @return 商品分类全列表
     */
    public List<ProductCategoryRespDTO> listProductCategories(ProductCategoryListQueryReqDTO listQueryReqDTO) {
        List<ProductCategoryBO> productCategoryBOs = productCategoryService.listProductCategories(
                ProductCategoryConvert.INSTANCE.convert(listQueryReqDTO));
        return ProductCategoryConvert.INSTANCE.convertList02(productCategoryBOs);
    }

}
