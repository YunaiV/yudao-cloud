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

    @Autowired
    private MQStreamProducer mqStreamProducer;

//    @Override
//    public ProductSpuBO getProductSpuDetail(Integer id) {
//        ProductSpuDO productSpuDO = productSpuMapper.selectById(id);
//        // 转换成 BO
//        return ProductSpuConvert.INSTANCE.convert(productSpuDO);
//    }

    @Override
    public ProductSpuDetailBO getProductSpuDetail(Integer id) {
        // 校验商品 spu 存在
        ProductSpuDO spu = productSpuMapper.selectById(id);
        if (spu == null) {
            throw ServiceExceptionUtil.exception(ProductErrorCodeEnum.PRODUCT_SPU_NOT_EXISTS.getCode());
        }
        // 获得商品分类分类
        ProductCategoryDO category = productCategoryService.getProductCategory(spu.getCid());
        Assert.notNull(category, String.format("分类编号(%d) 对应", spu.getCid()));
        // 获得商品 sku 数组
        List<ProductSkuDO> skus = productSkuMapper.selectListBySpuIdAndStatus(id, ProductSpuConstants.SKU_STATUS_ENABLE);
        // 获得规格
        Set<Integer> productAttrValueIds = new HashSet<>();
        skus.forEach(sku -> productAttrValueIds.addAll(StringUtil.splitToInt(sku.getAttrs(), ",")));
        List<ProductAttrAndValuePairBO> attrAndValuePairList = productAttrService.validProductAttrAndValue(productAttrValueIds,
                false); // 读取规格时，不考虑规格是否被禁用
        // 返回成功
        return ProductSpuConvert.INSTANCE.convert2(spu, skus, attrAndValuePairList, category);
    }

    @Override
    public List<ProductSpuDetailBO> getProductSpuDetailListForSync(Integer lastId, Integer limit) {
        // TODO 芋艿，这里目前是一个一个进行计算，后续需要优化下
        // 查询下一批商品编号集合
        List<Integer> spuIds = productSpuMapper.selectIdListByIdGt(lastId, limit);
        if (spuIds.isEmpty()) {
            return Collections.emptyList();
        }
        // 查询每个商品明细
        List<ProductSpuDetailBO> spus = spuIds.stream().map(id -> getProductSpuDetail(id)).collect(Collectors.toList()); // TODO 芋艿，此处相当于是 N 个查询，后续要优化。
        return spus;
    }

    @Override
    public ProductSpuDetailBO addProductSpu(Integer adminId, ProductSpuAddDTO productSpuAddDTO) {
        ProductSpuDetailBO productSpuDetailBO = addProductSpu0(adminId, productSpuAddDTO);
        // 如果新增生成，发送创建商品 Topic 消息
        // TODO 芋艿，先不考虑事务的问题。等后面的 fescar 一起搞
        sendProductUpdateMessage(productSpuDetailBO.getId());
        // 返回成功
        return productSpuDetailBO;
    }

    @SuppressWarnings("Duplicates")
    @Transactional
    public ProductSpuDetailBO addProductSpu0(Integer adminId, ProductSpuAddDTO productSpuAddDTO) {
        // 校验商品分类分类存在
        ProductCategoryDO category = productCategoryService.validProductCategory(productSpuAddDTO.getCid());
        if (ProductCategoryConstants.PID_ROOT.equals(category.getPid())) { // 商品只能添加到二级分类下
            throw ServiceExceptionUtil.exception(ProductErrorCodeEnum.PRODUCT_SPU_CATEGORY_MUST_BE_LEVEL2.getCode());
        }
        // 校验规格是否存在
        Set<Integer> productAttrValueIds = new HashSet<>();
        productSpuAddDTO.getSkus().forEach(productSkuAddDTO -> productAttrValueIds.addAll(productSkuAddDTO.getAttrs()));
        List<ProductAttrAndValuePairBO> attrAndValuePairList = productAttrService.validProductAttrAndValue(productAttrValueIds
            , true); // 读取规格时，需要考虑规格是否被禁用
        // 保存 Spu
        ProductSpuDO spu = ProductSpuConvert.INSTANCE.convert(productSpuAddDTO)
                .setPicUrls(StringUtil.join(productSpuAddDTO.getPicUrls(), ","))
                .setSort(0); // 排序为 0
        spu.setCreateTime(new Date());
        spu.setDeleted(DeletedStatusEnum.DELETED_NO.getValue());
        initSpuFromSkus(spu, productSpuAddDTO.getSkus()); // 初始化 sku 相关信息到 spu 中
        productSpuMapper.insert(spu);
        // 保存 Sku
        List<ProductSkuDO> skus = productSpuAddDTO.getSkus().stream().map(productSkuAddDTO -> {
            ProductSkuDO sku = ProductSpuConvert.INSTANCE.convert(productSkuAddDTO)
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

    @Override
    public void updateProductSpu(Integer adminId, ProductSpuUpdateDTO productSpuUpdateDTO) {
        // 更新商品
        updateProductSpu0(adminId, productSpuUpdateDTO);
        // TODO 芋艿，先不考虑事务的问题。等后面的 fescar 一起搞
        sendProductUpdateMessage(productSpuUpdateDTO.getId());
    }

    @SuppressWarnings("Duplicates")
    @Transactional
    public void updateProductSpu0(Integer adminId, ProductSpuUpdateDTO productSpuUpdateDTO) {
        // 校验 Spu 是否存在
        if (productSpuMapper.selectById(productSpuUpdateDTO.getId()) == null) {
            throw ServiceExceptionUtil.exception(ProductErrorCodeEnum.PRODUCT_SPU_NOT_EXISTS.getCode());
        }
        // 校验商品分类分类存在
        ProductCategoryDO category = productCategoryService.validProductCategory(productSpuUpdateDTO.getCid());
        if (ProductCategoryConstants.PID_ROOT.equals(category.getPid())) { // 商品只能添加到二级分类下
            throw ServiceExceptionUtil.exception(ProductErrorCodeEnum.PRODUCT_SPU_CATEGORY_MUST_BE_LEVEL2.getCode());
        }
        // 校验规格是否存在
        Set<Integer> productAttrValueIds = new HashSet<>();
        productSpuUpdateDTO.getSkus().forEach(productSkuAddDTO -> productAttrValueIds.addAll(productSkuAddDTO.getAttrs()));
        List<ProductAttrAndValuePairBO> attrAndValuePairList = productAttrService.validProductAttrAndValue(productAttrValueIds,
                true); // 读取规格时，需要考虑规格是否被禁用
        // 校验 Sku 规格
        validProductSku(productSpuUpdateDTO.getSkus(), attrAndValuePairList);
        // 更新 Spu
        ProductSpuDO updateSpu = ProductSpuConvert.INSTANCE.convert(productSpuUpdateDTO)
                .setPicUrls(StringUtil.join(productSpuUpdateDTO.getPicUrls(), ","));
        initSpuFromSkus(updateSpu, productSpuUpdateDTO.getSkus()); // 初始化 sku 相关信息到 spu 中
        productSpuMapper.update(updateSpu);
        // 修改 Sku
        List<ProductSkuDO> existsSkus = productSkuMapper.selectListBySpuIdAndStatus(productSpuUpdateDTO.getId(), ProductSpuConstants.SKU_STATUS_ENABLE);
        List<ProductSkuDO> insertSkus = new ArrayList<>(0); // 1、找不到，进行插入
        List<Integer> deleteSkus = new ArrayList<>(0); // 2、多余的，删除
        List<ProductSkuDO> updateSkus = new ArrayList<>(0); // 3、找的到，进行更新。
        for (ProductSkuAddOrUpdateDTO skuUpdateDTO : productSpuUpdateDTO.getSkus()) {
            ProductSkuDO existsSku = findProductSku(skuUpdateDTO.getAttrs(), existsSkus);
            // 3、找的到，进行更新。
            if (existsSku != null) {
                // 移除
                existsSkus.remove(existsSku);
                // 创建 ProductSkuDO
                updateSkus.add(ProductSpuConvert.INSTANCE.convert(skuUpdateDTO).setId(existsSku.getId()));
                continue;
            }
            // 1、找不到，进行插入
            ProductSkuDO insertSku = ProductSpuConvert.INSTANCE.convert(skuUpdateDTO)
                    .setSpuId(productSpuUpdateDTO.getId()).setStatus(ProductSpuConstants.SKU_STATUS_ENABLE).setAttrs(StringUtil.join(skuUpdateDTO.getAttrs(), ","));
            insertSku.setCreateTime(new Date());
            insertSku.setDeleted(DeletedStatusEnum.DELETED_NO.getValue());
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
            updateSkus.forEach(productSkuDO -> productSkuMapper.update(productSkuDO));
        }
        if (!deleteSkus.isEmpty()) {
            productSkuMapper.updateToDeleted(deleteSkus);
        }
    }

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
    public ProductSpuPageBO getProductSpuPage(ProductSpuPageDTO productSpuPageDTO) {
        ProductSpuPageBO productSpuPage = new ProductSpuPageBO();
        // 查询分页数据
        int offset = (productSpuPageDTO.getPageNo() - 1) * productSpuPageDTO.getPageSize();
        productSpuPage.setList(ProductSpuConvert.INSTANCE.convert(productSpuMapper.selectListByNameLikeOrderBySortAsc(
                productSpuPageDTO.getName(), productSpuPageDTO.getCid(), productSpuPageDTO.getHasQuantity(), productSpuPageDTO.getVisible(),
            offset, productSpuPageDTO.getPageSize())));
        // 查询分页总数
        productSpuPage.setTotal(productSpuMapper.selectCountByNameLike(productSpuPageDTO.getName(), productSpuPageDTO.getCid(), productSpuPageDTO.getHasQuantity(),
                productSpuPageDTO.getVisible()));
        // 返回结果
        return productSpuPage;
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

    /**
     * 校验 sku 是否合法
     *
     * @param productSkuAddDTOs sku 添加或修改信息
     * @param productAttrDetailBOs 商品规格明细数组
     * @return 是否校验通过
     */
    private Boolean validProductSku(List<ProductSkuAddOrUpdateDTO> productSkuAddDTOs, List<ProductAttrAndValuePairBO> productAttrDetailBOs) {
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
        // 校验通过
        return true;
    }

    /**
     * 获得 sku 数组中，指定规格的 sku
     *
     * @param attrs 指定规格
     * @param skus sku 数组
     * @return 符合条件的 sku
     */
    private ProductSkuDO findProductSku(Collection<Integer> attrs, List<ProductSkuDO> skus) {
        if (CollectionUtil.isEmpty(skus)) {
            return null;
        }
        // 创建成 Set ，方便后面比较
        attrs = new HashSet<>(attrs);
        for (ProductSkuDO sku : skus) {
            Set<Integer> skuAttrs = StringUtil.split(sku.getAttrs(), ",").stream().map(Integer::parseInt).collect(Collectors.toSet());
            if (attrs.equals(skuAttrs)) {
                return sku;
            }
        }
        return null;
    }

    /**
     * 根据 sku 数组，计算相关的字段到 spu 中。
     *
     * @param spu spu
     * @param skus sku 数组
     */
    private void initSpuFromSkus(ProductSpuDO spu, List<ProductSkuAddOrUpdateDTO> skus) {
        assert skus.size() > 0; // 写个断言，避免下面警告
        spu.setPrice(skus.stream().min(Comparator.comparing(ProductSkuAddOrUpdateDTO::getPrice)).get().getPrice()); // 求最小价格
        spu.setQuantity(skus.stream().mapToInt(ProductSkuAddOrUpdateDTO::getQuantity).sum()); // 求库存之和
    }

    private boolean sendProductUpdateMessage(Integer id) {
        // 创建 Message 对象
        ProductUpdateMessage message = new ProductUpdateMessage().setId(id);
        // 创建 Spring Message 对象
        Message<ProductUpdateMessage> springMessage = MessageBuilder.withPayload(message)
                .build();
        // 发送消息
        return mqStreamProducer.productUpdateOutput().send(springMessage);
    }

}
