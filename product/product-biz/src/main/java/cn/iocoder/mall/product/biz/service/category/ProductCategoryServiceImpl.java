package cn.iocoder.mall.product.biz.service.category;

import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.mall.mybatis.enums.DeletedStatusEnum;
import cn.iocoder.mall.product.biz.bo.category.ProductCategoryBO;
import cn.iocoder.mall.product.biz.convert.category.ProductCategoryConvert;
import cn.iocoder.mall.product.biz.dao.category.ProductCategoryMapper;
import cn.iocoder.mall.product.biz.dataobject.category.ProductCategoryDO;
import cn.iocoder.mall.product.biz.dto.category.ProductCategoryAddDTO;
import cn.iocoder.mall.product.biz.dto.category.ProductCategoryDeleteDTO;
import cn.iocoder.mall.product.biz.dto.category.ProductCategoryUpdateDTO;
import cn.iocoder.mall.product.biz.dto.category.ProductCategoryUpdateStatusDTO;
import cn.iocoder.mall.product.biz.enums.ProductErrorCodeEnum;
import cn.iocoder.mall.product.biz.enums.category.ProductCategoryNodeEnum;
import cn.iocoder.mall.product.biz.enums.category.ProductCategoryStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static cn.iocoder.mall.product.biz.enums.ProductErrorCodeEnum.*;

/**
 * @Author: jiangweifan
 * @Date: 2020/5/6
 * @Description: 商品分类 - 服务实现层
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Override
    public List<ProductCategoryBO> getAllProductCategory() {
        List<ProductCategoryDO> categoryList = productCategoryMapper.selectList(null);
        return ProductCategoryConvert.INSTANCE.convertToAllListBO(categoryList);
    }

    @Override
    public ProductCategoryBO addProductCategory(ProductCategoryAddDTO productCategoryAddDTO) {
        // 校验父分类
        validParent(productCategoryAddDTO.getPid());
        // 保存到数据库
        ProductCategoryDO productCategory = ProductCategoryConvert.INSTANCE.convertToDO(productCategoryAddDTO)
                .setStatus(ProductCategoryStatusEnum.ENABLED.getStatus());
        productCategory.setCreateTime(new Date());
        productCategory.setDeleted(DeletedStatusEnum.DELETED_NO.getValue());
        productCategoryMapper.insert(productCategory);
        // TODO 伟帆 操作日志
        // 返回成功
        return ProductCategoryConvert.INSTANCE.convertToBO(productCategory);
    }

    @Override
    public Boolean updateProductCategory(ProductCategoryUpdateDTO productCategoryUpdateDTO) {
        // 校验当前分类是否存在
        if (productCategoryMapper.selectById(productCategoryUpdateDTO.getId()) == null) {
            throw ServiceExceptionUtil.exception(PRODUCT_CATEGORY_NOT_EXISTS);
        }
        // 校验父分类
        validParent(productCategoryUpdateDTO.getPid());
        // 校验不能设置自己为父分类
        if (productCategoryUpdateDTO.getId().equals(productCategoryUpdateDTO.getPid())) {
            throw ServiceExceptionUtil.exception(PRODUCT_CATEGORY_PARENT_NOT_SELF);
        }
        // 校验父分类是否存在
        if (!ProductCategoryNodeEnum.ROOT.getId().equals(productCategoryUpdateDTO.getPid())
                && productCategoryMapper.selectById(productCategoryUpdateDTO.getPid()) == null) {
            throw ServiceExceptionUtil.exception(PRODUCT_CATEGORY_PARENT_NOT_EXISTS);
        }
        // 更新到数据库
        ProductCategoryDO updateProductCategory = ProductCategoryConvert.INSTANCE.convertToDO(productCategoryUpdateDTO);
        productCategoryMapper.updateById(updateProductCategory);
        // TODO 伟帆 操作日志
        return true;
    }

    @Override
    public Boolean updateProductCategoryStatus(ProductCategoryUpdateStatusDTO productCategoryUpdateStatusDTO) {
        // 校验商品分类是否存在
        ProductCategoryDO productCategoryDO = productCategoryMapper.selectById(productCategoryUpdateStatusDTO.getId());
        if (productCategoryDO == null) {
            throw ServiceExceptionUtil.exception(PRODUCT_CATEGORY_NOT_EXISTS);
        }
        // 判断更新状态是否存在
        if (Arrays.stream(ProductCategoryStatusEnum.ARRAYS).noneMatch(status -> status == productCategoryUpdateStatusDTO.getStatus())) {
            throw ServiceExceptionUtil.exception(PRODUCT_CATEGORY_STATUS_NOT_EXISTS);
        }
        // 如果状态相同，则返回错误
        if (productCategoryDO.getStatus().equals(productCategoryUpdateStatusDTO.getStatus())) {
            throw ServiceExceptionUtil.exception(PRODUCT_CATEGORY_STATUS_EQUALS);
        }
        // 更新商品分类状态
        ProductCategoryDO updateCategoryStatus = ProductCategoryConvert.INSTANCE.convertToDO(productCategoryUpdateStatusDTO);
        productCategoryMapper.updateById(updateCategoryStatus);
        // TODO 伟帆 操作日志
        return true;
    }

    @Override
    public Boolean deleteProductCategory(ProductCategoryDeleteDTO productCategoryDeleteDTO) {
        Integer productCategoryId = productCategoryDeleteDTO.getId();
        // 校验分类是否存在
        ProductCategoryDO productCategory = productCategoryMapper.selectById(productCategoryId);
        if (productCategory == null) {
            throw ServiceExceptionUtil.exception(PRODUCT_CATEGORY_NOT_EXISTS);
        }
        // 只有禁用的商品分类才可以删除
        if (ProductCategoryStatusEnum.ENABLED.getStatus().equals(productCategory.getStatus())) {
            throw ServiceExceptionUtil.exception(PRODUCT_CATEGORY_DELETE_ONLY_DISABLE);
        }
        // 只有不存在子分类才可以删除
        // TODO FROM 芋艿 to jiangweifan：Wrappers 只用在 Mapper 层 [DONE]
        Integer childCount = productCategoryMapper.selectChildCategoryCount(productCategoryId);
        if (childCount > 0) {
            throw ServiceExceptionUtil.exception(PRODUCT_CATEGORY_DELETE_ONLY_NO_CHILD);
        }
        // TODO 伟帆 补充只有不存在商品才可以删除
        // 标记删除商品分类
        productCategoryMapper.deleteById(productCategoryId);
        // TODO 伟帆 操作日志
        return true;
    }

    private void validParent(Integer pid) {
        if (!ProductCategoryNodeEnum.ROOT.getId().equals(pid)) {
            ProductCategoryDO parentCategory = productCategoryMapper.selectById(pid);
            // 校验父分类是否存在
            if (parentCategory == null) {
                throw ServiceExceptionUtil.exception(PRODUCT_CATEGORY_PARENT_NOT_EXISTS);
            }
            // 父分类必须是一级分类
            if (!ProductCategoryNodeEnum.ROOT.getId().equals(parentCategory.getPid())) {
                throw ServiceExceptionUtil.exception(PRODUCT_CATEGORY_PARENT_CAN_NOT_BE_LEVEL2);
            }
        }
    }

    @Override
    public ProductCategoryDO validProductCategory(Integer productCategoryId) {
        // 校验分类是否存在
        ProductCategoryDO productCategory = productCategoryMapper.selectById(productCategoryId);
        if (productCategory == null) {
            throw ServiceExceptionUtil.exception(ProductErrorCodeEnum.PRODUCT_CATEGORY_NOT_EXISTS.getCode());
        }
        // 只有禁用的商品分类才可以删除
        if (ProductCategoryStatusEnum.DISABLED.getStatus().equals(productCategory.getStatus())) {
            throw ServiceExceptionUtil.exception(ProductErrorCodeEnum.PRODUCT_CATEGORY_MUST_ENABLE.getCode());
        }
        // 返回结果
        return productCategory;
    }
}
