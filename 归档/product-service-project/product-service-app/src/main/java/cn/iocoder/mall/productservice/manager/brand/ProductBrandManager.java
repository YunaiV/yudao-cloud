package cn.iocoder.mall.productservice.manager.brand;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.productservice.convert.brand.ProductBrandConvert;
import cn.iocoder.mall.productservice.rpc.brand.dto.ProductBrandCreateReqDTO;
import cn.iocoder.mall.productservice.rpc.brand.dto.ProductBrandPageReqDTO;
import cn.iocoder.mall.productservice.rpc.brand.dto.ProductBrandRespDTO;
import cn.iocoder.mall.productservice.rpc.brand.dto.ProductBrandUpdateReqDTO;
import cn.iocoder.mall.productservice.service.brand.ProductBrandService;
import cn.iocoder.mall.productservice.service.brand.bo.ProductBrandBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 商品品牌 Manager
*/
@Service
public class ProductBrandManager {

    @Autowired
    private ProductBrandService productBrandService;

    /**
    * 创建商品品牌
    *
    * @param createDTO 创建商品品牌 DTO
    * @return 商品品牌
    */
    public Integer createProductBrand(ProductBrandCreateReqDTO createDTO) {
        ProductBrandBO productBrandBO = productBrandService.createProductBrand(ProductBrandConvert.INSTANCE.convert(createDTO));
        return productBrandBO.getId();
    }

    /**
    * 更新商品品牌
    *
    * @param updateDTO 更新商品品牌 DTO
    */
    public void updateProductBrand(ProductBrandUpdateReqDTO updateDTO) {
        productBrandService.updateProductBrand(ProductBrandConvert.INSTANCE.convert(updateDTO));
    }

    /**
    * 删除商品品牌
    *
    * @param productBrandId 商品品牌编号
    */
    public void deleteProductBrand(Integer productBrandId) {
        productBrandService.deleteProductBrand(productBrandId);
    }

    /**
    * 获得商品品牌
    *
    * @param productBrandId 商品品牌编号
    * @return 商品品牌
    */
    public ProductBrandRespDTO getProductBrand(Integer productBrandId) {
        ProductBrandBO productBrandBO = productBrandService.getProductBrand(productBrandId);
        return ProductBrandConvert.INSTANCE.convert(productBrandBO);
    }

    /**
    * 获得商品品牌列表
    *
    * @param productBrandIds 商品品牌编号列表
    * @return 商品品牌列表
    */
    public List<ProductBrandRespDTO> listProductBrands(List<Integer> productBrandIds) {
        List<ProductBrandBO> productBrandBOs = productBrandService.listProductBrands(productBrandIds);
        return ProductBrandConvert.INSTANCE.convertList02(productBrandBOs);
    }

    /**
    * 获得商品品牌分页
    *
    * @param pageDTO 商品品牌分页查询
    * @return 商品品牌分页结果
    */
    public PageResult<ProductBrandRespDTO> pageProductBrand(ProductBrandPageReqDTO pageDTO) {
        PageResult<ProductBrandBO> pageResultBO = productBrandService.pageProductBrand(ProductBrandConvert.INSTANCE.convert(pageDTO));
        return ProductBrandConvert.INSTANCE.convertPage(pageResultBO);
    }
}
