package cn.iocoder.mall.product.service;

import cn.iocoder.common.framework.dataobject.BaseDO;
import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.util.StringUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.product.api.ProductSpuService;
import cn.iocoder.mall.product.api.bo.ProductAttrDetailBO;
import cn.iocoder.mall.product.api.bo.ProductSpuBO;
import cn.iocoder.mall.product.api.bo.ProductSpuDetailBO;
import cn.iocoder.mall.product.api.constant.ProductErrorCodeEnum;
import cn.iocoder.mall.product.api.constant.ProductSpuConstants;
import cn.iocoder.mall.product.api.dto.ProductSkuAddDTO;
import cn.iocoder.mall.product.api.dto.ProductSpuAddDTO;
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

    @Override
    public ProductSpuBO getProductSpu(Integer id) {
        ProductSpuDO productSpuDO = productSpuMapper.selectById(id);
        // 转换成 BO
        return ProductSpuConvert.INSTANCE.convert(productSpuDO);
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
        CommonResult<List<ProductAttrDetailBO>> validAttrResult = productAttrService.validProductAttrAndValue(productAttrValueIds);
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
    public CommonResult<Boolean> updateProductSpu(Integer adminId, ProductSpuUpdateDTO productSpuUpdateDTO) {
        // 校验商品分类分类存在
        CommonResult<ProductCategoryDO> validCategoryResult = productCategoryService.validProductCategory(productSpuUpdateDTO.getCid());
        if (validCategoryResult.isError()) {
            return CommonResult.error(validCategoryResult);
        }
        // 校验规格是否存在
        Set<Integer> productAttrValueIds = new HashSet<>();
        productSpuUpdateDTO.getSkus().forEach(productSkuAddDTO -> productAttrValueIds.addAll(productSkuAddDTO.getAttrs()));
        CommonResult<List<ProductAttrDetailBO>> validAttrResult = productAttrService.validProductAttrAndValue(productAttrValueIds);
        if (validAttrResult.isError()) {
            return CommonResult.error(validAttrResult);
        }
        // 更新 Spu
        ProductSpuDO updateSpu = ProductSpuConvert.INSTANCE.convert(productSpuUpdateDTO)
                .setPicUrls(StringUtil.join(productSpuUpdateDTO.getPicUrls(), ","));
        productSpuMapper.update(updateSpu);
        // 校验 Sku 规格
        return CommonResult.success(true);
    }

    private CommonResult<Boolean> validProductSku(List<ProductSkuAddDTO> productSkuAddDTOs, List<ProductAttrDetailBO> productAttrDetailBOs) {
        // 创建 ProductAttrDetailBO 的映射。其中，KEY 为 ProductAttrDetailBO.attrValueId ，即规格值的编号
        Map<Integer, ProductAttrDetailBO> productAttrDetailBOMap = productAttrDetailBOs.stream().collect(
                Collectors.toMap(ProductAttrDetailBO::getAttrValueId, productAttrDetailBO -> productAttrDetailBO));
        // 1. 先校验，一个 Sku 下，没有重复的规格。校验方式是，遍历每个 Sku ，看看是否有重复的规格 attrId
        for (ProductSkuAddDTO sku : productSkuAddDTOs) {
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
        for (ProductSkuAddDTO sku : productSkuAddDTOs) {
            if (!skuAttrValues.add(new HashSet<>(sku.getAttrs()))) { // 添加失败，说明重复
                return ServiceExceptionUtil.error(ProductErrorCodeEnum.PRODUCT_SPU_SKU__NOT_DUPLICATE.getCode());
            }
        }
        // 校验通过
        return CommonResult.success(true);
    }

}