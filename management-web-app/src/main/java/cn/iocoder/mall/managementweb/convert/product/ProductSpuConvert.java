package cn.iocoder.mall.managementweb.convert.product;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.managementweb.controller.product.vo.spu.ProductSpuCreateReqVO;
import cn.iocoder.mall.managementweb.controller.product.vo.spu.ProductSpuPageReqVO;
import cn.iocoder.mall.managementweb.controller.product.vo.spu.ProductSpuRespVO;
import cn.iocoder.mall.managementweb.controller.product.vo.spu.ProductSpuUpdateReqVO;
import cn.iocoder.mall.productservice.rpc.spu.dto.ProductSpuAndSkuCreateReqDTO;
import cn.iocoder.mall.productservice.rpc.spu.dto.ProductSpuAndSkuUpdateReqDTO;
import cn.iocoder.mall.productservice.rpc.spu.dto.ProductSpuPageReqDTO;
import cn.iocoder.mall.productservice.rpc.spu.dto.ProductSpuRespDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductSpuConvert {

    ProductSpuConvert INSTANCE = Mappers.getMapper(ProductSpuConvert.class);

    ProductSpuAndSkuCreateReqDTO convert(ProductSpuCreateReqVO bean);

    ProductSpuAndSkuUpdateReqDTO convert(ProductSpuUpdateReqVO bean);

    ProductSpuRespVO convert(ProductSpuRespDTO bean);

    List<ProductSpuRespVO> convertList(List<ProductSpuRespDTO> list);

	PageResult<ProductSpuRespVO> convertPage(PageResult<ProductSpuRespDTO> page);

    ProductSpuPageReqDTO convert(ProductSpuPageReqVO bean);

}
