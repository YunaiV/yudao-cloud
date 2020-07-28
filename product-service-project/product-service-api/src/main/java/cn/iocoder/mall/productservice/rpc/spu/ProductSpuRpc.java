package cn.iocoder.mall.productservice.rpc.spu;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.productservice.rpc.spu.dto.ProductSpuAndSkuCreateReqDTO;
import cn.iocoder.mall.productservice.rpc.spu.dto.ProductSpuAndSkuUpdateReqDTO;
import cn.iocoder.mall.productservice.rpc.spu.dto.ProductSpuPageReqDTO;
import cn.iocoder.mall.productservice.rpc.spu.dto.ProductSpuRespDTO;

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
    CommonResult<List<ProductSpuRespDTO>> listProductSpus(List<Integer> productSpuIds);

    /**
    * 获得商品 SPU分页
    *
    * @param pageDTO 商品 SPU分页查询
    * @return 商品 SPU分页结果
    */
    CommonResult<PageResult<ProductSpuRespDTO>> pageProductSpu(ProductSpuPageReqDTO pageDTO);

}
