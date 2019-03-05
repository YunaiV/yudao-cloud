package cn.iocoder.mall.product.service;

import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.product.api.bo.ProductAttrDetailBO;
import cn.iocoder.mall.product.api.constant.ProductAttrConstants;
import cn.iocoder.mall.product.api.constant.ProductErrorCodeEnum;
import cn.iocoder.mall.product.dao.ProductAttrMapper;
import cn.iocoder.mall.product.dao.ProductAttrValueMapper;
import cn.iocoder.mall.product.dataobject.ProductAttrDO;
import cn.iocoder.mall.product.dataobject.ProductAttrValueDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class ProductAttrServiceImpl {

    @Autowired
    private ProductAttrMapper productAttrMapper;
    @Autowired
    private ProductAttrValueMapper productValueMapper;

    public CommonResult<List<ProductAttrDetailBO>> validProductAttrAndValue(Set<Integer> productAttrValueIds) {
        // 首先，校验规格值
        List<ProductAttrValueDO> attrValues = productValueMapper.selectListByIds(productAttrValueIds);
        if (attrValues.size() != productAttrValueIds.size()) {
            return ServiceExceptionUtil.error(ProductErrorCodeEnum.PRODUCT_ATTR_VALUE_NOT_EXIST.getCode());
        }
        for (ProductAttrValueDO attrValue : attrValues) { // 同时，校验下状态
            if (ProductAttrConstants.ATTR_STATUS_DISABLE.equals(attrValue.getStatus())) {
                return ServiceExceptionUtil.error(ProductErrorCodeEnum.PRODUCT_ATTR_VALUE_NOT_EXIST.getCode());
            }
        }
        // 然后，校验规格
        Set<Integer> attrIds = attrValues.stream().map(ProductAttrValueDO::getAttrId).collect(Collectors.toSet());
        List<ProductAttrDO> attrs = productAttrMapper.selectListByIds(attrIds);
        if (attrs.size() != attrIds.size()) {
            return ServiceExceptionUtil.error(ProductErrorCodeEnum.PRODUCT_ATTR_NOT_EXIST.getCode());
        }
        for (ProductAttrDO attr : attrs) { // 同时，校验下状态
            if (ProductAttrConstants.ATTR_VALUE_STATUS_DISABLE.equals(attr.getStatus())) {
                return ServiceExceptionUtil.error(ProductErrorCodeEnum.PRODUCT_ATTR_NOT_EXIST.getCode());
            }
        }
        // 返回成功
        Map<Integer, ProductAttrDO> attrMap = attrs.stream().collect(Collectors.toMap(ProductAttrDO::getId, productAttrDO -> productAttrDO)); // ProductAttrDO 的映射，方便查找。
        List<ProductAttrDetailBO> result = attrValues.stream().map(productAttrValueDO -> new ProductAttrDetailBO()
                .setAttrId(productAttrValueDO.getAttrId()).setAttrName(attrMap.get(productAttrValueDO.getAttrId()).getName())
                .setAttrValueId(productAttrValueDO.getId()).setAttrValueName(productAttrValueDO.getName())).collect(Collectors.toList());
        return CommonResult.success(result);
    }

}