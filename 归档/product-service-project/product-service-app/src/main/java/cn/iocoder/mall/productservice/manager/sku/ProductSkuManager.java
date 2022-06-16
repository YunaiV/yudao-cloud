package cn.iocoder.mall.productservice.manager.sku;

import cn.iocoder.common.framework.util.CollectionUtils;
import cn.iocoder.mall.productservice.convert.sku.ProductSkuConvert;
import cn.iocoder.mall.productservice.enums.sku.ProductSkuDetailFieldEnum;
import cn.iocoder.mall.productservice.rpc.sku.dto.ProductSkuListQueryReqDTO;
import cn.iocoder.mall.productservice.rpc.sku.dto.ProductSkuRespDTO;
import cn.iocoder.mall.productservice.service.attr.ProductAttrService;
import cn.iocoder.mall.productservice.service.attr.bo.ProductAttrKeyValueBO;
import cn.iocoder.mall.productservice.service.sku.ProductSkuService;
import cn.iocoder.mall.productservice.service.sku.bo.ProductSkuBO;
import cn.iocoder.mall.productservice.service.spu.ProductSpuService;
import cn.iocoder.mall.productservice.service.spu.bo.ProductSpuBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 商品 SKU Manager
 */
@Service
public class ProductSkuManager {

    @Autowired
    private ProductSkuService productSkuService;
    @Autowired
    private ProductSpuService productSpuService;
    @Autowired
    private ProductAttrService productAttrService;

    /**
     * 获得商品 SKU
     *
     * @param productSkuId 商品 SKU编号
     * @return 商品 SKU
     */
    public ProductSkuRespDTO getProductSku(Integer productSkuId) {
        ProductSkuBO productSkuBO = productSkuService.getProductSku(productSkuId);
        return ProductSkuConvert.INSTANCE.convert(productSkuBO);
    }

    /**
     * 获得商品 SKU 列表
     *
     * @param queryReqDTO 商品 SKU 列表的查询请求 DTO
     * @return 商品 SKU列表
     */
    public List<ProductSkuRespDTO> listProductSkus(ProductSkuListQueryReqDTO queryReqDTO) {
        // 获得商品 SKU 列表
        List<ProductSkuBO> skuBOs = productSkuService.listProductSkus(
                ProductSkuConvert.INSTANCE.convert(queryReqDTO));
        if (CollectionUtils.isEmpty(skuBOs)) {
            return Collections.emptyList();
        }
        // 获得商品 SPU 列表
        List<ProductSpuBO> spuBOs = Collections.emptyList();
        if (queryReqDTO.getFields().contains(ProductSkuDetailFieldEnum.SPU.getField())) {
            spuBOs = productSpuService.listProductSpus(
                    CollectionUtils.convertSet(skuBOs, ProductSkuBO::getSpuId));
        }
        // 获取商品 SKU 的规格数组
        List<ProductAttrKeyValueBO> attrBOs = Collections.emptyList();
        if (queryReqDTO.getFields().contains(ProductSkuDetailFieldEnum.ATTR.getField())) {
            Set<Integer> attrValueIds = new HashSet<>();
            skuBOs.forEach(sku -> attrValueIds.addAll(sku.getAttrValueIds()));
            attrBOs = productAttrService.validProductAttr(attrValueIds, false); // 读取规格时，不考虑规格是否被禁用
        }
        // 拼接最终返回
        return ProductSkuConvert.INSTANCE.convertList(skuBOs, spuBOs, attrBOs);
    }

}
