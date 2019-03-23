package cn.iocoder.mall.product.service;

import cn.iocoder.common.framework.constant.DeletedStatusEnum;
import cn.iocoder.common.framework.constant.SysErrorCodeEnum;
import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.product.api.ProductAttrService;
import cn.iocoder.mall.product.api.bo.*;
import cn.iocoder.mall.product.api.constant.ProductAttrConstants;
import cn.iocoder.mall.product.api.constant.ProductErrorCodeEnum;
import cn.iocoder.mall.product.api.dto.*;
import cn.iocoder.mall.product.convert.ProductAttrConvert;
import cn.iocoder.mall.product.dao.ProductAttrMapper;
import cn.iocoder.mall.product.dao.ProductAttrValueMapper;
import cn.iocoder.mall.product.dataobject.ProductAttrDO;
import cn.iocoder.mall.product.dataobject.ProductAttrValueDO;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.Multimaps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 商品规格 Service 实现类
 *
 * @see cn.iocoder.mall.product.dataobject.ProductAttrDO
 * @see cn.iocoder.mall.product.dataobject.ProductAttrValueDO
 */
@Service
@com.alibaba.dubbo.config.annotation.Service(validation = "true")
public class ProductAttrServiceImpl implements ProductAttrService {

    @Autowired
    private ProductAttrMapper productAttrMapper;
    @Autowired
    private ProductAttrValueMapper productAttrValueMapper;

    public CommonResult<List<ProductAttrAndValuePairBO>> validProductAttrAndValue(Set<Integer> productAttrValueIds, boolean validStatus) {
        // 首先，校验规格值
        List<ProductAttrValueDO> attrValues = productAttrValueMapper.selectListByIds(productAttrValueIds);
        if (attrValues.size() != productAttrValueIds.size()) {
            return ServiceExceptionUtil.error(ProductErrorCodeEnum.PRODUCT_ATTR_VALUE_NOT_EXIST.getCode());
        }
        if (validStatus) {
            for (ProductAttrValueDO attrValue : attrValues) { // 同时，校验下状态
                if (ProductAttrConstants.ATTR_STATUS_DISABLE.equals(attrValue.getStatus())) {
                    return ServiceExceptionUtil.error(ProductErrorCodeEnum.PRODUCT_ATTR_VALUE_NOT_EXIST.getCode());
                }
            }
        }
        // 然后，校验规格
        Set<Integer> attrIds = attrValues.stream().map(ProductAttrValueDO::getAttrId).collect(Collectors.toSet());
        List<ProductAttrDO> attrs = productAttrMapper.selectListByIds(attrIds);
        if (attrs.size() != attrIds.size()) {
            return ServiceExceptionUtil.error(ProductErrorCodeEnum.PRODUCT_ATTR_NOT_EXIST.getCode());
        }
        if (validStatus) {
            for (ProductAttrDO attr : attrs) { // 同时，校验下状态
                if (ProductAttrConstants.ATTR_VALUE_STATUS_DISABLE.equals(attr.getStatus())) {
                    return ServiceExceptionUtil.error(ProductErrorCodeEnum.PRODUCT_ATTR_NOT_EXIST.getCode());
                }
            }
        }
        // 返回成功
        Map<Integer, ProductAttrDO> attrMap = attrs.stream().collect(Collectors.toMap(ProductAttrDO::getId, productAttrDO -> productAttrDO)); // ProductAttrDO 的映射，方便查找。
        List<ProductAttrAndValuePairBO> result = attrValues.stream().map(productAttrValueDO -> new ProductAttrAndValuePairBO()
                .setAttrId(productAttrValueDO.getAttrId()).setAttrName(attrMap.get(productAttrValueDO.getAttrId()).getName())
                .setAttrValueId(productAttrValueDO.getId()).setAttrValueName(productAttrValueDO.getName())).collect(Collectors.toList());
        return CommonResult.success(result);
    }

    @Override
    public CommonResult<ProductAttrPageBO> getProductAttrPage(ProductAttrPageDTO productAttrPageDTO) {
        ProductAttrPageBO productAttrPageBO = new ProductAttrPageBO();
        // 查询分页数据
        int offset = productAttrPageDTO.getPageNo() * productAttrPageDTO.getPageSize();
        productAttrPageBO.setAttrs(ProductAttrConvert.INSTANCE.convert(productAttrMapper.selectListByNameLike(productAttrPageDTO.getName(),
                offset, productAttrPageDTO.getPageSize())));
        // 查询分页总数
        productAttrPageBO.setCount(productAttrMapper.selectCountByNameLike(productAttrPageDTO.getName()));
        // 将规格值拼接上去
        if (!productAttrPageBO.getAttrs().isEmpty()) {
            Set<Integer> attrIds = productAttrPageBO.getAttrs().stream().map(ProductAttrDetailBO::getId).collect(Collectors.toSet());
            List<ProductAttrValueDO> attrValues = productAttrValueMapper.selectListByAttrIds(attrIds);
            ImmutableListMultimap<Integer, ProductAttrValueDO> attrValueMap = Multimaps.index(attrValues, ProductAttrValueDO::getAttrId); // KEY 是 attrId ，VALUE 是 ProductAttrValueDO 数组
            for (ProductAttrDetailBO productAttrDetailBO : productAttrPageBO.getAttrs()) {
                productAttrDetailBO.setValues(ProductAttrConvert.INSTANCE.convert2(((attrValueMap).get(productAttrDetailBO.getId()))));
            }
        }
        // 返回结果
        return CommonResult.success(productAttrPageBO);
    }

    @Override
    public CommonResult<List<ProductAttrSimpleBO>> getProductAttrList() {
        // 查询所有开启的规格数组
        List<ProductAttrSimpleBO> attrs = ProductAttrConvert.INSTANCE.convert3(productAttrMapper.selectListByStatus(ProductAttrConstants.ATTR_STATUS_ENABLE));
        // 如果为空，则返回空
        if (attrs.isEmpty()) {
            return CommonResult.success(Collections.emptyList());
        }
        // 将规格值拼接上去
        List<ProductAttrValueDO> attrValues = productAttrValueMapper.selectListByStatus(ProductAttrConstants.ATTR_VALUE_STATUS_ENABLE);
        ImmutableListMultimap<Integer, ProductAttrValueDO> attrValueMap = Multimaps.index(attrValues, ProductAttrValueDO::getAttrId); // KEY 是 attrId ，VALUE 是 ProductAttrValueDO 数组
        for (ProductAttrSimpleBO productAttrSimpleBO : attrs) {
            productAttrSimpleBO.setValues(ProductAttrConvert.INSTANCE.convert4(((attrValueMap).get(productAttrSimpleBO.getId()))));
        }
        return CommonResult.success(attrs);
    }

    @Override
    public CommonResult<ProductAttrBO> addProductAttr(Integer adminId, ProductAttrAddDTO productAttrAddDTO) {
        // 校验规格名不重复
        if (productAttrMapper.selectByName(productAttrAddDTO.getName()) != null) {
            return ServiceExceptionUtil.error(ProductErrorCodeEnum.PRODUCT_ATTR_EXISTS.getCode());
        }
        // 插入到数据库
        ProductAttrDO productAttrDO = ProductAttrConvert.INSTANCE.convert(productAttrAddDTO)
                .setStatus(ProductAttrConstants.ATTR_STATUS_ENABLE);
        productAttrDO.setCreateTime(new Date());
        productAttrDO.setDeleted(DeletedStatusEnum.DELETED_NO.getValue());
        productAttrMapper.insert(productAttrDO);
        // 返回成功
        return CommonResult.success(ProductAttrConvert.INSTANCE.convert(productAttrDO));
    }

    @Override
    public CommonResult<Boolean> updateProductAttr(Integer adminId, ProductAttrUpdateDTO productAttrUpdateDTO) {
        // 校验存在
        if (productAttrMapper.selectById(productAttrUpdateDTO.getId()) == null) {
            return ServiceExceptionUtil.error(ProductErrorCodeEnum.PRODUCT_ATTR_NOT_EXIST.getCode());
        }
        // 校验规格名不重复
        ProductAttrDO existsAttrDO = productAttrMapper.selectByName(productAttrUpdateDTO.getName());
        if (existsAttrDO != null && !existsAttrDO.getId().equals(productAttrUpdateDTO.getId())) {
            return ServiceExceptionUtil.error(ProductErrorCodeEnum.PRODUCT_ATTR_EXISTS.getCode());
        }
        // 更新到数据库
        ProductAttrDO updateProductAttr = ProductAttrConvert.INSTANCE.convert(productAttrUpdateDTO);
        productAttrMapper.update(updateProductAttr);
        // 返回成功
        return CommonResult.success(true);
    }

    @Override
    public CommonResult<Boolean> updateProductAttrStatus(Integer adminId, Integer productAttrId, Integer status) {
        // 校验参数
        if (!isValidAttrStatus(status)) {
            return CommonResult.error(SysErrorCodeEnum.VALIDATION_REQUEST_PARAM_ERROR.getCode(), "变更状态必须是开启（1）或关闭（2）"); // TODO 有点搓
        }
        // 校验存在
        ProductAttrDO productAttrDO = productAttrMapper.selectById(productAttrId);
        if (productAttrDO == null) {
            return ServiceExceptionUtil.error(ProductErrorCodeEnum.PRODUCT_ATTR_NOT_EXIST.getCode());
        }
        // 校验状态
        if (productAttrDO.getStatus().equals(status)) {
            return ServiceExceptionUtil.error(ProductErrorCodeEnum.PRODUCT_ATTR_STATUS_EQUALS.getCode());
        }
        // 更新到数据库
        ProductAttrDO updateProductAttr = new ProductAttrDO().setId(productAttrId).setStatus(status);
        productAttrMapper.update(updateProductAttr);
        // 返回成功
        return CommonResult.success(true);
    }

    @Override
    public CommonResult<ProductAttrValueBO> addProductAttrValue(Integer adminId, ProductAttrValueAddDTO productAttrValueAddDTO) {
        // 校验规格名不重复
        if (productAttrValueMapper.selectByAttrIdAndName(productAttrValueAddDTO.getAttrId(), productAttrValueAddDTO.getName()) != null) {
            return ServiceExceptionUtil.error(ProductErrorCodeEnum.PRODUCT_ATTR_VALUE_EXISTS.getCode());
        }
        // 插入到数据库
        ProductAttrValueDO productAttrValueDO = ProductAttrConvert.INSTANCE.convert(productAttrValueAddDTO)
                .setStatus(ProductAttrConstants.ATTR_VALUE_STATUS_ENABLE);
        productAttrValueDO.setCreateTime(new Date());
        productAttrValueDO.setDeleted(DeletedStatusEnum.DELETED_NO.getValue());
        productAttrValueMapper.insert(productAttrValueDO);
        // 返回成功
        return CommonResult.success(ProductAttrConvert.INSTANCE.convert2(productAttrValueDO));
    }

    @Override
    public CommonResult<Boolean> updateProductAttrValue(Integer adminId, ProductAttrValueUpdateDTO productAttrValueUpdateDTO) {
        // 校验存在
        ProductAttrValueDO productAttrValueDO = productAttrValueMapper.selectById(productAttrValueUpdateDTO.getId());
        if (productAttrValueDO == null) {
            return ServiceExceptionUtil.error(ProductErrorCodeEnum.PRODUCT_ATTR_VALUE_NOT_EXIST.getCode());
        }
        // 校验规格名不重复
        ProductAttrValueDO existsAttrDO = productAttrValueMapper.selectByAttrIdAndName(productAttrValueDO.getAttrId(), productAttrValueUpdateDTO.getName());
        if (existsAttrDO != null && !existsAttrDO.getId().equals(productAttrValueUpdateDTO.getId())) {
            return ServiceExceptionUtil.error(ProductErrorCodeEnum.PRODUCT_ATTR_VALUE_EXISTS.getCode());
        }
        // 更新到数据库
        ProductAttrValueDO updateProductValue = ProductAttrConvert.INSTANCE.convert(productAttrValueUpdateDTO);
        productAttrValueMapper.update(updateProductValue);
        // 返回成功
        return CommonResult.success(true);
    }

    @Override
    public CommonResult<Boolean> updateProductAttrValueStatus(Integer adminId, Integer productAttrValueId, Integer status) {
        // 校验参数
        if (!isValidAttrValueStatus(status)) {
            return CommonResult.error(SysErrorCodeEnum.VALIDATION_REQUEST_PARAM_ERROR.getCode(), "变更状态必须是开启（1）或关闭（2）"); // TODO 有点搓
        }
        // 校验存在
        ProductAttrValueDO productAttrValueDO = productAttrValueMapper.selectById(productAttrValueId);
        if (productAttrValueDO == null) {
            return ServiceExceptionUtil.error(ProductErrorCodeEnum.PRODUCT_ATTR_VALUE_NOT_EXIST.getCode());
        }
        // 校验状态
        if (productAttrValueDO.getStatus().equals(status)) {
            return ServiceExceptionUtil.error(ProductErrorCodeEnum.PRODUCT_ATTR_VALUE_STATUS_EQUALS.getCode());
        }
        // 更新到数据库
        ProductAttrValueDO updateProductAttrValue = new ProductAttrValueDO().setId(productAttrValueId).setStatus(status);
        productAttrValueMapper.update(updateProductAttrValue);
        // 返回成功
        return CommonResult.success(true);
    }

    private boolean isValidAttrStatus(Integer status) {
        return ProductAttrConstants.ATTR_STATUS_ENABLE.equals(status)
                || ProductAttrConstants.ATTR_STATUS_DISABLE.equals(status);
    }

    private boolean isValidAttrValueStatus(Integer status) {
        return ProductAttrConstants.ATTR_VALUE_STATUS_ENABLE.equals(status)
                || ProductAttrConstants.ATTR_VALUE_STATUS_DISABLE.equals(status);
    }

}