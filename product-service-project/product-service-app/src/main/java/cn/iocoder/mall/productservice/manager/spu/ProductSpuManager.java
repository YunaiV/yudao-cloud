package cn.iocoder.mall.productservice.manager.spu;

import cn.iocoder.common.framework.exception.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.productservice.convert.spu.ProductSpuConvert;
import cn.iocoder.mall.productservice.enums.category.ProductCategoryIdEnum;
import cn.iocoder.mall.productservice.rpc.spu.dto.ProductSpuAndSkuCreateReqDTO;
import cn.iocoder.mall.productservice.rpc.spu.dto.ProductSpuPageReqDTO;
import cn.iocoder.mall.productservice.rpc.spu.dto.ProductSpuRespDTO;
import cn.iocoder.mall.productservice.rpc.spu.dto.ProductSpuUpdateReqDTO;
import cn.iocoder.mall.productservice.service.attr.ProductAttrService;
import cn.iocoder.mall.productservice.service.attr.bo.ProductAttrKeyValueBO;
import cn.iocoder.mall.productservice.service.category.ProductCategoryService;
import cn.iocoder.mall.productservice.service.category.bo.ProductCategoryBO;
import cn.iocoder.mall.productservice.service.sku.ProductSkuService;
import cn.iocoder.mall.productservice.service.sku.bo.ProductSkuCreateOrUpdateBO;
import cn.iocoder.mall.productservice.service.spu.ProductSpuService;
import cn.iocoder.mall.productservice.service.spu.bo.ProductSpuBO;
import cn.iocoder.mall.productservice.service.spu.bo.ProductSpuCreateBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static cn.iocoder.mall.productservice.enums.ProductErrorCodeConstants.PRODUCT_CATEGORY_NOT_EXISTS;
import static cn.iocoder.mall.productservice.enums.ProductErrorCodeConstants.PRODUCT_SPU_CATEGORY_MUST_BE_LEVEL2;

/**
* 商品 SPU Manager
*/
@Service
public class ProductSpuManager {

    @Autowired
    private ProductSpuService productSpuService;
    @Autowired
    private ProductSkuService productSkuService;
    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private ProductAttrService productAttrService;

    /**
    * 创建商品 SPU 和 SKU
    *
    * @param createDTO 创建商品 SPU 和 SKU DTO
    * @return 商品 SPU
    */
    @Transactional
    public Integer createProductSpu(ProductSpuAndSkuCreateReqDTO createDTO) {
        // 校验商品分类分类存在
        ProductCategoryBO categoryBO = productCategoryService.getProductCategory(createDTO.getCid());
        if (categoryBO == null) {
            // 不存在
            throw ServiceExceptionUtil.exception(PRODUCT_CATEGORY_NOT_EXISTS);
        }
        if (ProductCategoryIdEnum.ROOT.getId().equals(categoryBO.getPid())) {
            // 商品只能添加到二级分类下
            throw ServiceExceptionUtil.exception(PRODUCT_SPU_CATEGORY_MUST_BE_LEVEL2);
        }
        // 校验规格是否存在
        Set<Integer> attrValueIds = new HashSet<>();
        createDTO.getSkus().forEach(productSkuAddDTO -> attrValueIds.addAll(productSkuAddDTO.getAttrValueIds()));
        List<ProductAttrKeyValueBO> attrKeyValueBOs = productAttrService.validProductAttr(attrValueIds, true);
        // 创建商品 SKU 对象，并进行校验
        List<ProductSkuCreateOrUpdateBO> skus = ProductSpuConvert.INSTANCE.convert(createDTO.getSkus());
        productSkuService.validProductSku(skus, attrKeyValueBOs);
        // 插入商品 SPU 记录
        ProductSpuCreateBO spuCreateBO = ProductSpuConvert.INSTANCE.convert(createDTO).setSort(0);
        spuCreateBO.setPrice(skus.stream().min(Comparator.comparing(ProductSkuCreateOrUpdateBO::getPrice)).get().getPrice()); // 求最小价格
        spuCreateBO.setQuantity(skus.stream().mapToInt(ProductSkuCreateOrUpdateBO::getQuantity).sum()); // 求库存之和
        ProductSpuBO spuBO = productSpuService.createProductSpu(spuCreateBO);
        // 从插入商品 SKU 记录
        productSkuService.createProductSkus(spuBO.getId(), skus);
        return spuBO.getId();
    }

    /**
    * 更新商品 SPU
    *
    * @param updateDTO 更新商品 SPU DTO
    */
    public void updateProductSpu(ProductSpuUpdateReqDTO updateDTO) {
        productSpuService.updateProductSpu(ProductSpuConvert.INSTANCE.convert(updateDTO));
    }

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
    public List<ProductSpuRespDTO> listProductSpus(List<Integer> productSpuIds) {
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

}
