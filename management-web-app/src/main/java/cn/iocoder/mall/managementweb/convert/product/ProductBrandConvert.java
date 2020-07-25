package cn.iocoder.mall.managementweb.convert.product;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.managementweb.controller.product.vo.brand.ProductBrandCreateReqVO;
import cn.iocoder.mall.managementweb.controller.product.vo.brand.ProductBrandPageReqVO;
import cn.iocoder.mall.managementweb.controller.product.vo.brand.ProductBrandRespVO;
import cn.iocoder.mall.managementweb.controller.product.vo.brand.ProductBrandUpdateReqVO;
import cn.iocoder.mall.productservice.rpc.brand.dto.ProductBrandCreateReqDTO;
import cn.iocoder.mall.productservice.rpc.brand.dto.ProductBrandPageReqDTO;
import cn.iocoder.mall.productservice.rpc.brand.dto.ProductBrandRespDTO;
import cn.iocoder.mall.productservice.rpc.brand.dto.ProductBrandUpdateReqDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductBrandConvert {

    ProductBrandConvert INSTANCE = Mappers.getMapper(ProductBrandConvert.class);

    ProductBrandCreateReqDTO convert(ProductBrandCreateReqVO bean);

    ProductBrandUpdateReqDTO convert(ProductBrandUpdateReqVO bean);

    ProductBrandRespVO convert(ProductBrandRespDTO bean);

    List<ProductBrandRespVO> convertList(List<ProductBrandRespDTO> list);

    PageResult<ProductBrandRespVO> convertPage(PageResult<ProductBrandRespDTO> page);

    ProductBrandPageReqDTO convert(ProductBrandPageReqVO bean);

}
