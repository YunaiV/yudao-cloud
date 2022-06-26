package cn.iocoder.mall.productservice.convert.category;

import cn.iocoder.mall.productservice.dal.mysql.dataobject.category.ProductCategoryDO;
import cn.iocoder.mall.productservice.rpc.category.dto.ProductCategoryCreateReqDTO;
import cn.iocoder.mall.productservice.rpc.category.dto.ProductCategoryListQueryReqDTO;
import cn.iocoder.mall.productservice.rpc.category.dto.ProductCategoryRespDTO;
import cn.iocoder.mall.productservice.rpc.category.dto.ProductCategoryUpdateReqDTO;
import cn.iocoder.mall.productservice.service.category.bo.ProductCategoryBO;
import cn.iocoder.mall.productservice.service.category.bo.ProductCategoryCreateBO;
import cn.iocoder.mall.productservice.service.category.bo.ProductCategoryListQueryBO;
import cn.iocoder.mall.productservice.service.category.bo.ProductCategoryUpdateBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductCategoryConvert {

    ProductCategoryConvert INSTANCE = Mappers.getMapper(ProductCategoryConvert.class);

    ProductCategoryDO convert(ProductCategoryCreateBO bean);

    ProductCategoryBO convert(ProductCategoryDO bean);

    List<ProductCategoryBO> convertList(List<ProductCategoryDO> list);

    ProductCategoryDO convert(ProductCategoryUpdateBO bean);

    ProductCategoryCreateBO convert(ProductCategoryCreateReqDTO bean);

    ProductCategoryUpdateBO convert(ProductCategoryUpdateReqDTO bean);

    ProductCategoryRespDTO convert(ProductCategoryBO bean);

    List<ProductCategoryRespDTO> convertList02(List<ProductCategoryBO> list);

    ProductCategoryListQueryBO convert(ProductCategoryListQueryReqDTO bean);

}
