package cn.iocoder.mall.productservice.rpc.attr;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.productservice.rpc.attr.dto.ProductAttrKeyCreateReqDTO;
import cn.iocoder.mall.productservice.rpc.attr.dto.ProductAttrKeyPageReqDTO;
import cn.iocoder.mall.productservice.rpc.attr.dto.ProductAttrKeyRespDTO;
import cn.iocoder.mall.productservice.rpc.attr.dto.ProductAttrKeyUpdateReqDTO;

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
     * 删除商品规格键
     *
     * @param productAttrKeyId 商品规格键编号
     */
    CommonResult<Boolean> deleteProductAttrKey(Integer productAttrKeyId);

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

}
