package cn.iocoder.mall.product.biz.service.spu;

import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.util.StringUtil;
import cn.iocoder.mall.mybatis.enums.DeletedStatusEnum;
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

    public ProductSpuDetailBO addProductSpu(Integer adminId, ProductSpuAddDTO productSpuAddDTO) {
        ProductSpuDetailBO productSpuDetailBO = addProductSpu0(adminId, productSpuAddDTO);
        // 如果新增生成，发送创建商品 Topic 消息
        // TODO 芋艿，先不考虑事务的问题。等后面的 fescar 一起搞
//        sendProductUpdateMessage(productSpuDetailBO.getId());
        // 返回成功
        return productSpuDetailBO;
    }

    @SuppressWarnings("Duplicates")
    @Transactional
    public ProductSpuDetailBO addProductSpu0(Integer adminId, ProductSpuAddDTO productSpuAddDTO) {
        // 校验商品分类分类存在
        ProductCategoryDO category = productCategoryService.validProductCategory(productSpuAddDTO.getCid());
        if (ProductCategoryNodeEnum.ROOT.getId().equals(category.getPid())) {
            // 商品只能添加到二级分类下
            throw ServiceExceptionUtil.exception(ProductErrorCodeEnum.PRODUCT_SPU_CATEGORY_MUST_BE_LEVEL2.getCode());
        }
        // 校验规格是否存在
        Set<Integer> productAttrValueIds = new HashSet<>();
        productSpuAddDTO.getSkus().forEach(productSkuAddDTO -> productAttrValueIds.addAll(productSkuAddDTO.getAttrs()));
        // 读取规格时，需要考虑规格是否被禁用
        List<ProductAttrAndValuePairBO> attrAndValuePairList = productAttrService.validProductAttrAndValue(productAttrValueIds, true);
        // 保存 Spu
        ProductSpuDO spu = ProductSpuConvert.INSTANCE.convertToSpuDO(productSpuAddDTO)
                .setPicUrls(StringUtil.join(productSpuAddDTO.getPicUrls(), ","))
                .setSort(0); // 排序为 0
        spu.setCreateTime(new Date());
        spu.setDeleted(DeletedStatusEnum.DELETED_NO.getValue());
        // 初始化 sku 相关信息到 spu 中
        initSpuFromSkus(spu, productSpuAddDTO.getSkus());
        productSpuMapper.insert(spu);
        // 保存 Sku
        List<ProductSkuDO> skus = productSpuAddDTO.getSkus().stream().map(productSkuAddDTO -> {
            ProductSkuDO sku = ProductSpuConvert.INSTANCE.convertToSkuDO(productSkuAddDTO)
                    .setSpuId(spu.getId())
                    .setStatus(ProductSpuConstants.SKU_STATUS_ENABLE)
                    .setAttrs(StringUtil.join(productSkuAddDTO.getAttrs(), ","));
            sku.setCreateTime(new Date());
            sku.setDeleted(DeletedStatusEnum.DELETED_NO.getValue());
            return sku;
        }).collect(Collectors.toList());
        // 校验 Sku 规格
        validProductSku(productSpuAddDTO.getSkus(), attrAndValuePairList);
        // 插入 SKU 到数据库
        productSkuMapper.insertList(skus);
        // 返回成功
        return ProductSpuConvert.INSTANCE.convert2(spu, skus, attrAndValuePairList, category);
    }

    /**
     * 根据 sku 数组，计算相关的字段到 spu 中。
     *
     * @param spu  spu
     * @param skus sku 数组
     */
    private void initSpuFromSkus(ProductSpuDO spu, List<ProductSkuAddOrUpdateDTO> skus) {
        assert skus.size() > 0; // 写个断言，避免下面警告
        spu.setPrice(skus.stream().min(Comparator.comparing(ProductSkuAddOrUpdateDTO::getPrice)).get().getPrice()); // 求最小价格
        spu.setQuantity(skus.stream().mapToInt(ProductSkuAddOrUpdateDTO::getQuantity).sum()); // 求库存之和
    }

//    private boolean sendProductUpdateMessage(Integer id) {
//        // 创建 Message 对象
//        ProductUpdateMessage message = new ProductUpdateMessage().setId(id);
//        // 创建 Spring Message 对象
//        Message<ProductUpdateMessage> springMessage = MessageBuilder.withPayload(message)
//                .build();
//        // 发送消息
//        return mqStreamProducer.productUpdateOutput().send(springMessage);
//    }

    /**
     * 校验 sku 是否合法
     *
     * @param productSkuAddDTOs    sku 添加或修改信息
     * @param productAttrDetailBOs 商品规格明细数组
     */
    private void validProductSku(List<ProductSkuAddOrUpdateDTO> productSkuAddDTOs, List<ProductAttrAndValuePairBO> productAttrDetailBOs) {
        // 创建 ProductAttrDetailBO 的映射。其中，KEY 为 ProductAttrDetailBO.attrValueId ，即规格值的编号
        Map<Integer, ProductAttrAndValuePairBO> productAttrDetailBOMap = productAttrDetailBOs.stream().collect(
                Collectors.toMap(ProductAttrAndValuePairBO::getAttrValueId, productAttrDetailBO -> productAttrDetailBO));
        // 1. 先校验，一个 Sku 下，没有重复的规格。校验方式是，遍历每个 Sku ，看看是否有重复的规格 attrId
        for (ProductSkuAddOrUpdateDTO sku : productSkuAddDTOs) {
            Set<Integer> attrIds = sku.getAttrs().stream().map(attrValueId -> productAttrDetailBOMap.get(attrValueId).getAttrId())
                    .collect(Collectors.toSet());
            if (attrIds.size() != sku.getAttrs().size()) {
                throw ServiceExceptionUtil.exception(ProductErrorCodeEnum.PRODUCT_SKU_ATTR_CANT_NOT_DUPLICATE.getCode());
            }
        }
        // 2. 再校验，每个 Sku 的规格值的数量，是一致的。
        int attrSize = productSkuAddDTOs.get(0).getAttrs().size();
        for (int i = 1; i < productSkuAddDTOs.size(); i++) {
            if (attrSize != productSkuAddDTOs.get(i).getAttrs().size()) {
                throw ServiceExceptionUtil.exception(ProductErrorCodeEnum.PRODUCT_SPU_ATTR_NUMBERS_MUST_BE_EQUALS.getCode());
            }
        }
        // 3. 最后校验，每个 Sku 之间不是重复的
        Set<Set<Integer>> skuAttrValues = new HashSet<>(); // 每个元素，都是一个 Sku 的 attrValueId 集合。这样，通过最外层的 Set ，判断是否有重复的.
        for (ProductSkuAddOrUpdateDTO sku : productSkuAddDTOs) {
            if (!skuAttrValues.add(new HashSet<>(sku.getAttrs()))) { // 添加失败，说明重复
                throw ServiceExceptionUtil.exception(ProductErrorCodeEnum.PRODUCT_SPU_SKU__NOT_DUPLICATE.getCode());
            }
        }
    }
}
