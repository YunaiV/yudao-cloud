package cn.iocoder.mall.productservice.manager.attr;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.productservice.convert.attr.ProductAttrConvert;
import cn.iocoder.mall.productservice.rpc.attr.dto.ProductAttrKeyCreateReqDTO;
import cn.iocoder.mall.productservice.rpc.attr.dto.ProductAttrKeyPageReqDTO;
import cn.iocoder.mall.productservice.rpc.attr.dto.ProductAttrKeyRespDTO;
import cn.iocoder.mall.productservice.rpc.attr.dto.ProductAttrKeyUpdateReqDTO;
import cn.iocoder.mall.productservice.service.attr.ProductAttrService;
import cn.iocoder.mall.productservice.service.attr.bo.ProductAttrKeyBO;
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
     * 删除商品规格键
     *
     * @param productAttrKeyId 商品规格键编号
     */
    public void deleteProductAttrKey(Integer productAttrKeyId) {
        productAttrService.deleteProductAttrKey(productAttrKeyId);
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

}
