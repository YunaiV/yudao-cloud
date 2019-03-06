package cn.iocoder.mall.product.convert;

import cn.iocoder.common.framework.util.StringUtil;
import cn.iocoder.mall.product.api.bo.ProductAttrAndValuePairBO;
import cn.iocoder.mall.product.api.bo.ProductSkuDetailBO;
import cn.iocoder.mall.product.api.bo.ProductSpuBO;
import cn.iocoder.mall.product.api.bo.ProductSpuDetailBO;
import cn.iocoder.mall.product.api.dto.ProductSkuAddOrUpdateDTO;
import cn.iocoder.mall.product.api.dto.ProductSpuAddDTO;
import cn.iocoder.mall.product.api.dto.ProductSpuUpdateDTO;
import cn.iocoder.mall.product.dataobject.ProductSkuDO;
import cn.iocoder.mall.product.dataobject.ProductSpuDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper
public interface ProductSpuConvert {

    ProductSpuConvert INSTANCE = Mappers.getMapper(ProductSpuConvert.class);

    @Mappings({
            @Mapping(source = "picUrls", target = "picUrls", qualifiedByName = "translatePicUrlsFromString")
    })
    ProductSpuBO convert(ProductSpuDO spu);

    @Mappings({})
    List<ProductSpuBO> convert(List<ProductSpuDO> spus);

    @Mappings({
            @Mapping(source = "picUrls", target = "picUrls", ignore = true)
    })
    ProductSpuDO convert(ProductSpuAddDTO productSpuAddDTO);

    @Mappings({
            @Mapping(source = "attrs", target = "attrs", ignore = true)
    })
    ProductSkuDO convert(ProductSkuAddOrUpdateDTO productSkuAddDTO);


    @Mappings({
            @Mapping(source = "picUrls", target = "picUrls", ignore = true)
    })
    ProductSpuDO convert(ProductSpuUpdateDTO productSpuUpdateDTO);

    @Mappings({})
    ProductSpuDetailBO convert(ProductSpuBO spu);

    @Mappings({
            @Mapping(source = "picUrls", target = "picUrls", ignore = true)
    })
    ProductSpuDetailBO convert2(ProductSpuDO spu);

    @Mappings({
            @Mapping(source = "attrs", target = "attrs", ignore = true)
    })
    ProductSkuDetailBO convert2(ProductSkuDO sku);

    @Mappings({}) // TODO 芋艿，后续细看下 mapstruct 的 API ，优化这块
    default ProductSpuDetailBO convert2(ProductSpuDO spu, List<ProductSkuDO> skus, List<ProductAttrAndValuePairBO> productAttrDetailBOs) {
        // 创建并转换 ProductSpuDetailBO 对象
        ProductSpuDetailBO spuDetail = this.convert2(spu).setPicUrls(StringUtil.split(spu.getPicUrls(), ","));
        // 创建 ProductAttrDetailBO 的映射。其中，KEY 为 ProductAttrDetailBO.attrValueId ，即规格值的编号
        Map<Integer, ProductAttrAndValuePairBO> productAttrDetailBOMap = productAttrDetailBOs.stream().collect(
                Collectors.toMap(ProductAttrAndValuePairBO::getAttrValueId, productAttrDetailBO -> productAttrDetailBO));
        // 创建并转换 ProductSpuDetailBO 数组
        spuDetail.setSkus(new ArrayList<>());
        skus.forEach(sku -> {
            // 创建 ProductSpuDetailBO 对象
            ProductSkuDetailBO skuDetail = ProductSpuConvert.this.convert2(sku)
                    .setAttrs(new ArrayList<>());
            spuDetail.getSkus().add(skuDetail);
            // 设置 ProductSpuDetailBO 的 attrs 规格属性
            List<String> attrs = StringUtil.split(sku.getAttrs(), ",");
            attrs.forEach(attr -> skuDetail.getAttrs().add(productAttrDetailBOMap.get(Integer.valueOf(attr))));
        });
        // 返回
        return spuDetail;
    }

    @Named("translatePicUrlsFromString")
    default List<String> translatePicUrlsFromString(String picUrls) {
        return StringUtil.split(picUrls, ",");
    }

}