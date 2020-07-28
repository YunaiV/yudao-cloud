package cn.iocoder.mall.managementweb.convert.product;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.managementweb.controller.product.vo.attr.ProductAttrKeyCreateReqVO;
import cn.iocoder.mall.managementweb.controller.product.vo.attr.ProductAttrKeyPageReqVO;
import cn.iocoder.mall.managementweb.controller.product.vo.attr.ProductAttrKeyRespVO;
import cn.iocoder.mall.managementweb.controller.product.vo.attr.ProductAttrKeyUpdateReqVO;
import cn.iocoder.mall.productservice.rpc.attr.dto.ProductAttrKeyCreateReqDTO;
import cn.iocoder.mall.productservice.rpc.attr.dto.ProductAttrKeyPageReqDTO;
import cn.iocoder.mall.productservice.rpc.attr.dto.ProductAttrKeyRespDTO;
import cn.iocoder.mall.productservice.rpc.attr.dto.ProductAttrKeyUpdateReqDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductAttrKeyConvert {

    ProductAttrKeyConvert INSTANCE = Mappers.getMapper(ProductAttrKeyConvert.class);

    ProductAttrKeyCreateReqDTO convert(ProductAttrKeyCreateReqVO bean);

    ProductAttrKeyUpdateReqDTO convert(ProductAttrKeyUpdateReqVO bean);

    ProductAttrKeyRespVO convert(ProductAttrKeyRespDTO bean);

    List<ProductAttrKeyRespVO> convertList(List<ProductAttrKeyRespDTO> list);

	PageResult<ProductAttrKeyRespVO> convertPage(PageResult<ProductAttrKeyRespDTO> page);

    ProductAttrKeyPageReqDTO convert(ProductAttrKeyPageReqVO bean);

}
