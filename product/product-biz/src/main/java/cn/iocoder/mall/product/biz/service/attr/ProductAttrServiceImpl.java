package cn.iocoder.mall.product.biz.service.attr;

import cn.iocoder.common.framework.util.CollectionUtil;
import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.mybatis.enums.DeletedStatusEnum;
import cn.iocoder.mall.product.biz.bo.attr.ProductAttrBO;
import cn.iocoder.mall.product.biz.bo.attr.ProductAttrSimpleWithValueBO;
import cn.iocoder.mall.product.biz.bo.attr.ProductAttrValueBO;
import cn.iocoder.mall.product.biz.bo.attr.ProductAttrWithValueBO;
import cn.iocoder.mall.product.biz.bo.product.ProductAttrAndValuePairBO;
import cn.iocoder.mall.product.biz.convert.attr.ProductAttrConvert;
import cn.iocoder.mall.product.biz.dao.attr.ProductAttrMapper;
import cn.iocoder.mall.product.biz.dao.attr.ProductAttrValueMapper;
import cn.iocoder.mall.product.biz.dataobject.attr.ProductAttrDO;
import cn.iocoder.mall.product.biz.dataobject.attr.ProductAttrValueDO;
import cn.iocoder.mall.product.biz.dto.attr.*;
import cn.iocoder.mall.product.biz.enums.ProductErrorCodeEnum;
import cn.iocoder.mall.product.biz.enums.attr.ProductAttrConstants;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 商品规格 Service 实现类
 *
 * @see ProductAttrDO
 * @see ProductAttrValueDO
 */
@Service
public class ProductAttrServiceImpl implements ProductAttrService {

    @Autowired
    private ProductAttrMapper productAttrMapper;
    @Autowired
    private ProductAttrValueMapper productAttrValueMapper;

    @Override
    public PageResult<ProductAttrWithValueBO> getProductAttrPage(ProductAttrPageDTO productAttrPageDTO) {
        //查询分页
        Page<ProductAttrDO> page = new Page<>(productAttrPageDTO.getPageNo(), productAttrPageDTO.getPageSize());
        LambdaQueryWrapper<ProductAttrDO> queryWrapper = Wrappers.<ProductAttrDO>query().lambda()
                .like(StringUtils.isNotBlank(productAttrPageDTO.getName()), ProductAttrDO::getName, productAttrPageDTO.getName())
                .eq(ProductAttrDO::getDeleted, false);
        IPage<ProductAttrDO> attrPage = productAttrMapper.selectPage(page, queryWrapper);
        PageResult<ProductAttrWithValueBO> productAttrPage = ProductAttrConvert.INSTANCE.convertPage(attrPage);
        // 将规格值拼接上去
        if (!CollectionUtil.isEmpty(productAttrPage.getList())) {
            Set<Integer> attrIds = productAttrPage.getList().stream().map(ProductAttrBO::getId).collect(Collectors.toSet());
            List<ProductAttrValueDO> attrValues = productAttrValueMapper.selectList(Wrappers.<ProductAttrValueDO>query().lambda()
                    .in(ProductAttrValueDO::getAttrId, attrIds)
                    .eq(ProductAttrValueDO::getDeleted, false));
            Map<Integer, List<ProductAttrValueDO>> attrValueMap = attrValues.stream().collect(Collectors.groupingBy(ProductAttrValueDO::getAttrId));
            for (ProductAttrWithValueBO item : productAttrPage.getList()) {
                item.setValues(ProductAttrConvert.INSTANCE.convertAttrValues(attrValueMap.get(item.getId())));
            }
        }
        return productAttrPage;
    }

    @Override
    public List<ProductAttrSimpleWithValueBO> getProductAttrList() {
        // 查询所有开启的规格数组
        List<ProductAttrDO> attrDos = productAttrMapper.selectList(Wrappers.<ProductAttrDO>query().lambda()
                .in(ProductAttrDO::getStatus, ProductAttrConstants.ATTR_STATUS_ENABLE)
                .eq(ProductAttrDO::getDeleted, false));
        // 如果为空，则返回空
        if (attrDos.isEmpty()) {
            return Collections.emptyList();
        }
        List<ProductAttrSimpleWithValueBO> attrs = ProductAttrConvert.INSTANCE.convertAttrSimple(attrDos);
        // 将规格值拼接上去
        List<ProductAttrValueDO> attrValues = productAttrValueMapper.selectList(Wrappers.<ProductAttrValueDO>query().lambda()
                .in(ProductAttrValueDO::getStatus, ProductAttrConstants.ATTR_STATUS_ENABLE)
                .eq(ProductAttrValueDO::getDeleted, false));
        Map<Integer, List<ProductAttrValueDO>> attrValueMap = attrValues.stream().collect(Collectors.groupingBy(ProductAttrValueDO::getAttrId));
        for (ProductAttrSimpleWithValueBO item : attrs) {
            item.setValues(ProductAttrConvert.INSTANCE.convertAttrValueSimple(attrValueMap.get(item.getId())));
        }
        return attrs;
    }

    @Override
    public ProductAttrBO addProductAttr(Integer adminId, ProductAttrAddDTO productAttrAddDTO) {
        // 校验规格名不重复
        int count = productAttrMapper.selectCount(Wrappers.<ProductAttrDO>query().lambda()
                .eq(ProductAttrDO::getName, productAttrAddDTO.getName())
                .eq(ProductAttrDO::getDeleted, false));
        if (count > 0) {
            throw ServiceExceptionUtil.exception(ProductErrorCodeEnum.PRODUCT_ATTR_EXISTS.getCode());
        }
        // 插入到数据库
        ProductAttrDO productAttrDO = new ProductAttrDO().setName(productAttrAddDTO.getName())
                .setStatus(ProductAttrConstants.ATTR_STATUS_ENABLE);
        productAttrDO.setCreateTime(new Date());
        productAttrDO.setDeleted(DeletedStatusEnum.DELETED_NO.getValue());
        productAttrMapper.insert(productAttrDO);
        // 返回成功
        return ProductAttrConvert.INSTANCE.convertAttr(productAttrDO);
    }

    @Override
    public Boolean updateProductAttr(Integer adminId, ProductAttrUpdateDTO productAttrUpdateDTO) {
        // 校验存在
        if (productAttrMapper.selectById(productAttrUpdateDTO.getId()) == null) {
            throw ServiceExceptionUtil.exception(ProductErrorCodeEnum.PRODUCT_ATTR_NOT_EXIST.getCode());
        }
        // 校验规格名不重复
        ProductAttrDO existsAttrDO = productAttrMapper.selectOne(Wrappers.<ProductAttrDO>query().lambda()
                .eq(ProductAttrDO::getName, productAttrUpdateDTO.getName())
                .eq(ProductAttrDO::getDeleted, false));
        if (existsAttrDO != null && !existsAttrDO.getId().equals(productAttrUpdateDTO.getId())) {
            throw ServiceExceptionUtil.exception(ProductErrorCodeEnum.PRODUCT_ATTR_EXISTS.getCode());
        }
        // 更新到数据库
        ProductAttrDO updateProductAttr = ProductAttrConvert.INSTANCE.convertUpdate(productAttrUpdateDTO);
        updateProductAttr.setUpdateTime(new Date());
        int i = productAttrMapper.updateById(updateProductAttr);
        // 返回成功
        return i > 0;
    }


    @Override
    public Boolean updateProductAttrStatus(Integer adminId, Integer productAttrId, Integer status) {
        // 校验存在
        ProductAttrDO productAttrDO = productAttrMapper.selectById(productAttrId);
        if (productAttrDO == null) {
            throw ServiceExceptionUtil.exception(ProductErrorCodeEnum.PRODUCT_ATTR_NOT_EXIST.getCode());
        }
        // 校验状态
        if (productAttrDO.getStatus().equals(status)) {
            throw ServiceExceptionUtil.exception(ProductErrorCodeEnum.PRODUCT_ATTR_STATUS_EQUALS.getCode());
        }
        // 更新到数据库
        ProductAttrDO updateProductAttr = new ProductAttrDO().setId(productAttrId).setStatus(status);
        int i = productAttrMapper.updateById(updateProductAttr);
        return i > 0;
    }

    @Override
    public ProductAttrValueBO addProductAttrValue(Integer adminId, ProductAttrValueAddDTO productAttrValueAddDTO) {
        // 校验规格名不重复
        int count = productAttrValueMapper.selectCount(Wrappers.<ProductAttrValueDO>query().lambda()
                .eq(ProductAttrValueDO::getName, productAttrValueAddDTO.getName())
                .eq(ProductAttrValueDO::getAttrId, productAttrValueAddDTO.getAttrId())
                .eq(ProductAttrValueDO::getDeleted, false));
        if (count > 0) {
            throw ServiceExceptionUtil.exception(ProductErrorCodeEnum.PRODUCT_ATTR_VALUE_EXISTS.getCode());
        }
        // 插入到数据库
        ProductAttrValueDO productAttrValueDO = ProductAttrConvert.INSTANCE.convertValueAdd(productAttrValueAddDTO)
                .setStatus(ProductAttrConstants.ATTR_VALUE_STATUS_ENABLE);
        productAttrValueDO.setCreateTime(new Date());
        productAttrValueDO.setDeleted(DeletedStatusEnum.DELETED_NO.getValue());
        productAttrValueMapper.insert(productAttrValueDO);
        return ProductAttrConvert.INSTANCE.convertAttrValue(productAttrValueDO);
    }

    @Override
    public Boolean updateProductAttrValue(Integer adminId, ProductAttrValueUpdateDTO productAttrValueUpdateDTO) {
        // 校验存在
        ProductAttrValueDO productAttrValueDO = productAttrValueMapper.selectById(productAttrValueUpdateDTO.getId());
        if (productAttrValueDO == null) {
            throw ServiceExceptionUtil.exception(ProductErrorCodeEnum.PRODUCT_ATTR_VALUE_NOT_EXIST.getCode());
        }
        // 校验规格名不重复
        ProductAttrValueDO existsAttrDO = productAttrValueMapper.selectOne(Wrappers.<ProductAttrValueDO>query().lambda()
                .eq(ProductAttrValueDO::getName, productAttrValueDO.getName())
                .eq(ProductAttrValueDO::getAttrId, productAttrValueDO.getAttrId())
                .eq(ProductAttrValueDO::getDeleted, false));
        if (existsAttrDO != null && !existsAttrDO.getId().equals(productAttrValueUpdateDTO.getId())) {
            throw ServiceExceptionUtil.exception(ProductErrorCodeEnum.PRODUCT_ATTR_VALUE_EXISTS.getCode());
        }
        // 更新到数据库
        ProductAttrValueDO updateProductValue = ProductAttrConvert.INSTANCE.convertValueUpdate(productAttrValueUpdateDTO);
        updateProductValue.setUpdateTime(new Date());
        int i = productAttrValueMapper.updateById(updateProductValue);
        return i > 0;
    }

    @Override
    public Boolean updateProductAttrValueStatus(Integer adminId, Integer productAttrValueId, Integer status) {
        // 校验存在
        ProductAttrValueDO productAttrValueDO = productAttrValueMapper.selectById(productAttrValueId);
        if (productAttrValueDO == null) {
            throw ServiceExceptionUtil.exception(ProductErrorCodeEnum.PRODUCT_ATTR_VALUE_NOT_EXIST.getCode());
        }
        // 校验状态
        if (productAttrValueDO.getStatus().equals(status)) {
            throw ServiceExceptionUtil.exception(ProductErrorCodeEnum.PRODUCT_ATTR_VALUE_STATUS_EQUALS.getCode());
        }
        // 更新到数据库
        ProductAttrValueDO updateProductAttrValue = new ProductAttrValueDO().setId(productAttrValueId).setStatus(status);
        int i = productAttrValueMapper.updateById(updateProductAttrValue);
        return i > 0;
    }

    @Override
    public List<ProductAttrAndValuePairBO> validProductAttrAndValue(Set<Integer> productAttrValueIds, boolean validStatus) {
        // 首先，校验规格值
        List<ProductAttrValueDO> attrValues = productAttrValueMapper.selectBatchIds(productAttrValueIds);
        if (attrValues.size() != productAttrValueIds.size()) {
            throw ServiceExceptionUtil.exception(ProductErrorCodeEnum.PRODUCT_ATTR_VALUE_NOT_EXIST.getCode());
        }
        if (validStatus) {
            // 同时，校验下状态
            for (ProductAttrValueDO attrValue : attrValues) {
                if (ProductAttrConstants.ATTR_STATUS_DISABLE.equals(attrValue.getStatus())) {
                    throw ServiceExceptionUtil.exception(ProductErrorCodeEnum.PRODUCT_ATTR_VALUE_NOT_EXIST.getCode());
                }
            }
        }
        // 然后，校验规格
        Set<Integer> attrIds = attrValues.stream().map(ProductAttrValueDO::getAttrId).collect(Collectors.toSet());
        List<ProductAttrDO> attrs = productAttrMapper.selectBatchIds(attrIds);
        if (attrs.size() != attrIds.size()) {
            throw ServiceExceptionUtil.exception(ProductErrorCodeEnum.PRODUCT_ATTR_NOT_EXIST.getCode());
        }
        if (validStatus) {
            // 同时，校验下状态
            for (ProductAttrDO attr : attrs) {
                if (ProductAttrConstants.ATTR_VALUE_STATUS_DISABLE.equals(attr.getStatus())) {
                    throw ServiceExceptionUtil.exception(ProductErrorCodeEnum.PRODUCT_ATTR_NOT_EXIST.getCode());
                }
            }
        }
        // 返回成功
        // ProductAttrDO 的映射，方便查找。
        Map<Integer, ProductAttrDO> attrMap = attrs.stream().collect(Collectors.toMap(ProductAttrDO::getId, productAttrDO -> productAttrDO));
        return attrValues.stream().map(productAttrValueDO -> new ProductAttrAndValuePairBO()
                .setAttrId(productAttrValueDO.getAttrId()).setAttrName(attrMap.get(productAttrValueDO.getAttrId()).getName())
                .setAttrValueId(productAttrValueDO.getId()).setAttrValueName(productAttrValueDO.getName())).collect(Collectors.toList());
    }

}
