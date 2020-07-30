package cn.iocoder.mall.productservice.convert.sku;

import cn.iocoder.common.framework.util.StringUtils;
import cn.iocoder.mall.productservice.dal.mysql.dataobject.sku.ProductSkuDO;
import cn.iocoder.mall.productservice.rpc.sku.dto.ProductSkuListQueryReqDTO;
import cn.iocoder.mall.productservice.rpc.sku.dto.ProductSkuRespDTO;
import cn.iocoder.mall.productservice.service.sku.bo.ProductSkuBO;
import cn.iocoder.mall.productservice.service.sku.bo.ProductSkuCreateOrUpdateBO;
import cn.iocoder.mall.productservice.service.sku.bo.ProductSkuListQueryBO;
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
    ProductSkuDO convert(ProductSkuCreateOrUpdateBO bean);

    @Mapping(source = "attrs", target = "attrValueIds", qualifiedByName = "translateAttrValueIdsFromString")
    ProductSkuBO convert(ProductSkuDO bean);

    List<ProductSkuBO> convertList02(List<ProductSkuDO> list);

    ProductSkuRespDTO convert(ProductSkuBO bean);

    ProductSkuListQueryBO convert(ProductSkuListQueryReqDTO bean);

    List<ProductSkuRespDTO> convertList03(List<ProductSkuBO> list);

    @Named("translateAttrValueIdsFromString")
    default List<String> translateAttrValueIdsFromString(String attrValueIdsStar) {
        return StringUtils.split(attrValueIdsStar, ",");
    }

    @Named("translateAttrValueIdsFromList")
    default String translateAttrValueIdsFromList(List<Integer> attrValueIds) {
        return StringUtils.join(attrValueIds, ",");
    }

}
