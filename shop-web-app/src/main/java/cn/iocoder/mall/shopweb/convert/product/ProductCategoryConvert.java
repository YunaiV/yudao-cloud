package cn.iocoder.mall.shopweb.convert.product;

import cn.iocoder.mall.productservice.rpc.category.dto.ProductCategoryRespDTO;
import cn.iocoder.mall.shopweb.controller.product.vo.category.ProductCategoryRespVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductCategoryConvert {

    ProductCategoryConvert INSTANCE = Mappers.getMapper(ProductCategoryConvert.class);

    List<ProductCategoryRespVO> convertList(List<ProductCategoryRespDTO> list);

}
