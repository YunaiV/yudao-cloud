package cn.iocoder.mall.product.biz.service.product.impl;

import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.mall.mybatis.enums.DeletedStatusEnum;
import cn.iocoder.mall.product.biz.bo.category.ProductCategoryAddBO;
import cn.iocoder.mall.product.biz.bo.category.ProductCategoryAllListBO;
import cn.iocoder.mall.product.biz.convert.category.ProductCategoryConvert;
import cn.iocoder.mall.product.biz.dao.product.ProductCategoryMapper;
import cn.iocoder.mall.product.biz.dataobject.product.ProductCategoryDO;
import cn.iocoder.mall.product.biz.dto.category.ProductCategoryAddDTO;
import cn.iocoder.mall.product.biz.enums.ProductErrorCodeEnum;
import cn.iocoder.mall.product.biz.enums.product.ProductCategoryConstants;
import cn.iocoder.mall.product.biz.service.product.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
