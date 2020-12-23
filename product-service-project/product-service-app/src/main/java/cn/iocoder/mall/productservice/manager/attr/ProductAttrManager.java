package cn.iocoder.mall.productservice.manager.attr;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.productservice.convert.attr.ProductAttrConvert;
import cn.iocoder.mall.productservice.rpc.attr.dto.*;
import cn.iocoder.mall.productservice.service.attr.ProductAttrService;
import cn.iocoder.mall.productservice.service.attr.bo.ProductAttrKeyBO;
import cn.iocoder.mall.productservice.service.attr.bo.ProductAttrValueBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品规格 Manager
 */
@Service
public class ProductAttrManager {

    @Autowired
    private ProductAttrService productAttrService;

    /**
     * 创建商品规格键
     *
     * @param createDTO 创建商品规格键 DTO
     * @return 商品规格键
     */
    public Integer createProductAttrKey(ProductAttrKeyCreateReqDTO createDTO) {
        ProductAttrKeyBO productAttrKeyBO = productAttrService.createProductAttrKey(ProductAttrConvert.INSTANCE.convert(createDTO));
        return productAttrKeyBO.getId();
    }

    /**
     * 更新商品规格键
     *
     * @param updateDTO 更新商品规格键 DTO
     */
    public void updateProductAttrKey(ProductAttrKeyUpdateReqDTO updateDTO) {
        productAttrService.updateProductAttrKey(ProductAttrConvert.INSTANCE.convert(updateDTO));
    }

    /**
     * 获得商品规格键
     *
     * @param productAttrKeyId 商品规格键编号
     * @return 商品规格键
     */
    public ProductAttrKeyRespDTO getProductAttrKey(Integer productAttrKeyId) {
        ProductAttrKeyBO productAttrKeyBO = productAttrService.getProductAttrKey(productAttrKeyId);
        return ProductAttrConvert.INSTANCE.convert(productAttrKeyBO);
    }

    /**
     * 获得商品规格键列表
     *
     * @param productAttrKeyIds 商品规格键编号列表
     * @return 商品规格键列表
     */
    public List<ProductAttrKeyRespDTO> listProductAttrKeys(List<Integer> productAttrKeyIds) {
        List<ProductAttrKeyBO> productAttrKeyBOs = productAttrService.listProductAttrKeys(productAttrKeyIds);
        return ProductAttrConvert.INSTANCE.convertList02(productAttrKeyBOs);
    }

    /**
     * 获得商品规格键分页
     *
     * @param pageDTO 商品规格键分页查询
     * @return 商品规格键分页结果
     */
    public PageResult<ProductAttrKeyRespDTO> pageProductAttrKey(ProductAttrKeyPageReqDTO pageDTO) {
            PageResult<ProductAttrKeyBO> pageResultBO = productAttrService.pageProductAttrKey(ProductAttrConvert.INSTANCE.convert(pageDTO));
        return ProductAttrConvert.INSTANCE.convertPage(pageResultBO);
    }

    /**
     * 创建商品规格值
     *
     * @param createDTO 创建商品规格值 DTO
     * @return 商品规格值
     */
    public Integer createProductAttrValue(ProductAttrValueCreateReqDTO createDTO) {
        ProductAttrValueBO productAttrValueBO = productAttrService.createProductAttrValue(ProductAttrConvert.INSTANCE.convert(createDTO));
        return productAttrValueBO.getId();
    }

    /**
     * 更新商品规格值
     *
     * @param updateDTO 更新商品规格值 DTO
     */
    public void updateProductAttrValue(ProductAttrValueUpdateReqDTO updateDTO) {
        productAttrService.updateProductAttrValue(ProductAttrConvert.INSTANCE.convert(updateDTO));
    }

    /**
     * 获得商品规格值
     *
     * @param productAttrValueId 商品规格值编号
     * @return 商品规格值
     */
    public ProductAttrValueRespDTO getProductAttrValue(Integer productAttrValueId) {
        ProductAttrValueBO productAttrValueBO = productAttrService.getProductAttrValue(productAttrValueId);
        return ProductAttrConvert.INSTANCE.convert(productAttrValueBO);
    }

    /**
     * 获得商品规格值列表
     *
     * @param queryDTO 商品规格值的列表查询条件 DTO
     * @return 商品规格值列表
     */
    public List<ProductAttrValueRespDTO> listProductAttrValues(ProductAttrValueListQueryReqDTO queryDTO) {
        List<ProductAttrValueBO> productAttrValueBOs = productAttrService.listProductAttrValues(ProductAttrConvert.INSTANCE.convert(queryDTO));
        return ProductAttrConvert.INSTANCE.convertList04(productAttrValueBOs);
    }

}
