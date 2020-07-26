package cn.iocoder.mall.productservice.manager.spu;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.productservice.convert.spu.ProductSpuConvert;
import cn.iocoder.mall.productservice.rpc.spu.dto.ProductSpuCreateReqDTO;
import cn.iocoder.mall.productservice.rpc.spu.dto.ProductSpuPageReqDTO;
import cn.iocoder.mall.productservice.rpc.spu.dto.ProductSpuRespDTO;
import cn.iocoder.mall.productservice.rpc.spu.dto.ProductSpuUpdateReqDTO;
import cn.iocoder.mall.productservice.service.spu.ProductSpuService;
import cn.iocoder.mall.productservice.service.spu.bo.ProductSpuBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 商品 SPU Manager
*/
@Service
public class ProductSpuManager {

    @Autowired
    private ProductSpuService productSpuService;

    /**
    * 创建商品 SPU
    *
    * @param createDTO 创建商品 SPU DTO
    * @return 商品 SPU
    */
    public Integer createProductSpu(ProductSpuCreateReqDTO createDTO) {
        ProductSpuBO productSpuBO = productSpuService.createProductSpu(ProductSpuConvert.INSTANCE.convert(createDTO));
        return productSpuBO.getId();
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
