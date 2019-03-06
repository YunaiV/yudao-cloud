package cn.iocoder.mall.product.convert;

import cn.iocoder.mall.product.api.bo.ProductAttrDetailBO;
import cn.iocoder.mall.product.api.bo.ProductAttrSimpleBO;
import cn.iocoder.mall.product.api.bo.ProductAttrValueDetailBO;
import cn.iocoder.mall.product.api.bo.ProductAttrValueSimpleBO;
import cn.iocoder.mall.product.dataobject.ProductAttrDO;
import cn.iocoder.mall.product.dataobject.ProductAttrValueDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductAttrConvert {

    ProductAttrConvert INSTANCE = Mappers.getMapper(ProductAttrConvert.class);

    @Mappings({})
    List<ProductAttrDetailBO> convert(List<ProductAttrDO> attrs);

    @Mappings({})
    ProductAttrValueDetailBO convert(ProductAttrValueDO value);

    @Mappings({})
    List<ProductAttrValueDetailBO> convert2(List<ProductAttrValueDO> values);

    @Mappings({})
    List<ProductAttrSimpleBO> convert3(List<ProductAttrDO> attrs);

//    @Mappings({})
//    ProductAttrValueSimpleBO convert3(ProductAttrValueDO value);
//
    @Mappings({})
    List<ProductAttrValueSimpleBO> convert4(List<ProductAttrValueDO> values);
    
}