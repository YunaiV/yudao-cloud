package cn.iocoder.mall.managementweb.manager.product;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.managementweb.controller.product.vo.brand.ProductBrandCreateReqVO;
import cn.iocoder.mall.managementweb.controller.product.vo.brand.ProductBrandPageReqVO;
import cn.iocoder.mall.managementweb.controller.product.vo.brand.ProductBrandRespVO;
import cn.iocoder.mall.managementweb.controller.product.vo.brand.ProductBrandUpdateReqVO;
import cn.iocoder.mall.managementweb.convert.product.ProductBrandConvert;
import cn.iocoder.mall.productservice.rpc.brand.ProductBrandRpc;
import cn.iocoder.mall.productservice.rpc.brand.dto.ProductBrandRespDTO;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 商品品牌 Manager
*/
@Service
public class ProductBrandManager {

    @Reference(version = "${dubbo.consumer.ProductBrandRpc.version}")
    private ProductBrandRpc productBrandRpc;

    /**
    * 创建商品品牌
    *
    * @param createVO 创建商品品牌 VO
    * @return 商品品牌
    */
    public Integer createProductBrand(ProductBrandCreateReqVO createVO) {
        CommonResult<Integer> createProductBrandResult = productBrandRpc.createProductBrand(ProductBrandConvert.INSTANCE.convert(createVO));
        createProductBrandResult.checkError();
        return createProductBrandResult.getData();
    }

    /**
    * 更新商品品牌
    *
    * @param updateVO 更新商品品牌 VO
    */
    public void updateProductBrand(ProductBrandUpdateReqVO updateVO) {
        CommonResult<Boolean> updateProductBrandResult = productBrandRpc.updateProductBrand(ProductBrandConvert.INSTANCE.convert(updateVO));
        updateProductBrandResult.checkError();
    }

    /**
    * 删除商品品牌
    *
    * @param productBrandId 商品品牌编号
    */
    public void deleteProductBrand(Integer productBrandId) {
        CommonResult<Boolean> deleteProductBrandResult = productBrandRpc.deleteProductBrand(productBrandId);
        deleteProductBrandResult.checkError();
    }

    /**
    * 获得商品品牌
    *
    * @param productBrandId 商品品牌编号
    * @return 商品品牌
    */
    public ProductBrandRespVO getProductBrand(Integer productBrandId) {
        CommonResult<ProductBrandRespDTO> getProductBrandResult = productBrandRpc.getProductBrand(productBrandId);
        getProductBrandResult.checkError();
        return ProductBrandConvert.INSTANCE.convert(getProductBrandResult.getData());
    }

    /**
    * 获得商品品牌列表
    *
    * @param productBrandIds 商品品牌编号列表
    * @return 商品品牌列表
    */
    public List<ProductBrandRespVO> listProductBrands(List<Integer> productBrandIds) {
        CommonResult<List<ProductBrandRespDTO>> listProductBrandResult = productBrandRpc.listProductBrands(productBrandIds);
        listProductBrandResult.checkError();
        return ProductBrandConvert.INSTANCE.convertList(listProductBrandResult.getData());
    }

    /**
    * 获得商品品牌分页
    *
    * @param pageVO 商品品牌分页查询
    * @return 商品品牌分页结果
    */
    public PageResult<ProductBrandRespVO> pageProductBrand(ProductBrandPageReqVO pageVO) {
        CommonResult<PageResult<ProductBrandRespDTO>> pageProductBrandResult = productBrandRpc.pageProductBrand(ProductBrandConvert.INSTANCE.convert(pageVO));
        pageProductBrandResult.checkError();
        return ProductBrandConvert.INSTANCE.convertPage(pageProductBrandResult.getData());
    }

}
