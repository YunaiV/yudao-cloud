package cn.iocoder.mall.product.biz.service.product.impl;

import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.util.StringUtil;
import cn.iocoder.mall.product.biz.bo.product.ProductAttrAndValuePairBO;
import cn.iocoder.mall.product.biz.bo.product.ProductSpuDetailBO;
import cn.iocoder.mall.product.biz.convert.product.ProductSpuConvert;
import cn.iocoder.mall.product.biz.dao.product.ProductCategoryMapper;
import cn.iocoder.mall.product.biz.dao.product.ProductSkuMapper;
import cn.iocoder.mall.product.biz.dao.product.ProductSpuMapper;
import cn.iocoder.mall.product.biz.dataobject.product.ProductCategoryDO;
import cn.iocoder.mall.product.biz.dataobject.product.ProductSkuDO;
import cn.iocoder.mall.product.biz.dataobject.product.ProductSpuDO;
import cn.iocoder.mall.product.biz.enums.ProductErrorCodeEnum;
import cn.iocoder.mall.product.biz.enums.product.ProductSpuConstants;
import cn.iocoder.mall.product.biz.service.product.ProductAttrService;
import cn.iocoder.mall.product.biz.service.product.ProductSpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProductSpuServiceImpl implements ProductSpuService {

    @Autowired
    private ProductSpuMapper productSpuMapper;
    @Autowired
    private ProductSkuMapper productSkuMapper;
    @Autowired
    private ProductCategoryMapper productCategoryMapper;
    @Autowired
    private ProductAttrService productAttrService;

    @Override
    public ProductSpuDetailBO getProductSpuDetail(Integer spuId) {
        // 校验商品 spu 存在
        ProductSpuDO spu = productSpuMapper.selectById(spuId);
        if (spu == null) {
            throw ServiceExceptionUtil.exception(ProductErrorCodeEnum.PRODUCT_SPU_NOT_EXISTS.getCode());
        }
        // 获得商品分类分类
        ProductCategoryDO category = productCategoryMapper.selectById(spu.getCid());
        Assert.notNull(category, String.format("分类编号(%d) 对应", spu.getCid()));
        // 获得商品 sku 数组
        List<ProductSkuDO> skus = productSkuMapper.selectListBySpuIdAndStatus(spuId, ProductSpuConstants.SKU_STATUS_ENABLE);
        // 获得规格
        Set<Integer> productAttrValueIds = new HashSet<>();
        skus.forEach(sku -> productAttrValueIds.addAll(StringUtil.splitToInt(sku.getAttrs(), ",")));
        // 读取规格时，不考虑规格是否被禁用
        List<ProductAttrAndValuePairBO> attrAndValuePairList = productAttrService.validProductAttrAndValue(productAttrValueIds, false);
        // 返回成功
        return ProductSpuConvert.INSTANCE.convert2(spu, skus, attrAndValuePairList, category);
    }

}
