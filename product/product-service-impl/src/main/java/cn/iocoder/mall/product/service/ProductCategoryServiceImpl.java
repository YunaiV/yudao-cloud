package cn.iocoder.mall.product.service;

import cn.iocoder.common.framework.constant.DeletedStatusEnum;
import cn.iocoder.common.framework.constant.SysErrorCodeEnum;
import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.product.api.ProductCategoryService;
import cn.iocoder.mall.product.api.bo.ProductCategoryBO;
import cn.iocoder.mall.product.api.constant.ProductCategoryConstants;
import cn.iocoder.mall.product.api.constant.ProductErrorCodeEnum;
import cn.iocoder.mall.product.api.dto.ProductCategoryAddDTO;
import cn.iocoder.mall.product.api.dto.ProductCategoryUpdateDTO;
import cn.iocoder.mall.product.convert.ProductCategoryConvert;
import cn.iocoder.mall.product.dao.ProductCategoryMapper;
import cn.iocoder.mall.product.dataobject.ProductCategoryDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service // 实际上不用添加。添加的原因是，必须 Spring 报错提示
@org.apache.dubbo.config.annotation.Service(validation = "true")
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Override
    public List<ProductCategoryBO> getListByPid(Integer pid) {
        List<ProductCategoryDO> categoryList = productCategoryMapper.selectListByPidAndStatusOrderBySort(pid, ProductCategoryConstants.STATUS_ENABLE);
        return ProductCategoryConvert.INSTANCE.convertToBO(categoryList);
    }

    @Override
    public List<ProductCategoryBO> getListByIds(Collection<Integer> ids) {
        List<ProductCategoryDO> categoryList = productCategoryMapper.selectByIds(ids);
        return ProductCategoryConvert.INSTANCE.convertToBO(categoryList);
    }

    @Override
    public CommonResult<List<ProductCategoryBO>> getAll() {
        List<ProductCategoryDO> categoryList = productCategoryMapper.selectList();
        return CommonResult.success(ProductCategoryConvert.INSTANCE.convertToBO(categoryList));
    }

    @Override
    public CommonResult<ProductCategoryBO> addProductCategory(Integer adminId, ProductCategoryAddDTO productCategoryAddDTO) {
        // 校验父分类是否存在
        if (!ProductCategoryConstants.PID_ROOT.equals(productCategoryAddDTO.getPid())
            && productCategoryMapper.selectById(productCategoryAddDTO.getPid()) == null) {
            return ServiceExceptionUtil.error(ProductErrorCodeEnum.PRODUCT_CATEGORY_PARENT_NOT_EXISTS.getCode());
        }
        // 保存到数据库
        ProductCategoryDO productCategory = ProductCategoryConvert.INSTANCE.convert(productCategoryAddDTO)
                .setStatus(ProductCategoryConstants.STATUS_ENABLE);
        productCategory.setCreateTime(new Date());
        productCategory.setDeleted(DeletedStatusEnum.DELETED_NO.getValue());
        productCategoryMapper.insert(productCategory);
        // TODO 操作日志
        // 返回成功
        return CommonResult.success(ProductCategoryConvert.INSTANCE.convertToBO(productCategory));
    }

    @Override
    public CommonResult<Boolean> updateProductCategory(Integer adminId, ProductCategoryUpdateDTO productCategoryUpdateDTO) {
        // 校验分类是否存在
        if (productCategoryMapper.selectById(productCategoryUpdateDTO.getId()) == null) {
            return ServiceExceptionUtil.error(ProductErrorCodeEnum.PRODUCT_CATEGORY_NOT_EXISTS.getCode());
        }
        // 校验不能设置自己为父分类
        if (productCategoryUpdateDTO.getId().equals(productCategoryUpdateDTO.getPid())) {
            return ServiceExceptionUtil.error(ProductErrorCodeEnum.PRODUCT_CATEGORY_PARENT_NOT_SELF.getCode());
        }
        // 校验父分类是否存在
        if (!ProductCategoryConstants.PID_ROOT.equals(productCategoryUpdateDTO.getPid())
                && productCategoryMapper.selectById(productCategoryUpdateDTO.getPid()) == null) {
            return ServiceExceptionUtil.error(ProductErrorCodeEnum.PRODUCT_CATEGORY_PARENT_NOT_EXISTS.getCode());
        }
        // 更新到数据库
        ProductCategoryDO updateProductCategory = ProductCategoryConvert.INSTANCE.convert(productCategoryUpdateDTO);
        productCategoryMapper.update(updateProductCategory);
        // TODO 操作日志
        return CommonResult.success(true);
    }

    @Override
    public CommonResult<Boolean> updateProductCategoryStatus(Integer adminId, Integer productCategoryId, Integer status) {
        // 校验参数
        if (!isValidStatus(status)) {
            return CommonResult.error(SysErrorCodeEnum.VALIDATION_REQUEST_PARAM_ERROR.getCode(), "变更状态必须是开启（1）或关闭（2）"); // TODO 有点搓
        }
        // 校验分类是否存在
        ProductCategoryDO productCategory = productCategoryMapper.selectById(productCategoryId);
        if (productCategory == null) {
            return ServiceExceptionUtil.error(ProductErrorCodeEnum.PRODUCT_CATEGORY_NOT_EXISTS.getCode());
        }
        // 如果状态相同，则返回错误
        if (productCategory.getStatus().equals(status)) {
            return ServiceExceptionUtil.error(ProductErrorCodeEnum.PRODUCT_CATEGORY_STATUS_EQUALS.getCode());
        }
        // 更新商品分类
        ProductCategoryDO updateProductCategory = new ProductCategoryDO()
                .setId(productCategoryId).setStatus(status);
        productCategoryMapper.update(updateProductCategory);
        // TODO 操作日志
        return CommonResult.success(true);
    }

    @Override
    public CommonResult<Boolean> deleteProductCategory(Integer admin, Integer productCategoryId) {
        // 校验分类是否存在
        ProductCategoryDO productCategory = productCategoryMapper.selectById(productCategoryId);
        if (productCategory == null) {
            return ServiceExceptionUtil.error(ProductErrorCodeEnum.PRODUCT_CATEGORY_NOT_EXISTS.getCode());
        }
        // 只有禁用的商品分类才可以删除
        if (ProductCategoryConstants.STATUS_ENABLE.equals(productCategory.getStatus())) {
            return ServiceExceptionUtil.error(ProductErrorCodeEnum.PRODUCT_CATEGORY_DELETE_ONLY_DISABLE.getCode());
        }
        // TODO 芋艿：考虑下，是否需要判断下该分类下是否有商品
        // TODO 芋艿，需要补充下，还有子分类
        // 标记删除商品分类
        ProductCategoryDO updateProductCategory = new ProductCategoryDO()
                .setId(productCategoryId);
        updateProductCategory.setDeleted(DeletedStatusEnum.DELETED_YES.getValue());
        productCategoryMapper.update(updateProductCategory);
        // TODO 操作日志
        return CommonResult.success(true);
    }

    public ProductCategoryDO getProductCategory(Integer productCategoryId) {
        return productCategoryMapper.selectById(productCategoryId);
    }

    public CommonResult<ProductCategoryDO> validProductCategory(Integer productCategoryId) {
        // 校验分类是否存在
        ProductCategoryDO productCategory = productCategoryMapper.selectById(productCategoryId);
        if (productCategory == null) {
            return ServiceExceptionUtil.error(ProductErrorCodeEnum.PRODUCT_CATEGORY_NOT_EXISTS.getCode());
        }
        // 只有禁用的商品分类才可以删除
        if (ProductCategoryConstants.STATUS_DISABLE.equals(productCategory.getStatus())) {
            return ServiceExceptionUtil.error(ProductErrorCodeEnum.PRODUCT_CATEGORY_MUST_ENABLE.getCode());
        }
        // 返回结果
        return CommonResult.success(productCategory);
    }

    private boolean isValidStatus(Integer status) {
        return ProductCategoryConstants.STATUS_ENABLE.equals(status)
                || ProductCategoryConstants.STATUS_DISABLE.equals(status);
    }

}
