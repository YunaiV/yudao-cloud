package cn.iocoder.mall.product.service;

import cn.iocoder.common.framework.dataobject.BaseDO;
import cn.iocoder.common.framework.util.CollectionUtil;
import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.util.StringUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.product.api.ProductSpuService;
import cn.iocoder.mall.product.api.bo.ProductAttrAndValuePairBO;
import cn.iocoder.mall.product.api.bo.ProductSpuDetailBO;
import cn.iocoder.mall.product.api.bo.ProductSpuPageBO;
import cn.iocoder.mall.product.api.constant.ProductErrorCodeEnum;
import cn.iocoder.mall.product.api.constant.ProductSpuConstants;
import cn.iocoder.mall.product.api.dto.ProductSkuAddOrUpdateDTO;
import cn.iocoder.mall.product.api.dto.ProductSpuAddDTO;
import cn.iocoder.mall.product.api.dto.ProductSpuPageDTO;
import cn.iocoder.mall.product.api.dto.ProductSpuUpdateDTO;
import cn.iocoder.mall.product.convert.ProductSpuConvert;
import cn.iocoder.mall.product.dao.ProductSkuMapper;
import cn.iocoder.mall.product.dao.ProductSpuMapper;
import cn.iocoder.mall.product.dataobject.ProductCategoryDO;
import cn.iocoder.mall.product.dataobject.ProductSkuDO;
import cn.iocoder.mall.product.dataobject.ProductSpuDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service // 实际上不用添加。添加的原因是，必须 Spring 报错提示
@com.alibaba.dubbo.config.annotation.Service(validation = "true")
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
//    public ProductSpuBO getProductSpu(Integer id) {
//        ProductSpuDO productSpuDO = productSpuMapper.selectById(id);
//        // 转换成 BO
//        return ProductSpuConvert.INSTANCE.convert(productSpuDO);
//    }

    @Override
    public CommonResult<ProductSpuDetailBO> getProductSpu(Integer id) {
        // 校验商品 spu 存在
        ProductSpuDO spu = productSpuMapper.selectById(id);
        if (spu == null) {
            return ServiceExceptionUtil.error(ProductErrorCodeEnum.PRODUCT_SPU_NOT_EXISTS.getCode());
        }
        // 获得商品 sku 数组
        List<ProductSkuDO> skus = productSkuMapper.selectListBySpuIdAndStatus(id, ProductSpuConstants.SKU_STATUS_ENABLE);
        // 获得规格
        Set<Integer> productAttrValueIds = new HashSet<>();
        skus.forEach(sku -> productAttrValueIds.addAll(StringUtil.splitToInt(sku.getAttrs(), ",")));
        CommonResult<List<ProductAttrAndValuePairBO>> validAttrResult = productAttrService.validProductAttrAndValue(productAttrValueIds,
                false); // 读取规格时，不考虑规格是否被禁用
        if (validAttrResult.isError()) {
            return CommonResult.error(validAttrResult);
        }
        // 返回成功
        return CommonResult.success(ProductSpuConvert.INSTANCE.convert2(spu, skus, validAttrResult.getData()));
    }

    @SuppressWarnings("Duplicates")
    @Override
    @Transactional
    public CommonResult<ProductSpuDetailBO> addProductSpu(Integer adminId, ProductSpuAddDTO productSpuAddDTO) {
        // 校验商品分类分类存在
        CommonResult<ProductCategoryDO> validCategoryResult = productCategoryService.validProductCategory(productSpuAddDTO.getCid());
        if (validCategoryResult.isError()) {
            return CommonResult.error(validCategoryResult);
        }
        // 校验规格是否存在
        Set<Integer> productAttrValueIds = new HashSet<>();
        productSpuAddDTO.getSkus().forEach(productSkuAddDTO -> productAttrValueIds.addAll(productSkuAddDTO.getAttrs()));
        CommonResult<List<ProductAttrAndValuePairBO>> validAttrResult = productAttrService.validProductAttrAndValue(productAttrValueIds
            , true); // 读取规格时，需要考虑规格是否被禁用
        if (validAttrResult.isError()) {
            return CommonResult.error(validAttrResult);
        }
        // 保存 Spu
        ProductSpuDO spu = ProductSpuConvert.INSTANCE.convert(productSpuAddDTO)
                .setPicUrls(StringUtil.join(productSpuAddDTO.getPicUrls(), ","))
                .setSort(0); // 排序为 0
        spu.setCreateTime(new Date()).setDeleted(BaseDO.DELETED_NO);
        productSpuMapper.insert(spu);
        // 保存 Sku
        List<ProductSkuDO> skus = productSpuAddDTO.getSkus().stream().map(productSkuAddDTO -> {
            ProductSkuDO sku = ProductSpuConvert.INSTANCE.convert(productSkuAddDTO)
                    .setSpuId(spu.getId())
                    .setStatus(ProductSpuConstants.SKU_STATUS_ENABLE)
                    .setAttrs(StringUtil.join(productSkuAddDTO.getAttrs(), ","));
            sku.setCreateTime(new Date()).setDeleted(BaseDO.DELETED_NO);
            return sku;
        }).collect(Collectors.toList());
        // 校验 Sku 规格
        CommonResult<Boolean> validProductSkuResult = validProductSku(productSpuAddDTO.getSkus(), validAttrResult.getData());
        if (validProductSkuResult.isError()) {
            return CommonResult.error(validProductSkuResult);
        }
        productSkuMapper.insertList(skus);
        // 返回成功
        return CommonResult.success(ProductSpuConvert.INSTANCE.convert2(spu, skus, validAttrResult.getData()));
    }

    @SuppressWarnings("Duplicates")
    @Override
    @Transactional
    public CommonResult<Boolean> updateProductSpu(Integer adminId, ProductSpuUpdateDTO productSpuUpdateDTO) {
        // 校验 Spu 是否存在
        if (productSpuMapper.selectById(productSpuUpdateDTO.getId()) == null) {
            return ServiceExceptionUtil.error(ProductErrorCodeEnum.PRODUCT_SPU_NOT_EXISTS.getCode());
        }
        // 校验商品分类分类存在
        CommonResult<ProductCategoryDO> validCategoryResult = productCategoryService.validProductCategory(productSpuUpdateDTO.getCid());
        if (validCategoryResult.isError()) {
            return CommonResult.error(validCategoryResult);
        }
        // 校验规格是否存在
        Set<Integer> productAttrValueIds = new HashSet<>();
        productSpuUpdateDTO.getSkus().forEach(productSkuAddDTO -> productAttrValueIds.addAll(productSkuAddDTO.getAttrs()));
        CommonResult<List<ProductAttrAndValuePairBO>> validAttrResult = productAttrService.validProductAttrAndValue(productAttrValueIds,
                true); // 读取规格时，需要考虑规格是否被禁用
        if (validAttrResult.isError()) {
            return CommonResult.error(validAttrResult);
        }
        // 校验 Sku 规格
        CommonResult<Boolean> validProductSkuResult = validProductSku(productSpuUpdateDTO.getSkus(), validAttrResult.getData());
        if (validProductSkuResult.isError()) {
            return CommonResult.error(validProductSkuResult);
        }
        // 更新 Spu
        ProductSpuDO updateSpu = ProductSpuConvert.INSTANCE.convert(productSpuUpdateDTO)
                .setPicUrls(StringUtil.join(productSpuUpdateDTO.getPicUrls(), ","));
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
            insertSku.setCreateTime(new Date()).setDeleted(BaseDO.DELETED_NO);
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
        return CommonResult.success(true);
    }

    @Override
    public CommonResult<Boolean> updateProductSpuSort(Integer adminId, Integer spuId, Integer sort) {
        // 校验 Spu 是否存在
        if (productSpuMapper.selectById(spuId) == null) {
            return ServiceExceptionUtil.error(ProductErrorCodeEnum.PRODUCT_SPU_NOT_EXISTS.getCode());
        }
        // 更新排序
        ProductSpuDO updateSpu = new ProductSpuDO().setId(spuId).setSort(sort);
        productSpuMapper.update(updateSpu);
        // 返回成功
        return CommonResult.success(true);
    }

    @Override
    public CommonResult<ProductSpuPageBO> getProductSpuPage(ProductSpuPageDTO productSpuPageDTO) {
        ProductSpuPageBO productSpuPage = new ProductSpuPageBO();
        // 查询分页数据
        int offset = productSpuPageDTO.getPageNo() * productSpuPageDTO.getPageSize();
        productSpuPage.setSpus(ProductSpuConvert.INSTANCE.convert(productSpuMapper.selectListByNameLikeOrderBySortAsc(productSpuPageDTO.getName(),
            offset, productSpuPageDTO.getPageSize())));
        // 查询分页总数
        productSpuPage.setCount(productSpuMapper.selectCountByNameLike(productSpuPageDTO.getName()));
        // 返回结果
        return CommonResult.success(productSpuPage);
    }

    private CommonResult<Boolean> validProductSku(List<ProductSkuAddOrUpdateDTO> productSkuAddDTOs, List<ProductAttrAndValuePairBO> productAttrDetailBOs) {
        // 创建 ProductAttrDetailBO 的映射。其中，KEY 为 ProductAttrDetailBO.attrValueId ，即规格值的编号
        Map<Integer, ProductAttrAndValuePairBO> productAttrDetailBOMap = productAttrDetailBOs.stream().collect(
                Collectors.toMap(ProductAttrAndValuePairBO::getAttrValueId, productAttrDetailBO -> productAttrDetailBO));
        // 1. 先校验，一个 Sku 下，没有重复的规格。校验方式是，遍历每个 Sku ，看看是否有重复的规格 attrId
        for (ProductSkuAddOrUpdateDTO sku : productSkuAddDTOs) {
            Set<Integer> attrIds = sku.getAttrs().stream().map(attrValueId -> productAttrDetailBOMap.get(attrValueId).getAttrId()).collect(Collectors.toSet());
            if (attrIds.size() != sku.getAttrs().size()) {
                return ServiceExceptionUtil.error(ProductErrorCodeEnum.PRODUCT_SKU_ATTR_CANT_NOT_DUPLICATE.getCode());
            }
        }
        // 2. 再校验，每个 Sku 的规格值的数量，是一致的。
        int attrSize = productSkuAddDTOs.get(0).getAttrs().size();
        for (int i = 1; i < productSkuAddDTOs.size(); i++) {
            if (attrSize != productSkuAddDTOs.get(i).getAttrs().size()) {
                return ServiceExceptionUtil.error(ProductErrorCodeEnum.PRODUCT_SPU_ATTR_NUMBERS_MUST_BE_EQUALS.getCode());
            }
        }
        // 3. 最后校验，每个 Sku 之间不是重复的
        Set<Set<Integer>> skuAttrValues = new HashSet<>(); // 每个元素，都是一个 Sku 的 attrValueId 集合。这样，通过最外层的 Set ，判断是否有重复的.
        for (ProductSkuAddOrUpdateDTO sku : productSkuAddDTOs) {
            if (!skuAttrValues.add(new HashSet<>(sku.getAttrs()))) { // 添加失败，说明重复
                return ServiceExceptionUtil.error(ProductErrorCodeEnum.PRODUCT_SPU_SKU__NOT_DUPLICATE.getCode());
            }
        }
        // 校验通过
        return CommonResult.success(true);
    }

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

}