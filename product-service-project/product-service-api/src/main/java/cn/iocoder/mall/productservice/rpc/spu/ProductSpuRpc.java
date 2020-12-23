package cn.iocoder.mall.productservice.rpc.spu;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.productservice.rpc.spu.dto.*;

import java.util.Collection;
import java.util.List;

/**
* 商品 SPU Rpc 接口
*/
public interface ProductSpuRpc {

    /**
    * 创建商品 SPU
    *
    * @param createDTO 创建商品 SPU DTO
    * @return 商品 SPU编号
    */
    CommonResult<Integer> createProductSpu(ProductSpuAndSkuCreateReqDTO createDTO);

    /**
    * 更新商品 SPU
    *
    * @param updateDTO 更新商品 SPU DTO
    */
    CommonResult<Boolean> updateProductSpu(ProductSpuAndSkuUpdateReqDTO updateDTO);

    /**
    * 获得商品 SPU
    *
    * @param productSpuId 商品 SPU 编号
    * @return 商品 SPU
    */
    CommonResult<ProductSpuRespDTO> getProductSpu(Integer productSpuId);

    /**
    * 获得商品 SPU列表
    *
    * @param productSpuIds 商品 SPU 编号列表
    * @return 商品 SPU 列表
    */
    CommonResult<List<ProductSpuRespDTO>> listProductSpus(Collection<Integer> productSpuIds);

    /**
    * 获得商品 SPU 分页
    *
    * @param pageDTO 商品 SPU 分页查询
    * @return 商品 SPU 分页结果
    */
    CommonResult<PageResult<ProductSpuRespDTO>> pageProductSpu(ProductSpuPageReqDTO pageDTO);

    /**
     * 顺序获得商品 SPU 编号数组
     *
     * @param lastSpuId 最后一个商品 SPU 编号
     * @param limit 数量
     * @return 商品 SPU 编号数组
     */
    CommonResult<List<Integer>> listProductSpuIds(Integer lastSpuId, Integer limit);

    CommonResult<ProductSpuDetailRespDTO> getProductSpuDetail(Integer productSpuId, Collection<String> fields);

}
