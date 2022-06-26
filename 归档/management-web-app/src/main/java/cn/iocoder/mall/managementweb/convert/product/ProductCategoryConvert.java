package cn.iocoder.mall.managementweb.convert.product;

import cn.iocoder.mall.managementweb.controller.product.vo.category.ProductCategoryCreateReqVO;
import cn.iocoder.mall.managementweb.controller.product.vo.category.ProductCategoryRespVO;
import cn.iocoder.mall.managementweb.controller.product.vo.category.ProductCategoryTreeNodeRespVO;
import cn.iocoder.mall.managementweb.controller.product.vo.category.ProductCategoryUpdateReqVO;
import cn.iocoder.mall.productservice.rpc.category.dto.ProductCategoryCreateReqDTO;
import cn.iocoder.mall.productservice.rpc.category.dto.ProductCategoryRespDTO;
import cn.iocoder.mall.productservice.rpc.category.dto.ProductCategoryUpdateReqDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductCategoryConvert {

    ProductCategoryConvert INSTANCE = Mappers.getMapper(ProductCategoryConvert.class);

    ProductCategoryCreateReqDTO convert(ProductCategoryCreateReqVO bean);

    ProductCategoryUpdateReqDTO convert(ProductCategoryUpdateReqVO bean);

    ProductCategoryRespVO convert(ProductCategoryRespDTO bean);

    ProductCategoryTreeNodeRespVO convertTreeNode(ProductCategoryRespDTO bean);

}
