package cn.iocoder.mall.productservice.manager.spu;

import cn.iocoder.common.framework.enums.CommonStatusEnum;
import cn.iocoder.common.framework.exception.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.util.CollectionUtils;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.productservice.convert.spu.ProductSpuConvert;
import cn.iocoder.mall.productservice.enums.category.ProductCategoryIdEnum;
import cn.iocoder.mall.productservice.enums.spu.ProductSpuDetailFieldEnum;
import cn.iocoder.mall.productservice.mq.producer.ProductMQProducer;
import cn.iocoder.mall.productservice.rpc.spu.dto.*;
import cn.iocoder.mall.productservice.service.attr.ProductAttrService;
import cn.iocoder.mall.productservice.service.attr.bo.ProductAttrKeyValueBO;
import cn.iocoder.mall.productservice.service.category.ProductCategoryService;
import cn.iocoder.mall.productservice.service.category.bo.ProductCategoryBO;
import cn.iocoder.mall.productservice.service.sku.ProductSkuService;
import cn.iocoder.mall.productservice.service.sku.bo.ProductSkuBO;
import cn.iocoder.mall.productservice.service.sku.bo.ProductSkuCreateOrUpdateBO;
import cn.iocoder.mall.productservice.service.sku.bo.ProductSkuListQueryBO;
import cn.iocoder.mall.productservice.service.spu.ProductSpuService;
import cn.iocoder.mall.productservice.service.spu.bo.ProductSpuBO;
import cn.iocoder.mall.productservice.service.spu.bo.ProductSpuCreateBO;
import cn.iocoder.mall.productservice.service.spu.bo.ProductSpuUpdateBO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static cn.iocoder.mall.productservice.enums.ProductErrorCodeConstants.*;

/**
* 商品 SPU Manager
*/
@Service
@Slf4j
public class ProductSpuManager {

    @Autowired
    private ProductSpuService productSpuService;
    @Autowired
    private ProductSkuService productSkuService;
    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private ProductAttrService productAttrService;

    @Autowired
    private ProductMQProducer productMQProducer;

    /**
    * 获得商品 SPU
    *
    * @param productSpuId 商品 SPU编号
    * @return 商品 SPU
    */
    public ProductSpuRespDTO getProductSpu(Integer productSpuId) {
        ProductSpuBO productSpuBO = productSpuService.getProductSpu(productSpuId);
        return ProductSpuConvert.INSTANCE.convert(productSpuBO);
    }

    /**
    * 获得商品 SPU列表
    *
    * @param productSpuIds 商品 SPU编号列表
    * @return 商品 SPU列表
    */
    public List<ProductSpuRespDTO> listProductSpus(Collection<Integer> productSpuIds) {
        List<ProductSpuBO> productSpuBOs = productSpuService.listProductSpus(productSpuIds);
        return ProductSpuConvert.INSTANCE.convertList02(productSpuBOs);
    }

    /**
    * 获得商品 SPU分页
    *
    * @param pageDTO 商品 SPU分页查询
    * @return 商品 SPU分页结果
    */
    public PageResult<ProductSpuRespDTO> pageProductSpu(ProductSpuPageReqDTO pageDTO) {
        PageResult<ProductSpuBO> pageResultBO = productSpuService.pageProductSpu(ProductSpuConvert.INSTANCE.convert(pageDTO));
        return ProductSpuConvert.INSTANCE.convertPage(pageResultBO);
    }

    /**
     * 添加或修改商品 SPU 时，校验商品分类是否合法
     *
     * @param cid 商品分类编号
     * @return 商品分类
     */
    private ProductCategoryBO checkProductCategory(Integer cid) {
        ProductCategoryBO categoryBO = productCategoryService.getProductCategory(cid);
        if (categoryBO == null) {
            // 不存在
            throw ServiceExceptionUtil.exception(PRODUCT_CATEGORY_NOT_EXISTS);
        }
        if (ProductCategoryIdEnum.ROOT.getId().equals(categoryBO.getPid())) {
            // 商品只能添加到二级分类下
            throw ServiceExceptionUtil.exception(PRODUCT_SPU_CATEGORY_MUST_BE_LEVEL2);
        }
        return categoryBO;
    }

    /**
     * 顺序获得商品 SPU 编号数组
     *
     * @param lastSpuId 最后一个商品 SPU 编号
     * @param limit 数量
     * @return 商品 SPU 编号数组
     */
    public List<Integer> listProductSpuIds(Integer lastSpuId, Integer limit) {
        return productSpuService.listProductSpuIds(lastSpuId, limit);
    }

    public ProductSpuDetailRespDTO getProductSpuDetail(Integer productSpuId, Collection<String> fields) {
        // 获得商品 SPU 信息
        ProductSpuBO spuBO = productSpuService.getProductSpu(productSpuId);
        if (spuBO == null) {
            throw ServiceExceptionUtil.exception(PRODUCT_SPU_NOT_EXISTS);
        }
        // 获取商品分类
        ProductCategoryBO categoryBO = productCategoryService.getProductCategory(spuBO.getCid());
        if (categoryBO == null) {
            throw ServiceExceptionUtil.exception(PRODUCT_CATEGORY_NOT_EXISTS);
        }
        // 获得商品 SKU 数组
        List<ProductSkuBO> skuBOs = Collections.emptyList();
        if (fields.contains(ProductSpuDetailFieldEnum.SKU.getField())) {
            skuBOs = productSkuService.listProductSkus(new ProductSkuListQueryBO().setProductSpuId(productSpuId)
                .setProductSkuStatus(CommonStatusEnum.ENABLE.getValue()));
        }
        // 获取商品 SKU 的规格数组
        List<ProductAttrKeyValueBO> attrBOs = Collections.emptyList();
        if (fields.contains(ProductSpuDetailFieldEnum.ATTR.getField()) && !CollectionUtils.isEmpty(skuBOs)) {
            Set<Integer> attrValueIds = new HashSet<>();
            skuBOs.forEach(sku -> attrValueIds.addAll(sku.getAttrValueIds()));
            attrBOs = productAttrService.validProductAttr(attrValueIds, false); // 读取规格时，不考虑规格是否被禁用
        }
        // 拼接最终返回
        return ProductSpuConvert.INSTANCE.convert(spuBO, skuBOs, attrBOs, categoryBO);
    }

}
