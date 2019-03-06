package cn.iocoder.mall.product.service;

import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.product.api.ProductAttrService;
import cn.iocoder.mall.product.api.bo.ProductAttrAndValuePairBO;
import cn.iocoder.mall.product.api.bo.ProductAttrDetailBO;
import cn.iocoder.mall.product.api.bo.ProductAttrPageBO;
import cn.iocoder.mall.product.api.bo.ProductAttrSimpleBO;
import cn.iocoder.mall.product.api.constant.ProductAttrConstants;
import cn.iocoder.mall.product.api.constant.ProductErrorCodeEnum;
import cn.iocoder.mall.product.api.dto.ProductAttrPageDTO;
import cn.iocoder.mall.product.convert.ProductAttrConvert;
import cn.iocoder.mall.product.dao.ProductAttrMapper;
import cn.iocoder.mall.product.dao.ProductAttrValueMapper;
import cn.iocoder.mall.product.dataobject.ProductAttrDO;
import cn.iocoder.mall.product.dataobject.ProductAttrValueDO;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.Multimaps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
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

}