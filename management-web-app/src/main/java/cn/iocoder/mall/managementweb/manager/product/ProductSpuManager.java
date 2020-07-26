package cn.iocoder.mall.managementweb.manager.product;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.managementweb.controller.product.vo.spu.ProductSpuCreateReqVO;
import cn.iocoder.mall.managementweb.controller.product.vo.spu.ProductSpuPageReqVO;
import cn.iocoder.mall.managementweb.controller.product.vo.spu.ProductSpuRespVO;
import cn.iocoder.mall.managementweb.controller.product.vo.spu.ProductSpuUpdateReqVO;
import cn.iocoder.mall.managementweb.convert.product.ProductSpuConvert;
import cn.iocoder.mall.productservice.rpc.spu.ProductSpuRpc;
import cn.iocoder.mall.productservice.rpc.spu.dto.ProductSpuRespDTO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 商品 SPU Manager
*/
@Service
public class ProductSpuManager {

    @DubboReference(version = "${dubbo.consumer.ProductSpuRpc.version}")
    private ProductSpuRpc productSpuRpc;

    /**
    * 创建商品 SPU
    *
    * @param createVO 创建商品 SPU VO
    * @return 商品 SPU
    */
    public Integer createProductSpu(ProductSpuCreateReqVO createVO) {
        CommonResult<Integer> createProductSpuResult = productSpuRpc.createProductSpu(ProductSpuConvert.INSTANCE.convert(createVO));
        createProductSpuResult.checkError();
        return createProductSpuResult.getData();
    }

    /**
    * 更新商品 SPU
    *
    * @param updateVO 更新商品 SPU VO
    */
    public void updateProductSpu(ProductSpuUpdateReqVO updateVO) {
        CommonResult<Boolean> updateProductSpuResult = productSpuRpc.updateProductSpu(ProductSpuConvert.INSTANCE.convert(updateVO));
        updateProductSpuResult.checkError();
    }

    /**
    * 获得商品 SPU
    *
    * @param productSpuId 商品 SPU编号
    * @return 商品 SPU
    */
    public ProductSpuRespVO getProductSpu(Integer productSpuId) {
        CommonResult<ProductSpuRespDTO> getProductSpuResult = productSpuRpc.getProductSpu(productSpuId);
        getProductSpuResult.checkError();
        return ProductSpuConvert.INSTANCE.convert(getProductSpuResult.getData());
    }

    /**
    * 获得商品 SPU列表
    *
    * @param productSpuIds 商品 SPU编号列表
    * @return 商品 SPU列表
    */
    public List<ProductSpuRespVO> listProductSpus(List<Integer> productSpuIds) {
        CommonResult<List<ProductSpuRespDTO>> listProductSpuResult = productSpuRpc.listProductSpus(productSpuIds);
        listProductSpuResult.checkError();
        return ProductSpuConvert.INSTANCE.convertList(listProductSpuResult.getData());
    }

    /**
    * 获得商品 SPU分页
    *
    * @param pageVO 商品 SPU分页查询
    * @return 商品 SPU分页结果
    */
    public PageResult<ProductSpuRespVO> pageProductSpu(ProductSpuPageReqVO pageVO) {
        CommonResult<PageResult<ProductSpuRespDTO>> pageProductSpuResult = productSpuRpc.pageProductSpu(ProductSpuConvert.INSTANCE.convert(pageVO));
        pageProductSpuResult.checkError();
        return ProductSpuConvert.INSTANCE.convertPage(pageProductSpuResult.getData());
    }

}
