package cn.iocoder.mall.product.convert;

import cn.iocoder.mall.product.api.bo.*;
import cn.iocoder.mall.product.api.dto.ProductAttrAddDTO;
import cn.iocoder.mall.product.api.dto.ProductAttrUpdateDTO;
import cn.iocoder.mall.product.api.dto.ProductAttrValueAddDTO;
import cn.iocoder.mall.product.api.dto.ProductAttrValueUpdateDTO;
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

    @Mappings({})
    ProductAttrValueSimpleBO convert3(ProductAttrValueDO value); // 保证 convert4 能够映射到这个方法

    @Mappings({})
    List<ProductAttrValueSimpleBO> convert4(List<ProductAttrValueDO> values);

    @Mappings({})
    ProductAttrDO convert(ProductAttrAddDTO productAttrAddDTO);

    @Mappings({})
    ProductAttrDO convert(ProductAttrUpdateDTO productAttrUpdateDTO);

    @Mappings({})
    ProductAttrValueDO convert(ProductAttrValueAddDTO productAttrValueAddDTO);

    @Mappings({})
    ProductAttrValueDO convert(ProductAttrValueUpdateDTO productAttrValueUpdateDTO);

    @Mappings({})
    ProductAttrBO convert(ProductAttrDO productAttrDO);

    @Mappings({})
    ProductAttrValueBO convert2(ProductAttrValueDO productAttrValueDO);


}