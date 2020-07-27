package cn.iocoder.mall.productservice.service.attr;

import cn.iocoder.common.framework.enums.CommonStatusEnum;
import cn.iocoder.common.framework.exception.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.util.CollectionUtils;
import cn.iocoder.mall.productservice.dal.mysql.dataobject.attr.ProductAttrKeyDO;
import cn.iocoder.mall.productservice.dal.mysql.dataobject.attr.ProductAttrValueDO;
import cn.iocoder.mall.productservice.dal.mysql.mapper.attr.ProductAttrKeyMapper;
import cn.iocoder.mall.productservice.dal.mysql.mapper.attr.ProductAttrValueMapper;
import cn.iocoder.mall.productservice.service.attr.bo.ProductAttrKeyValueBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static cn.iocoder.mall.productservice.enums.ProductErrorCodeConstants.PRODUCT_ATTR_KEY_NOT_EXIST;
import static cn.iocoder.mall.productservice.enums.ProductErrorCodeConstants.PRODUCT_ATTR_VALUE_NOT_EXIST;

@Service
public class ProductAttrService {

    @Autowired
    private ProductAttrKeyMapper productAttrKeyMapper;
    @Autowired
    private ProductAttrValueMapper productAttrValueMapper;

    public List<ProductAttrKeyValueBO> validProductAttr(Set<Integer> productAttrValueIds, boolean validStatus) {
        // 首先，校验规格 Value
        List<ProductAttrValueDO> attrValues = productAttrValueMapper.selectBatchIds(productAttrValueIds);
        if (attrValues.size() != productAttrValueIds.size()) {
            throw ServiceExceptionUtil.exception(PRODUCT_ATTR_VALUE_NOT_EXIST);
        }
        if (validStatus) {
            for (ProductAttrValueDO attrValue : attrValues) { // 同时，校验下状态
                if (CommonStatusEnum.DISABLE.getValue().equals(attrValue.getStatus())) {
                    throw ServiceExceptionUtil.exception(PRODUCT_ATTR_VALUE_NOT_EXIST);
                }
            }
        }
        // 然后，校验规 Key
        Set<Integer> attrKeyIds = CollectionUtils.convertSet(attrValues, ProductAttrValueDO::getAttrId);
        List<ProductAttrKeyDO> attrKeys = productAttrKeyMapper.selectBatchIds(attrKeyIds);
        if (attrKeys.size() != attrKeyIds.size()) {
            throw ServiceExceptionUtil.exception(PRODUCT_ATTR_KEY_NOT_EXIST);
        }
        if (validStatus) {
            for (ProductAttrKeyDO attrKey : attrKeys) { // 同时，校验下状态
                if (CommonStatusEnum.DISABLE.getValue().equals(attrKey.getStatus())) {
                    throw ServiceExceptionUtil.exception(PRODUCT_ATTR_KEY_NOT_EXIST);
                }
            }
        }
        // 返回成功
        Map<Integer, ProductAttrKeyDO> attrKeyMap = CollectionUtils.convertMap(attrKeys, ProductAttrKeyDO::getId, attrKeyDO -> attrKeyDO); // ProductAttrDO 的映射，方便查找。
        return attrValues.stream().map(attrValueDO -> new ProductAttrKeyValueBO()
                .setAttrKeyId(attrValueDO.getAttrId()).setAttrKeyName(attrKeyMap.get(attrValueDO.getAttrId()).getName())
                .setAttrValueId(attrValueDO.getId()).setAttrValueName(attrValueDO.getName()))
                .collect(Collectors.toList());
    }

}
