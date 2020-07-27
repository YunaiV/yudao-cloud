package cn.iocoder.mall.productservice.service.sku;

import cn.iocoder.common.framework.enums.CommonStatusEnum;
import cn.iocoder.common.framework.exception.util.ServiceExceptionUtil;
import cn.iocoder.mall.productservice.convert.sku.ProductSkuConvert;
import cn.iocoder.mall.productservice.dal.mysql.dataobject.spu.ProductSkuDO;
import cn.iocoder.mall.productservice.dal.mysql.mapper.sku.ProductSkuMapper;
import cn.iocoder.mall.productservice.service.attr.bo.ProductAttrKeyValueBO;
import cn.iocoder.mall.productservice.service.sku.bo.ProductSkuCreateOrUpdateBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static cn.iocoder.mall.productservice.enums.ProductErrorCodeConstants.*;

@Service
public class ProductSkuService {

    @Autowired
    private ProductSkuMapper productSkuMapper;

    public void createProductSkus(Integer spuId, List<ProductSkuCreateOrUpdateBO> createBOs) {
        List<ProductSkuDO> skus = ProductSkuConvert.INSTANCE.convertList(createBOs);
        skus.forEach(sku -> {
            sku.setStatus(CommonStatusEnum.ENABLE.getValue());
            sku.setSpuId(spuId);
        });
        productSkuMapper.insertList(skus);
    }

    public void updateProductSkus(Integer spuId, List<ProductSkuCreateOrUpdateBO> createBOs) {

    }

    /**
     * 校验 sku 是否合法
     *
     * @param skuBOs 商品 SKU 添加信息
     * @param attrKeyValueBOs 商品规格明细数组
     */
    public void validProductSku(List<ProductSkuCreateOrUpdateBO> skuBOs, List<ProductAttrKeyValueBO> attrKeyValueBOs) {
        // 创建 ProductAttrDetailBO 的映射。其中，KEY 为 ProductAttrDetailBO.attrValueId ，即规格值的编号
        Map<Integer, ProductAttrKeyValueBO> productAttrDetailBOMap = attrKeyValueBOs.stream().collect(
                Collectors.toMap(ProductAttrKeyValueBO::getAttrValueId, productAttrDetailBO -> productAttrDetailBO));
        // 1. 先校验，一个 Sku 下，没有重复的规格。校验方式是，遍历每个 Sku ，看看是否有重复的规格 attrId
        for (ProductSkuCreateOrUpdateBO sku : skuBOs) {
            Set<Integer> attrIds = sku.getAttrValueIds().stream().map(attrValueId -> productAttrDetailBOMap.get(attrValueId).getAttrKeyId())
                    .collect(Collectors.toSet());
            if (attrIds.size() != sku.getAttrValueIds().size()) {
                throw ServiceExceptionUtil.exception(PRODUCT_SKU_ATTR_CANT_NOT_DUPLICATE);
            }
        }
        // 2. 再校验，每个 Sku 的规格值的数量，是一致的。
        int attrValueIdsSize = skuBOs.get(0).getAttrValueIds().size();
        for (int i = 1; i < skuBOs.size(); i++) {
            if (attrValueIdsSize != skuBOs.get(i).getAttrValueIds().size()) {
                throw ServiceExceptionUtil.exception(PRODUCT_SPU_ATTR_NUMBERS_MUST_BE_EQUALS);
            }
        }
        // 3. 最后校验，每个 Sku 之间不是重复的
        Set<Set<Integer>> skuAttrValues = new HashSet<>(); // 每个元素，都是一个 Sku 的 attrValueId 集合。这样，通过最外层的 Set ，判断是否有重复的.
        for (ProductSkuCreateOrUpdateBO sku : skuBOs) {
            if (!skuAttrValues.add(new HashSet<>(sku.getAttrValueIds()))) { // 添加失败，说明重复
                throw ServiceExceptionUtil.exception(PRODUCT_SPU_SKU_NOT_DUPLICATE);
            }
        }
    }

}
