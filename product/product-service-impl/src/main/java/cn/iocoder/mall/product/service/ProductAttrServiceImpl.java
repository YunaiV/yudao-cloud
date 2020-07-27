package cn.iocoder.mall.product.service;

import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.mall.mybatis.core.enums.DeletedStatusEnum;
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
@org.apache.dubbo.config.annotation.Service(validation = "true", version = "${dubbo.provider.ProductAttrService.version}")
public class ProductAttrServiceImpl implements ProductAttrService {

    @Autowired
    private ProductAttrMapper productAttrMapper;
    @Autowired
    private ProductAttrValueMapper productAttrValueMapper;

    @Override
    public ProductAttrPageBO getProductAttrPage(ProductAttrPageDTO productAttrPageDTO) {
        ProductAttrPageBO productAttrPageBO = new ProductAttrPageBO();
        // 查询分页数据
        int offset = (productAttrPageDTO.getPageNo()-1) * productAttrPageDTO.getPageSize();
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
        return productAttrPageBO;
    }

    @Override
    public List<ProductAttrSimpleBO> getProductAttrList() {
        // 查询所有开启的规格数组
        List<ProductAttrSimpleBO> attrs = ProductAttrConvert.INSTANCE.convert3(productAttrMapper.selectListByStatus(ProductAttrConstants.ATTR_STATUS_ENABLE));
        // 如果为空，则返回空
        if (attrs.isEmpty()) {
            return Collections.emptyList();
        }
        // 将规格值拼接上去
        List<ProductAttrValueDO> attrValues = productAttrValueMapper.selectListByStatus(ProductAttrConstants.ATTR_VALUE_STATUS_ENABLE);
        ImmutableListMultimap<Integer, ProductAttrValueDO> attrValueMap = Multimaps.index(attrValues, ProductAttrValueDO::getAttrId); // KEY 是 attrId ，VALUE 是 ProductAttrValueDO 数组
        for (ProductAttrSimpleBO productAttrSimpleBO : attrs) {
            productAttrSimpleBO.setValues(ProductAttrConvert.INSTANCE.convert4(((attrValueMap).get(productAttrSimpleBO.getId()))));
        }
        return attrs;
    }

    @Override
    public ProductAttrBO addProductAttr(Integer adminId, ProductAttrAddDTO productAttrAddDTO) {
        // 校验规格名不重复
        if (productAttrMapper.selectByName(productAttrAddDTO.getName()) != null) {
            throw ServiceExceptionUtil.exception(ProductErrorCodeEnum.PRODUCT_ATTR_EXISTS.getCode());
        }
        // 插入到数据库
        ProductAttrDO productAttrDO = ProductAttrConvert.INSTANCE.convert(productAttrAddDTO)
                .setStatus(ProductAttrConstants.ATTR_STATUS_ENABLE);
        productAttrDO.setCreateTime(new Date());
        productAttrDO.setDeleted(DeletedStatusEnum.DELETED_NO.getValue());
        productAttrMapper.insert(productAttrDO);
        // 返回成功
        return ProductAttrConvert.INSTANCE.convert(productAttrDO);
    }

    @Override
    public Boolean updateProductAttr(Integer adminId, ProductAttrUpdateDTO productAttrUpdateDTO) {
        // 校验存在
        if (productAttrMapper.selectById(productAttrUpdateDTO.getId()) == null) {
            throw ServiceExceptionUtil.exception(ProductErrorCodeEnum.PRODUCT_ATTR_NOT_EXIST.getCode());
        }
        // 校验规格名不重复
        ProductAttrDO existsAttrDO = productAttrMapper.selectByName(productAttrUpdateDTO.getName());
        if (existsAttrDO != null && !existsAttrDO.getId().equals(productAttrUpdateDTO.getId())) {
            throw ServiceExceptionUtil.exception(ProductErrorCodeEnum.PRODUCT_ATTR_EXISTS.getCode());
        }
        // 更新到数据库
        ProductAttrDO updateProductAttr = ProductAttrConvert.INSTANCE.convert(productAttrUpdateDTO);
        productAttrMapper.update(updateProductAttr);
        // 返回成功
        return true;
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
        productAttrMapper.update(updateProductAttr);
        // 返回成功
        return true;
    }

    @Override
    public ProductAttrValueBO addProductAttrValue(Integer adminId, ProductAttrValueAddDTO productAttrValueAddDTO) {
        // 校验规格名不重复
        if (productAttrValueMapper.selectByAttrIdAndName(productAttrValueAddDTO.getAttrId(), productAttrValueAddDTO.getName()) != null) {
            throw ServiceExceptionUtil.exception(ProductErrorCodeEnum.PRODUCT_ATTR_VALUE_EXISTS.getCode());
        }
        // 插入到数据库
        ProductAttrValueDO productAttrValueDO = ProductAttrConvert.INSTANCE.convert(productAttrValueAddDTO)
                .setStatus(ProductAttrConstants.ATTR_VALUE_STATUS_ENABLE);
        productAttrValueDO.setCreateTime(new Date());
        productAttrValueDO.setDeleted(DeletedStatusEnum.DELETED_NO.getValue());
        productAttrValueMapper.insert(productAttrValueDO);
        // 返回成功
        return ProductAttrConvert.INSTANCE.convert2(productAttrValueDO);
    }

    @Override
    public Boolean updateProductAttrValue(Integer adminId, ProductAttrValueUpdateDTO productAttrValueUpdateDTO) {
        // 校验存在
        ProductAttrValueDO productAttrValueDO = productAttrValueMapper.selectById(productAttrValueUpdateDTO.getId());
        if (productAttrValueDO == null) {
            throw ServiceExceptionUtil.exception(ProductErrorCodeEnum.PRODUCT_ATTR_VALUE_NOT_EXIST.getCode());
        }
        // 校验规格名不重复
        ProductAttrValueDO existsAttrDO = productAttrValueMapper.selectByAttrIdAndName(productAttrValueDO.getAttrId(), productAttrValueUpdateDTO.getName());
        if (existsAttrDO != null && !existsAttrDO.getId().equals(productAttrValueUpdateDTO.getId())) {
            throw ServiceExceptionUtil.exception(ProductErrorCodeEnum.PRODUCT_ATTR_VALUE_EXISTS.getCode());
        }
        // 更新到数据库
        ProductAttrValueDO updateProductValue = ProductAttrConvert.INSTANCE.convert(productAttrValueUpdateDTO);
        productAttrValueMapper.update(updateProductValue);
        // 返回成功
        return true;
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
        productAttrValueMapper.update(updateProductAttrValue);
        // 返回成功
        return true;
    }

}
