package cn.iocoder.mall.product.biz.convert.attr;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.product.biz.bo.attr.ProductAttrBO;
import cn.iocoder.mall.product.biz.bo.attr.ProductAttrValueBO;
import cn.iocoder.mall.product.biz.dataobject.product.ProductAttrDO;
import cn.iocoder.mall.product.biz.dataobject.product.ProductAttrValueDO;
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
    PageResult<ProductAttrBO> convertPage(IPage<ProductAttrDO> bean);

    @Mappings({})
    List<ProductAttrValueBO> convertAttrValue(List<ProductAttrValueDO> values);
}
