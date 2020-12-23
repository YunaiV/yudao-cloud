package cn.iocoder.mall.productservice.convert.sku;

import cn.iocoder.common.framework.util.CollectionUtils;
import cn.iocoder.common.framework.util.StringUtils;
import cn.iocoder.mall.productservice.dal.mysql.dataobject.sku.ProductSkuDO;
import cn.iocoder.mall.productservice.rpc.attr.dto.ProductAttrKeyValueRespDTO;
import cn.iocoder.mall.productservice.rpc.sku.dto.ProductSkuListQueryReqDTO;
import cn.iocoder.mall.productservice.rpc.sku.dto.ProductSkuRespDTO;
import cn.iocoder.mall.productservice.rpc.spu.dto.ProductSpuRespDTO;
import cn.iocoder.mall.productservice.service.attr.bo.ProductAttrKeyValueBO;
import cn.iocoder.mall.productservice.service.sku.bo.ProductSkuBO;
import cn.iocoder.mall.productservice.service.sku.bo.ProductSkuCreateOrUpdateBO;
import cn.iocoder.mall.productservice.service.sku.bo.ProductSkuListQueryBO;
import cn.iocoder.mall.productservice.service.spu.bo.ProductSpuBO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    ProductSpuRespDTO convert(ProductSpuBO bean);

    ProductAttrKeyValueRespDTO convert(ProductAttrKeyValueBO bean);

    default List<ProductSkuRespDTO> convertList(List<ProductSkuBO> skuBOs, List<ProductSpuBO> spuBOs,
                                                List<ProductAttrKeyValueBO> attrBOs) {
        // 创建 ProductAttrDetailBO 的映射。其中，KEY 为 ProductAttrDetailBO.attrValueId ，即规格值的编号
        Map<Integer, ProductAttrKeyValueBO> attrDetailBOMap = CollectionUtils.convertMap(attrBOs,
                ProductAttrKeyValueBO::getAttrValueId);
        // 构建 ProductSpuBO 的映射。
        Map<Integer, ProductSpuBO> spuBOMap = CollectionUtils.convertMap(spuBOs, ProductSpuBO::getId);
        // 拼接数据
        List<ProductSkuRespDTO> skuRespDTOs = new ArrayList<>(skuBOs.size());
        skuBOs.forEach(skuBO -> {
            ProductSkuRespDTO skuRespDTO = convert(skuBO);
            skuRespDTOs.add(skuRespDTO);
            // 拼接商品 SPU
            skuRespDTO.setSpu(convert(spuBOMap.get(skuBO.getSpuId())));
            // 拼接商品 Attr
            skuRespDTO.setAttrs(new ArrayList<>());
            skuBO.getAttrValueIds().forEach(attrValueId -> skuRespDTO.getAttrs().add(convert(attrDetailBOMap.get(attrValueId))));
        });
        return skuRespDTOs;
    }

    @Named("translateAttrValueIdsFromString")
    default List<Integer> translateAttrValueIdsFromString(String attrValueIdsStar) {
        return StringUtils.splitToInt(attrValueIdsStar, ",");
    }

    @Named("translateAttrValueIdsFromList")
    default String translateAttrValueIdsFromList(List<Integer> attrValueIds) {
        return StringUtils.join(attrValueIds, ",");
    }

}
