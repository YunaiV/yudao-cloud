package cn.iocoder.mall.searchservice.convert.product;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.productservice.rpc.category.dto.ProductCategoryRespDTO;
import cn.iocoder.mall.productservice.rpc.spu.dto.ProductSpuRespDTO;
import cn.iocoder.mall.searchservice.dal.es.dataobject.ESProductDO;
import cn.iocoder.mall.searchservice.rpc.product.dto.SearchProductConditionRespDTO;
import cn.iocoder.mall.searchservice.rpc.product.dto.SearchProductPageReqDTO;
import cn.iocoder.mall.searchservice.rpc.product.dto.SearchProductRespDTO;
import cn.iocoder.mall.searchservice.service.product.bo.SearchProductBO;
import cn.iocoder.mall.searchservice.service.product.bo.SearchProductConditionBO;
import cn.iocoder.mall.searchservice.service.product.bo.SearchProductPageQueryBO;
import cn.iocoder.mall.searchservice.service.product.bo.SearchProductSaveBO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper
public interface SearchProductConvert {

    SearchProductConvert INSTANCE = Mappers.getMapper(SearchProductConvert.class);


    @Mapping(source = "spu.id", target = "id")
    @Mapping(source = "spu.name", target = "name")
    @Mapping(source = "spu.sellPoint", target = "sellPoint")
    @Mapping(source = "spu.description", target = "description")
    @Mapping(source = "spu.cid", target = "cid")
    @Mapping(source = "category.name", target = "categoryName")
    @Mapping(source = "spu.picUrls", target = "picUrls")
    @Mapping(source = "spu.visible", target = "visible")
    @Mapping(source = "spu.sort", target = "sort")
    SearchProductSaveBO convert(ProductSpuRespDTO spu, ProductCategoryRespDTO category);

    ESProductDO convert(SearchProductSaveBO bean);

    List<SearchProductBO> convertList(List<ESProductDO> list);

    default PageResult<SearchProductBO> convertPage(Page<ESProductDO> page) {
        return new PageResult<SearchProductBO>().setList(convertList(page.getContent()))
                .setTotal(page.getTotalElements());
    }

    SearchProductPageQueryBO convert(SearchProductPageReqDTO bean);

    PageResult<SearchProductRespDTO> convertPage(PageResult<SearchProductBO> page);

    SearchProductConditionRespDTO convert(SearchProductConditionBO bean);

}
