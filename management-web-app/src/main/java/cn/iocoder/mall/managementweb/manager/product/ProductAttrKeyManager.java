package cn.iocoder.mall.managementweb.manager.product;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.managementweb.controller.product.vo.attr.*;
import cn.iocoder.mall.managementweb.convert.product.ProductAttrConvert;
import cn.iocoder.mall.productservice.rpc.attr.ProductAttrRpc;
import cn.iocoder.mall.productservice.rpc.attr.dto.ProductAttrKeyRespDTO;
import cn.iocoder.mall.productservice.rpc.attr.dto.ProductAttrValueRespDTO;
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
                ProductAttrConvert.INSTANCE.convert(createVO));
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
                ProductAttrConvert.INSTANCE.convert(updateVO));
        updateProductAttrKeyResult.checkError();
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
        return ProductAttrConvert.INSTANCE.convert(getProductAttrKeyResult.getData());
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
        return ProductAttrConvert.INSTANCE.convertList(listProductAttrKeyResult.getData());
    }

    /**
    * 获得商品规格键分页
    *
    * @param pageVO 商品规格键分页查询
    * @return 商品规格键分页结果
    */
    public PageResult<ProductAttrKeyRespVO> pageProductAttrKey(ProductAttrKeyPageReqVO pageVO) {
        CommonResult<PageResult<ProductAttrKeyRespDTO>> pageProductAttrKeyResult = productAttrKeyRpc.pageProductAttrKey(
                ProductAttrConvert.INSTANCE.convert(pageVO));
        pageProductAttrKeyResult.checkError();
        return ProductAttrConvert.INSTANCE.convertPage(pageProductAttrKeyResult.getData());
    }

    /**
     * 创建商品规格值
     *
     * @param createVO 创建商品规格值 VO
     * @return 商品规格值
     */
    public Integer createProductAttrValue(ProductAttrValueCreateReqVO createVO) {
        CommonResult<Integer> createProductAttrValueResult = productAttrKeyRpc.createProductAttrValue(
                ProductAttrConvert.INSTANCE.convert(createVO));
        createProductAttrValueResult.checkError();
        return createProductAttrValueResult.getData();
    }

    /**
     * 更新商品规格值
     *
     * @param updateVO 更新商品规格值 VO
     */
    public void updateProductAttrValue(ProductAttrValueUpdateReqVO updateVO) {
        CommonResult<Boolean> updateProductAttrValueResult = productAttrKeyRpc.updateProductAttrValue(
                ProductAttrConvert.INSTANCE.convert(updateVO));
        updateProductAttrValueResult.checkError();
    }

    /**
     * 获得商品规格值
     *
     * @param productAttrValueId 商品规格值编号
     * @return 商品规格值
     */
    public ProductAttrValueRespVO getProductAttrValue(Integer productAttrValueId) {
        CommonResult<ProductAttrValueRespDTO> getProductAttrValueResult = productAttrKeyRpc.getProductAttrValue(productAttrValueId);
        getProductAttrValueResult.checkError();
        return ProductAttrConvert.INSTANCE.convert(getProductAttrValueResult.getData());
    }

    /**
     * 获得商品规格值列表
     *
     * @param queryReqVO 商品规格值的列表查询条件 VO
     * @return 商品规格值列表
     */
    public List<ProductAttrValueRespVO> listProductAttrValues(ProductAttrValueListQueryReqVO queryReqVO) {
        CommonResult<List<ProductAttrValueRespDTO>> listProductAttrValueResult = productAttrKeyRpc.listProductAttrValues(
                ProductAttrConvert.INSTANCE.convert(queryReqVO));
        listProductAttrValueResult.checkError();
        return ProductAttrConvert.INSTANCE.convertList02(listProductAttrValueResult.getData());
    }

}
