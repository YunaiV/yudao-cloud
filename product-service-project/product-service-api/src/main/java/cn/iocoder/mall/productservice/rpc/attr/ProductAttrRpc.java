package cn.iocoder.mall.productservice.rpc.attr;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.productservice.rpc.attr.dto.*;

import java.util.List;

/**
 * 商品规格 Rpc 接口
 */
public interface ProductAttrRpc {

    /**
     * 创建商品规格键
     *
     * @param createDTO 创建商品规格键 DTO
     * @return 商品规格键编号
     */
    CommonResult<Integer> createProductAttrKey(ProductAttrKeyCreateReqDTO createDTO);

    /**
     * 更新商品规格键
     *
     * @param updateDTO 更新商品规格键 DTO
     */
    CommonResult<Boolean> updateProductAttrKey(ProductAttrKeyUpdateReqDTO updateDTO);

    /**
     * 获得商品规格键
     *
     * @param productAttrKeyId 商品规格键编号
     * @return 商品规格键
     */
    CommonResult<ProductAttrKeyRespDTO> getProductAttrKey(Integer productAttrKeyId);

    /**
     * 获得商品规格键列表
     *
     * @param productAttrKeyIds 商品规格键编号列表
     * @return 商品规格键列表
     */
    CommonResult<List<ProductAttrKeyRespDTO>> listProductAttrKeys(List<Integer> productAttrKeyIds);

    /**
     * 获得商品规格键分页
     *
     * @param pageDTO 商品规格键分页查询
     * @return 商品规格键分页结果
     */
    CommonResult<PageResult<ProductAttrKeyRespDTO>> pageProductAttrKey(ProductAttrKeyPageReqDTO pageDTO);

    /**
     * 创建商品规格值
     *
     * @param createDTO 创建商品规格值 DTO
     * @return 商品规格值编号
     */
    CommonResult<Integer> createProductAttrValue(ProductAttrValueCreateReqDTO createDTO);

    /**
     * 更新商品规格值
     *
     * @param updateDTO 更新商品规格值 DTO
     */
    CommonResult<Boolean> updateProductAttrValue(ProductAttrValueUpdateReqDTO updateDTO);

    /**
     * 获得商品规格值
     *
     * @param productAttrValueId 商品规格值编号
     * @return 商品规格值
     */
    CommonResult<ProductAttrValueRespDTO> getProductAttrValue(Integer productAttrValueId);

    /**
     * 获得商品规格值列表
     *
     * @param queryDTO 商品规格值的列表查询条件 DTO
     * @return 商品规格值列表
     */
    CommonResult<List<ProductAttrValueRespDTO>> listProductAttrValues(ProductAttrValueListQueryReqDTO queryDTO);

}
