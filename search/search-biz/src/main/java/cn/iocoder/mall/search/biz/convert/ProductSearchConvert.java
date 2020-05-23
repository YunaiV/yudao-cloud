package cn.iocoder.mall.search.biz.convert;

import cn.iocoder.mall.search.biz.bo.ProductBO;
import cn.iocoder.mall.search.biz.dataobject.ESProductDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductSearchConvert {

    ProductSearchConvert INSTANCE = Mappers.getMapper(ProductSearchConvert.class);

    List<ProductBO> convert(List<ESProductDO> list);

}
