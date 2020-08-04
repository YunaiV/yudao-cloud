package cn.iocoder.mall.managementweb.convert.product;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.managementweb.controller.product.vo.attr.*;
import cn.iocoder.mall.productservice.rpc.attr.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductAttrConvert {

    ProductAttrConvert INSTANCE = Mappers.getMapper(ProductAttrConvert.class);

    ProductAttrKeyCreateReqDTO convert(ProductAttrKeyCreateReqVO bean);

    ProductAttrKeyUpdateReqDTO convert(ProductAttrKeyUpdateReqVO bean);

    ProductAttrKeyRespVO convert(ProductAttrKeyRespDTO bean);

    List<ProductAttrKeyRespVO> convertList(List<ProductAttrKeyRespDTO> list);

	PageResult<ProductAttrKeyRespVO> convertPage(PageResult<ProductAttrKeyRespDTO> page);

    ProductAttrKeyPageReqDTO convert(ProductAttrKeyPageReqVO bean);


    ProductAttrValueCreateReqDTO convert(ProductAttrValueCreateReqVO bean);

    ProductAttrValueUpdateReqDTO convert(ProductAttrValueUpdateReqVO bean);

    ProductAttrValueRespVO convert(ProductAttrValueRespDTO bean);

    List<ProductAttrValueRespVO> convertList02(List<ProductAttrValueRespDTO> list);

    ProductAttrValueListQueryReqDTO convert(ProductAttrValueListQueryReqVO bean);

}
