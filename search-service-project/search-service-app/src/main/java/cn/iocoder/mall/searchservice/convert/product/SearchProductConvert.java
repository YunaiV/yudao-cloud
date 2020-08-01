package cn.iocoder.mall.searchservice.convert.product;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.productservice.rpc.category.dto.ProductCategoryRespDTO;
import cn.iocoder.mall.productservice.rpc.spu.dto.ProductSpuRespDTO;
import cn.iocoder.mall.searchservice.dal.es.dataobject.ESProductDO;
import cn.iocoder.mall.searchservice.service.product.bo.SearchProductBO;
import cn.iocoder.mall.searchservice.service.product.bo.SearchProductSaveBO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

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

    @Mapping(source = "content", target = "list")
    @Mapping(source = "getTotalElements", target = "total")
    PageResult<SearchProductBO> convert(Page<ESProductDO> searchPage);

}
