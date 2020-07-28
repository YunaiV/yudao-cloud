package cn.iocoder.mall.productservice.service.sku;

import cn.iocoder.common.framework.enums.CommonStatusEnum;
import cn.iocoder.common.framework.util.CollectionUtils;
import cn.iocoder.common.framework.util.StringUtils;
import cn.iocoder.mall.productservice.convert.sku.ProductSkuConvert;
import cn.iocoder.mall.productservice.dal.mysql.dataobject.spu.ProductSkuDO;
import cn.iocoder.mall.productservice.dal.mysql.mapper.sku.ProductSkuMapper;
import cn.iocoder.mall.productservice.service.sku.bo.ProductSkuCreateOrUpdateBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductSkuService {

    @Autowired
    private ProductSkuMapper productSkuMapper;

    public void createProductSkus(Integer spuId, List<ProductSkuCreateOrUpdateBO> createSkuBOs) {
        List<ProductSkuDO> skus = ProductSkuConvert.INSTANCE.convertList(createSkuBOs);
        skus.forEach(sku -> {
            sku.setStatus(CommonStatusEnum.ENABLE.getValue());
            sku.setSpuId(spuId);
        });
        productSkuMapper.insertList(skus);
    }

    public void updateProductSkus(Integer spuId, List<ProductSkuCreateOrUpdateBO> skuUpdateBOs) {
        List<ProductSkuDO> existsSkus = productSkuMapper.selectListBySpuIdAndStatus(spuId,
                CommonStatusEnum.ENABLE.getValue());
        List<ProductSkuDO> insertSkus = new ArrayList<>(); // 1、找不到，进行插入
        List<Integer> deleteSkus = new ArrayList<>(); // 2、多余的，删除
        List<ProductSkuDO> updateSkus = new ArrayList<>(); // 3、找的到，进行更新。
        for (ProductSkuCreateOrUpdateBO skuUpdateDTO : skuUpdateBOs) {
            ProductSkuDO existsSku = findProductSku(skuUpdateDTO.getAttrValueIds(), existsSkus);
            // 3、找的到，进行更新。
            if (existsSku != null) {
                // 移除
                existsSkus.remove(existsSku);
                // 创建 ProductSkuDO
                updateSkus.add(ProductSkuConvert.INSTANCE.convert(skuUpdateDTO).setId(existsSku.getId()));
                continue;
            }
            // 1、找不到，进行插入
            ProductSkuDO insertSku = ProductSkuConvert.INSTANCE.convert(skuUpdateDTO)
                    .setSpuId(spuId).setStatus(CommonStatusEnum.ENABLE.getValue());
            insertSkus.add(insertSku);
        }
        // 2、多余的，删除
        if (!existsSkus.isEmpty()) {
            deleteSkus.addAll(existsSkus.stream().map(ProductSkuDO::getId).collect(Collectors.toList()));
        }
        // 执行修改 Sku
        if (!insertSkus.isEmpty()) {
            productSkuMapper.insertList(insertSkus);
        }
        if (!updateSkus.isEmpty()) {
            updateSkus.forEach(productSkuDO -> productSkuMapper.updateById(productSkuDO));
        }
        if (!deleteSkus.isEmpty()) {
            productSkuMapper.deleteBatchIds(deleteSkus);
        }
    }

    /**
     * 获得 sku 数组中，指定规格的 sku
     *
     * @param attrValueIds 指定规格 Value 的编号数组
     * @param skus sku 数组
     * @return 符合条件的 sku
     */
    private ProductSkuDO findProductSku(Collection<Integer> attrValueIds, List<ProductSkuDO> skus) {
        if (CollectionUtils.isEmpty(skus)) {
            return null;
        }
        // 创建成 Set ，方便后面比较
        attrValueIds = new HashSet<>(attrValueIds);
        for (ProductSkuDO sku : skus) {
            Set<Integer> skuAttrValueIds = StringUtils.split(sku.getAttrs(), ",")
                    .stream().map(Integer::parseInt).collect(Collectors.toSet());
            if (attrValueIds.equals(skuAttrValueIds)) {
                return sku;
            }
        }
        return null;
    }

}
