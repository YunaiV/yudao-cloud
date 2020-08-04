package cn.iocoder.mall.productservice.service.category;

import cn.iocoder.common.framework.exception.util.ServiceExceptionUtil;
import cn.iocoder.mall.productservice.convert.category.ProductCategoryConvert;
import cn.iocoder.mall.productservice.dal.mysql.dataobject.category.ProductCategoryDO;
import cn.iocoder.mall.productservice.dal.mysql.mapper.category.ProductCategoryMapper;
import cn.iocoder.mall.productservice.enums.category.ProductCategoryIdEnum;
import cn.iocoder.mall.productservice.service.category.bo.ProductCategoryBO;
import cn.iocoder.mall.productservice.service.category.bo.ProductCategoryCreateBO;
import cn.iocoder.mall.productservice.service.category.bo.ProductCategoryListQueryBO;
import cn.iocoder.mall.productservice.service.category.bo.ProductCategoryUpdateBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

import static cn.iocoder.mall.productservice.enums.ProductErrorCodeConstants.*;

/**
* 商品分类 Service
*/
@Service
@Validated
public class ProductCategoryService {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    /**
    * 创建商品分类
    *
    * @param createBO 创建商品分类 BO
    * @return 商品分类
    */
    public ProductCategoryBO createProductCategory(@Valid ProductCategoryCreateBO createBO) {
        // 校验父分类
        validParent(createBO.getPid());
        // 插入到数据库
        ProductCategoryDO productCategoryDO = ProductCategoryConvert.INSTANCE.convert(createBO);
        productCategoryMapper.insert(productCategoryDO);
        // 返回
        return ProductCategoryConvert.INSTANCE.convert(productCategoryDO);
    }

    /**
    * 更新商品分类
    *
    * @param updateBO 更新商品分类 BO
    */
    public void updateProductCategory(@Valid ProductCategoryUpdateBO updateBO) {
        // 校验父分类
        validParent(updateBO.getPid());
        // 校验不能设置自己为父分类
        if (updateBO.getId().equals(updateBO.getPid())) {
            throw ServiceExceptionUtil.exception(PRODUCT_CATEGORY_PARENT_NOT_SELF);
        }
        // 校验更新的商品分类是否存在
        if (productCategoryMapper.selectById(updateBO.getId()) == null) {
            throw ServiceExceptionUtil.exception(PRODUCT_CATEGORY_NOT_EXISTS);
        }
        // 更新到数据库
        ProductCategoryDO updateObject = ProductCategoryConvert.INSTANCE.convert(updateBO);
        productCategoryMapper.updateById(updateObject);
    }

    /**
    * 删除商品分类
    *
    * @param productCategoryId 商品分类编号
    */
    public void deleteProductCategory(Integer productCategoryId) {
        // 校验删除的商品分类是否存在
        if (productCategoryMapper.selectById(productCategoryId) == null) {
            throw ServiceExceptionUtil.exception(PRODUCT_CATEGORY_NOT_EXISTS);
        }
        // 只有不存在子分类才可以删除
        Integer childCount = productCategoryMapper.selectCountByPid(productCategoryId);
        if (childCount > 0) {
            throw ServiceExceptionUtil.exception(PRODUCT_CATEGORY_DELETE_ONLY_NO_CHILD);
        }
        // TODO 芋艿 补充只有不存在商品才可以删除
        // 标记删除
        productCategoryMapper.deleteById(productCategoryId);
    }

    /**
    * 获得商品分类
    *
    * @param productCategoryId 商品分类编号
    * @return 商品分类
    */
    public ProductCategoryBO getProductCategory(Integer productCategoryId) {
        ProductCategoryDO productCategoryDO = productCategoryMapper.selectById(productCategoryId);
        return ProductCategoryConvert.INSTANCE.convert(productCategoryDO);
    }

    /**
    * 获得商品分类列表
    *
    * @param productCategoryIds 商品分类编号列表
    * @return 商品分类列表
    */
    public List<ProductCategoryBO> listProductCategories(Collection<Integer> productCategoryIds) {
        List<ProductCategoryDO> productCategoryDOs = productCategoryMapper.selectBatchIds(productCategoryIds);
        return ProductCategoryConvert.INSTANCE.convertList(productCategoryDOs);
    }

    /**
     * 获得商品分类全列表
     *
     * @return 商品分类全列表
     */
    public List<ProductCategoryBO> listProductCategories(ProductCategoryListQueryBO listQueryBO) {
        List<ProductCategoryDO> resourceDOs = productCategoryMapper.selectList(listQueryBO);
        return ProductCategoryConvert.INSTANCE.convertList(resourceDOs);
    }

    private void validParent(Integer pid) {
        if (!ProductCategoryIdEnum.ROOT.getId().equals(pid)) {
            ProductCategoryDO parentCategory = productCategoryMapper.selectById(pid);
            // 校验父分类是否存在
            if (parentCategory == null) {
                throw ServiceExceptionUtil.exception(PRODUCT_CATEGORY_PARENT_NOT_EXISTS);
            }
            // 父分类必须是一级分类
            if (!ProductCategoryIdEnum.ROOT.getId().equals(parentCategory.getPid())) {
                throw ServiceExceptionUtil.exception((PRODUCT_CATEGORY_PARENT_CAN_NOT_BE_LEVEL2));
            }
        }
    }

}
