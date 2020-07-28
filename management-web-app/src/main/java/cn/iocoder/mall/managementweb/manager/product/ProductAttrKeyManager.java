package cn.iocoder.mall.managementweb.manager.product;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.managementweb.controller.product.vo.attr.ProductAttrKeyCreateReqVO;
import cn.iocoder.mall.managementweb.controller.product.vo.attr.ProductAttrKeyPageReqVO;
import cn.iocoder.mall.managementweb.controller.product.vo.attr.ProductAttrKeyRespVO;
import cn.iocoder.mall.managementweb.controller.product.vo.attr.ProductAttrKeyUpdateReqVO;
import cn.iocoder.mall.managementweb.convert.product.ProductAttrKeyConvert;
import cn.iocoder.mall.productservice.rpc.attr.ProductAttrRpc;
import cn.iocoder.mall.productservice.rpc.attr.dto.ProductAttrKeyRespDTO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 商品规格 Manager
*/
@Service
public class ProductAttrKeyManager {

    @DubboReference(version = "${dubbo.consumer.ProductAttrRpc.version}")
    private ProductAttrRpc productAttrKeyRpc;

    /**
    * 创建商品规格键
    *
    * @param createVO 创建商品规格键 VO
    * @return 商品规格键
    */
    public Integer createProductAttrKey(ProductAttrKeyCreateReqVO createVO) {
        CommonResult<Integer> createProductAttrKeyResult = productAttrKeyRpc.createProductAttrKey(
                ProductAttrKeyConvert.INSTANCE.convert(createVO));
        createProductAttrKeyResult.checkError();
        return createProductAttrKeyResult.getData();
    }

    /**
    * 更新商品规格键
    *
    * @param updateVO 更新商品规格键 VO
    */
    public void updateProductAttrKey(ProductAttrKeyUpdateReqVO updateVO) {
        CommonResult<Boolean> updateProductAttrKeyResult = productAttrKeyRpc.updateProductAttrKey(
                ProductAttrKeyConvert.INSTANCE.convert(updateVO));
        updateProductAttrKeyResult.checkError();
    }

    /**
    * 删除商品规格键
    *
    * @param productAttrKeyId 商品规格键编号
    */
    public void deleteProductAttrKey(Integer productAttrKeyId) {
        CommonResult<Boolean> deleteProductAttrKeyResult = productAttrKeyRpc.deleteProductAttrKey(productAttrKeyId);
        deleteProductAttrKeyResult.checkError();
    }

    /**
    * 获得商品规格键
    *
    * @param productAttrKeyId 商品规格键编号
    * @return 商品规格键
    */
    public ProductAttrKeyRespVO getProductAttrKey(Integer productAttrKeyId) {
        CommonResult<ProductAttrKeyRespDTO> getProductAttrKeyResult = productAttrKeyRpc.getProductAttrKey(productAttrKeyId);
        getProductAttrKeyResult.checkError();
        return ProductAttrKeyConvert.INSTANCE.convert(getProductAttrKeyResult.getData());
    }

    /**
    * 获得商品规格键列表
    *
    * @param productAttrKeyIds 商品规格键编号列表
    * @return 商品规格键列表
    */
    public List<ProductAttrKeyRespVO> listProductAttrKeys(List<Integer> productAttrKeyIds) {
        CommonResult<List<ProductAttrKeyRespDTO>> listProductAttrKeyResult = productAttrKeyRpc.listProductAttrKeys(productAttrKeyIds);
        listProductAttrKeyResult.checkError();
        return ProductAttrKeyConvert.INSTANCE.convertList(listProductAttrKeyResult.getData());
    }

    /**
    * 获得商品规格键分页
    *
    * @param pageVO 商品规格键分页查询
    * @return 商品规格键分页结果
    */
    public PageResult<ProductAttrKeyRespVO> pageProductAttrKey(ProductAttrKeyPageReqVO pageVO) {
        CommonResult<PageResult<ProductAttrKeyRespDTO>> pageProductAttrKeyResult = productAttrKeyRpc.pageProductAttrKey(
                ProductAttrKeyConvert.INSTANCE.convert(pageVO));
        pageProductAttrKeyResult.checkError();
        return ProductAttrKeyConvert.INSTANCE.convertPage(pageProductAttrKeyResult.getData());
    }

}
