package cn.iocoder.mall.shopweb.convert.product;

import cn.iocoder.common.framework.util.StringUtils;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.common.framework.vo.SortingField;
import cn.iocoder.mall.productservice.rpc.category.dto.ProductCategoryRespDTO;
import cn.iocoder.mall.productservice.rpc.spu.dto.ProductSpuDetailRespDTO;
import cn.iocoder.mall.searchservice.rpc.product.dto.SearchProductPageReqDTO;
import cn.iocoder.mall.searchservice.rpc.product.dto.SearchProductRespDTO;
import cn.iocoder.mall.shopweb.controller.product.vo.product.ProductSpuDetailRespVO;
import cn.iocoder.mall.shopweb.controller.product.vo.product.ProductSpuPageReqVO;
import cn.iocoder.mall.shopweb.controller.product.vo.product.ProductSpuRespVO;
import cn.iocoder.mall.shopweb.controller.product.vo.product.ProductSpuSearchConditionRespVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.List;

@Mapper
public interface ProductSpuConvert {

    ProductSpuConvert INSTANCE = Mappers.getMapper(ProductSpuConvert.class);

    default SearchProductPageReqDTO convert(ProductSpuPageReqVO bean) {
        SearchProductPageReqDTO reqDTO = new SearchProductPageReqDTO()
                .setCid(bean.getCid()).setKeyword(bean.getKeyword());
        reqDTO.setPageNo(bean.getPageNo()).setPageSize(bean.getPageSize());
        if (StringUtils.hasText(bean.getSortField()) && StringUtils.hasText(bean.getSortOrder())) {
            reqDTO.setSorts(Collections.singletonList(new SortingField(bean.getSortField(), bean.getSortOrder())));
        }
        return reqDTO;
    }

    PageResult<ProductSpuRespVO> convertPage(PageResult<SearchProductRespDTO> page);

    List<ProductSpuSearchConditionRespVO.Category> convertList(List<ProductCategoryRespDTO> list);

    ProductSpuDetailRespVO convert(ProductSpuDetailRespDTO bean);

}
