package cn.iocoder.mall.product.biz.convert.product;

import cn.iocoder.mall.product.biz.bo.product.*;
import cn.iocoder.mall.product.biz.dataobject.product.ProductAttrDO;
import cn.iocoder.mall.product.biz.dataobject.product.ProductAttrValueDO;
import cn.iocoder.mall.product.biz.dto.product.ProductAttrAddDTO;
import cn.iocoder.mall.product.biz.dto.product.ProductAttrUpdateDTO;
import cn.iocoder.mall.product.biz.dto.product.ProductAttrValueAddDTO;
import cn.iocoder.mall.product.biz.dto.product.ProductAttrValueUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductAttrConvert2 {

    ProductAttrConvert2 INSTANCE = Mappers.getMapper(ProductAttrConvert2.class);

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
    ProductAttrBO2 convert(ProductAttrDO productAttrDO);

    @Mappings({})
    ProductAttrValueBO convert2(ProductAttrValueDO productAttrValueDO);


}