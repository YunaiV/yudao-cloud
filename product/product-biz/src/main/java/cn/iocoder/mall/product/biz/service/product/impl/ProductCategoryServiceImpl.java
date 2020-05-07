package cn.iocoder.mall.product.biz.service.product.impl;

import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.mall.mybatis.enums.DeletedStatusEnum;
import cn.iocoder.mall.product.biz.bo.category.ProductCategoryAddBO;
import cn.iocoder.mall.product.biz.bo.category.ProductCategoryAllListBO;
import cn.iocoder.mall.product.biz.convert.category.ProductCategoryConvert;
import cn.iocoder.mall.product.biz.dao.product.ProductCategoryMapper;
import cn.iocoder.mall.product.biz.dataobject.product.ProductCategoryDO;
import cn.iocoder.mall.product.biz.dto.category.ProductCategoryAddDTO;
import cn.iocoder.mall.product.biz.dto.category.ProductCategoryDeleteDTO;
import cn.iocoder.mall.product.biz.dto.category.ProductCategoryUpdateDTO;
import cn.iocoder.mall.product.biz.dto.category.ProductCategoryUpdateStatusDTO;
import cn.iocoder.mall.product.biz.enums.ProductErrorCodeEnum;
import cn.iocoder.mall.product.biz.enums.product.ProductCategoryConstants;
import cn.iocoder.mall.product.biz.service.product.ProductCategoryService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
import java.util.*;

/**
 * @Author: jiangweifan
 * @Date: 2020/5/6
 * @Description: 商品分类 - 服务实现层
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    /**
     * 获取所有商品分类
     * @return
     */
    @Override
    public List<ProductCategoryAllListBO> getAllProductCategory() {
        List<ProductCategoryDO> categoryList = productCategoryMapper.selectList(null);
        return ProductCategoryConvert.INSTANCE.convertToAllListBO(categoryList);
    }

    /**
     * 新增商品分类
     * @param productCategoryAddDTO
     * @return
     */
    @Override
    public ProductCategoryAddBO addProductCategory(ProductCategoryAddDTO productCategoryAddDTO) {
        // 校验父分类
        validParent(productCategoryAddDTO.getPid());
        // 保存到数据库
        ProductCategoryDO productCategory = ProductCategoryConvert.INSTANCE.convertToDO(productCategoryAddDTO)
                .setStatus(ProductCategoryConstants.STATUS_ENABLE);
        productCategory.setCreateTime(new Date());
        productCategory.setDeleted(DeletedStatusEnum.DELETED_NO.getValue());
        productCategoryMapper.insert(productCategory);
        // TODO jiangweifan 操作日志
        // 返回成功
        return ProductCategoryConvert.INSTANCE.convertToAddBO(productCategory);
    }

    /**
     * 更新商品分类
     * @param productCategoryUpdateDTO
     * @return
     */
    @Override
    public Boolean updateProductCategory(ProductCategoryUpdateDTO productCategoryUpdateDTO) {
        // 校验当前分类是否存在
        if (productCategoryMapper.selectById(productCategoryUpdateDTO.getId()) == null) {
            throw ServiceExceptionUtil.exception(ProductErrorCodeEnum.PRODUCT_CATEGORY_NOT_EXISTS.getCode());
        }
        // 校验父分类
        validParent(productCategoryUpdateDTO.getPid());
        // 校验不能设置自己为父分类
        if (productCategoryUpdateDTO.getId().equals(productCategoryUpdateDTO.getPid())) {
            throw ServiceExceptionUtil.exception(ProductErrorCodeEnum.PRODUCT_CATEGORY_PARENT_NOT_SELF.getCode());
        }
        // 校验父分类是否存在
        if (!ProductCategoryConstants.PID_ROOT.equals(productCategoryUpdateDTO.getPid())
                && productCategoryMapper.selectById(productCategoryUpdateDTO.getPid()) == null) {
            throw ServiceExceptionUtil.exception(ProductErrorCodeEnum.PRODUCT_CATEGORY_PARENT_NOT_EXISTS.getCode());
        }
        // 更新到数据库
        ProductCategoryDO productCategoryDO = ProductCategoryConvert.INSTANCE.convertToDO(productCategoryUpdateDTO);
        productCategoryMapper.updateById(productCategoryDO);
        // TODO jiangweifan 操作日志
        return true;
    }

    /**
     * 更新商品分类状态
     * @param productCategoryUpdateStatusDTO
     * @return
     */
    @Override
    public Boolean updateProductCategoryStatus(ProductCategoryUpdateStatusDTO productCategoryUpdateStatusDTO) {
        Integer productCategoryId = productCategoryUpdateStatusDTO.getId();
        Integer status = productCategoryUpdateStatusDTO.getStatus();
        // 校验商品分类是否存在
        ProductCategoryDO productCategoryDO = productCategoryMapper.selectById(productCategoryId);
        if (productCategoryDO == null) {
            throw ServiceExceptionUtil.exception(ProductErrorCodeEnum.PRODUCT_CATEGORY_NOT_EXISTS.getCode());
        }
        // 判断更新状态是否存在
        if (!ProductCategoryConstants.STATUS_ENABLE.equals(status)
                && !ProductCategoryConstants.STATUS_DISABLE.equals(status)) {
            throw ServiceExceptionUtil.exception(ProductErrorCodeEnum.PRODUCT_CATEGORY_STATUS_NOT_EXISTS.getCode());
        }
        // 如果状态相同，则返回错误
        if (productCategoryDO.getStatus().equals(status)) {
            throw ServiceExceptionUtil.exception(ProductErrorCodeEnum.PRODUCT_CATEGORY_STATUS_EQUALS.getCode());
        }
        // 更新商品分类
        productCategoryDO.setId(productCategoryId).setStatus(status);
        productCategoryMapper.updateById(productCategoryDO);
        // TODO jiangweifan 操作日志
        return true;
    }

    /**
     * 删除商品分类
     * @param productCategoryDeleteDTO
     * @return
     */
    @Override
    public Boolean deleteProductCategory(ProductCategoryDeleteDTO productCategoryDeleteDTO) {
        Integer productCategoryId = productCategoryDeleteDTO.getId();
        // 校验分类是否存在
        ProductCategoryDO productCategory = productCategoryMapper.selectById(productCategoryId);
        if (productCategory == null) {
            throw ServiceExceptionUtil.exception(ProductErrorCodeEnum.PRODUCT_CATEGORY_NOT_EXISTS.getCode());
        }
        // 只有禁用的商品分类才可以删除
        if (ProductCategoryConstants.STATUS_ENABLE.equals(productCategory.getStatus())) {
            throw ServiceExceptionUtil.exception(ProductErrorCodeEnum.PRODUCT_CATEGORY_DELETE_ONLY_DISABLE.getCode());
        }
        // 只有不存在子分类才可以删除
        Integer childCount = productCategoryMapper.selectCount(
                Wrappers.<ProductCategoryDO>lambdaQuery().eq(ProductCategoryDO::getPid, productCategoryId)
        );
        if (childCount > 0) {
            throw ServiceExceptionUtil.exception(ProductErrorCodeEnum.PRODUCT_CATEGORY_DELETE_ONLY_NO_CHILD.getCode());
        }
        // TODO jiangweifan 补充只有不存在商品才可以删除
        // 标记删除商品分类
        ProductCategoryDO updateProductCategory = new ProductCategoryDO()
                .setId(productCategoryId);
        updateProductCategory.setDeleted(DeletedStatusEnum.DELETED_YES.getValue());
        productCategoryMapper.updateById(updateProductCategory);
        // TODO jiangweifan 操作日志
        return true;
    }

    /**
     * 校验商品分类的父分类
     * @param pid
     */
    private void validParent(Integer pid) {
        if (!ProductCategoryConstants.PID_ROOT.equals(pid)) {
            ProductCategoryDO parentCategory = productCategoryMapper.selectById(pid);
            // 校验父分类是否存在
            if (parentCategory == null) {
                throw ServiceExceptionUtil.exception(ProductErrorCodeEnum.PRODUCT_CATEGORY_PARENT_NOT_EXISTS.getCode());
            }
            // 父分类必须是一级分类
            if (!ProductCategoryConstants.PID_ROOT.equals(parentCategory.getPid())) {
                throw ServiceExceptionUtil.exception((ProductErrorCodeEnum.PRODUCT_CATEGORY_PARENT_CAN_NOT_BE_LEVEL2.getCode()));
            }
        }
    }

}
