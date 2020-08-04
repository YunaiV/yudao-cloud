package cn.iocoder.mall.shopweb.convert.product;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.searchservice.rpc.product.dto.SearchProductPageReqDTO;
import cn.iocoder.mall.searchservice.rpc.product.dto.SearchProductRespDTO;
import cn.iocoder.mall.shopweb.controller.product.vo.product.ProductSpuPageReqVO;
import cn.iocoder.mall.shopweb.controller.product.vo.product.ProductSpuRespVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductSpuConvert {

    ProductSpuConvert INSTANCE = Mappers.getMapper(ProductSpuConvert.class);

    SearchProductPageReqDTO convert(ProductSpuPageReqVO bean);

    PageResult<ProductSpuRespVO> convertPage(PageResult<SearchProductRespDTO> page);

}
