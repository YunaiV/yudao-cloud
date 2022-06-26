package cn.iocoder.mall.product.biz.convert.attr;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.product.biz.bo.attr.*;
import cn.iocoder.mall.product.biz.dataobject.attr.ProductAttrDO;
import cn.iocoder.mall.product.biz.dataobject.attr.ProductAttrValueDO;
import cn.iocoder.mall.product.biz.dto.attr.ProductAttrUpdateDTO;
import cn.iocoder.mall.product.biz.dto.attr.ProductAttrValueAddDTO;
import cn.iocoder.mall.product.biz.dto.attr.ProductAttrValueUpdateDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductAttrConvert {

    ProductAttrConvert INSTANCE = Mappers.getMapper(ProductAttrConvert.class);

    @Mapping(source = "records", target = "list")
    PageResult<ProductAttrWithValueBO> convertPage(IPage<ProductAttrDO> bean);

    @Mappings({})
    ProductAttrBO convertAttr(ProductAttrDO values);

    @Mappings({})
    ProductAttrValueBO convertAttrValue(ProductAttrValueDO productAttrValueDO);

    @Mappings({})
    List<ProductAttrValueBO> convertAttrValues(List<ProductAttrValueDO> values);

    @Mappings({})
    List<ProductAttrSimpleWithValueBO> convertAttrSimple(List<ProductAttrDO> attrs);

    @Mappings({})
    List<ProductAttrValueSimpleBO> convertAttrValueSimple(List<ProductAttrValueDO> values);

    @Mappings({})
    ProductAttrDO convertUpdate(ProductAttrUpdateDTO productAttrUpdateDTO);

    @Mappings({})
    ProductAttrValueDO convertValueAdd(ProductAttrValueAddDTO productAttrValueAddDTO);

    @Mappings({})
    ProductAttrValueDO convertValueUpdate(ProductAttrValueUpdateDTO productAttrValueUpdateDTO);
}
