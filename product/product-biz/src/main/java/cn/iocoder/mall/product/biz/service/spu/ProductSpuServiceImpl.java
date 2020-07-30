package cn.iocoder.mall.product.biz.service.spu;

import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.util.StringUtil;
import cn.iocoder.mall.mybatis.core.enums.DeletedStatusEnum;
import cn.iocoder.mall.product.biz.bo.product.ProductAttrAndValuePairBO;
import cn.iocoder.mall.product.biz.bo.product.ProductSpuDetailBO;
import cn.iocoder.mall.product.biz.convert.sku.ProductSpuConvert;
import cn.iocoder.mall.product.biz.dao.category.ProductCategoryMapper;
import cn.iocoder.mall.product.biz.dao.sku.ProductSkuMapper;
import cn.iocoder.mall.product.biz.dao.sku.ProductSpuMapper;
import cn.iocoder.mall.product.biz.dataobject.category.ProductCategoryDO;
import cn.iocoder.mall.product.biz.dataobject.spu.ProductSkuDO;
import cn.iocoder.mall.product.biz.dataobject.spu.ProductSpuDO;
import cn.iocoder.mall.product.biz.dto.sku.ProductSkuAddOrUpdateDTO;
import cn.iocoder.mall.product.biz.dto.sku.ProductSpuAddDTO;
import cn.iocoder.mall.product.biz.enums.ProductErrorCodeEnum;
import cn.iocoder.mall.product.biz.enums.category.ProductCategoryNodeEnum;
import cn.iocoder.mall.product.biz.enums.spu.ProductSpuConstants;
import cn.iocoder.mall.product.biz.service.attr.ProductAttrService;
import cn.iocoder.mall.product.biz.service.category.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.*;
import java.util.stream.Collectors;

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
    @Autowired
    private ProductCategoryService productCategoryService;

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
