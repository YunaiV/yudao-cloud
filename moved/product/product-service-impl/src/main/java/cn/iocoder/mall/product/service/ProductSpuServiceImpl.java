package cn.iocoder.mall.product.service;

import cn.iocoder.common.framework.util.CollectionUtil;
import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.util.StringUtil;
import cn.iocoder.mall.mybatis.core.enums.DeletedStatusEnum;
import cn.iocoder.mall.product.api.ProductSpuService;
import cn.iocoder.mall.product.api.bo.*;
import cn.iocoder.mall.product.api.constant.ProductCategoryConstants;
import cn.iocoder.mall.product.api.constant.ProductErrorCodeEnum;
import cn.iocoder.mall.product.api.constant.ProductSpuConstants;
import cn.iocoder.mall.product.api.dto.*;
import cn.iocoder.mall.product.api.message.ProductUpdateMessage;
import cn.iocoder.mall.product.convert.ProductSpuConvert;
import cn.iocoder.mall.product.dao.ProductSkuMapper;
import cn.iocoder.mall.product.dao.ProductSpuMapper;
import cn.iocoder.mall.product.dataobject.ProductCategoryDO;
import cn.iocoder.mall.product.dataobject.ProductSkuDO;
import cn.iocoder.mall.product.dataobject.ProductSpuDO;
import cn.iocoder.mall.product.message.MQStreamProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.*;
import java.util.stream.Collectors;

@Service // 实际上不用添加。添加的原因是，必须 Spring 报错提示
@org.apache.dubbo.config.annotation.Service(validation = "true", version = "${dubbo.provider.ProductSpuService.version}")
public class ProductSpuServiceImpl implements ProductSpuService {

    @Autowired
    private ProductSpuMapper productSpuMapper;
    @Autowired
    private ProductSkuMapper productSkuMapper;

    @Autowired
    private ProductCategoryServiceImpl productCategoryService;
    @Autowired
    private ProductAttrServiceImpl productAttrService;

//    @Override
//    public ProductSpuBO getProductSpuDetail(Integer id) {
//        ProductSpuDO productSpuDO = productSpuMapper.selectById(id);
//        // 转换成 BO
//        return ProductSpuConvert.INSTANCE.convert(productSpuDO);
//    }

    @Override
    public Boolean updateProductSpuSort(Integer adminId, Integer spuId, Integer sort) {
        // 校验 Spu 是否存在
        if (productSpuMapper.selectById(spuId) == null) {
            throw ServiceExceptionUtil.exception(ProductErrorCodeEnum.PRODUCT_SPU_NOT_EXISTS.getCode());
        }
        // 更新排序
        ProductSpuDO updateSpu = new ProductSpuDO().setId(spuId).setSort(sort);
        productSpuMapper.update(updateSpu);
        // 修改成功，发送商品 Topic 消息
        sendProductUpdateMessage(spuId);
        // 返回成功
        return true;
    }

    @Override
    public List<ProductSpuBO> getProductSpuSearchList(ProductSpuSearchListDTO productSpuSearchListDTO) {
        return ProductSpuConvert.INSTANCE.convert(
                productSpuMapper.selectListByNameLikeOrderBySortAsc(productSpuSearchListDTO.getName(), null, null,
                        null, null, null)
        );
    }

    @Override
    public List<ProductSpuBO> getProductSpuList(Collection<Integer> ids) {
        List<ProductSpuDO> spus = productSpuMapper.selectByIds(ids);
        return ProductSpuConvert.INSTANCE.convert(spus);
    }

    @Override
    public ProductSkuBO getProductSku(Integer id) {
        ProductSkuDO sku = productSkuMapper.selectById(id);
        return ProductSpuConvert.INSTANCE.convert4(sku);
    }

    @Override
    public List<ProductSkuDetailBO> getProductSkuDetailList(Collection<Integer> ids) {
        // 查询 SKU 数组
        List<ProductSkuDO> skus = productSkuMapper.selectByIds(ids);
        if (skus.isEmpty()) {
            return Collections.emptyList();
        }
        // 查询 SPU 数组
        List<ProductSpuDO> spus = productSpuMapper.selectByIds(skus.stream().map(ProductSkuDO::getSpuId).collect(Collectors.toSet()));
        if (spus.isEmpty()) {
            return Collections.emptyList();
        }
        // 获得规格
        Set<Integer> productAttrValueIds = new HashSet<>();
        skus.forEach(sku -> productAttrValueIds.addAll(StringUtil.splitToInt(sku.getAttrs(), ",")));
        List<ProductAttrAndValuePairBO> attrAndValuePairList = productAttrService.validProductAttrAndValue(productAttrValueIds,
                false); // 读取规格时，不考虑规格是否被禁用
        // 返回成功
        return ProductSpuConvert.INSTANCE.convert3(skus, spus, attrAndValuePairList);
    }

}
