package cn.iocoder.mall.productservice.convert.sku;

import cn.iocoder.common.framework.util.StringUtils;
import cn.iocoder.mall.productservice.dal.mysql.dataobject.spu.ProductSkuDO;
import cn.iocoder.mall.productservice.service.sku.bo.ProductSkuCreateOrUpdateBO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductSkuConvert {

    ProductSkuConvert INSTANCE = Mappers.getMapper(ProductSkuConvert.class);

    List<ProductSkuDO> convertList(List<ProductSkuCreateOrUpdateBO> list);

    @Mapping(source = "attrValueIds", target = "attrs", qualifiedByName = "translatePicUrlsFromStringList")
    ProductSkuDO convertList(ProductSkuCreateOrUpdateBO bean);

    @Named("translateAttrValueIdsFromString")
    default List<String> translateAttrValueIdsFromString(String attrValueIdsStar) {
        return StringUtils.split(attrValueIdsStar, ",");
    }

    @Named("translateAttrValueIdsFromList")
    default String translateAttrValueIdsFromList(List<Integer> attrValueIds) {
        return StringUtils.join(attrValueIds, ",");
    }

}
