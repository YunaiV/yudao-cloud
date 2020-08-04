package cn.iocoder.mall.product.convert;

import cn.iocoder.common.framework.util.StringUtil;
import cn.iocoder.mall.product.api.bo.*;
import cn.iocoder.mall.product.api.dto.ProductSkuAddOrUpdateDTO;
import cn.iocoder.mall.product.api.dto.ProductSpuAddDTO;
import cn.iocoder.mall.product.api.dto.ProductSpuUpdateDTO;
import cn.iocoder.mall.product.dataobject.ProductCategoryDO;
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

    @Named("translatePicUrlsFromString")
    default List<String> translatePicUrlsFromString(String picUrls) {
        return StringUtil.split(picUrls, ",");
    }

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
            @Mapping(source = "picUrls", target = "picUrls", ignore = true)
    })
    ProductSkuDetailBO.Spu convert3(ProductSpuDO spu);

    @Mappings({
            @Mapping(source = "attrs", target = "attrs", ignore = true)
    })
    ProductSpuDetailBO.Sku convert2(ProductSkuDO sku);

    @Mappings({
            @Mapping(source = "attrs", target = "attrs", ignore = true)
    })
    ProductSkuDetailBO convert3(ProductSkuDO sku);

    @Mappings({
//            @Mapping(source = "attrs", target = "attrs", ignore = true) // TODO 芋艿 后续补充
    })
    ProductSkuBO convert4(ProductSkuDO sku);



    @Mappings({}) // TODO 芋艿，后续细看下 mapstruct 的 API ，优化这块
    default List<ProductSkuDetailBO> convert3(List<ProductSkuDO> skus, List<ProductSpuDO> spus, List<ProductAttrAndValuePairBO> productAttrDetailBOs) {
        // 创建 ProductAttrDetailBO 的映射。其中，KEY 为 ProductAttrDetailBO.attrValueId ，即规格值的编号
        Map<Integer, ProductAttrAndValuePairBO> productAttrDetailBOMap = productAttrDetailBOs.stream().collect(
                Collectors.toMap(ProductAttrAndValuePairBO::getAttrValueId, productAttrDetailBO -> productAttrDetailBO));
        // 创建 ProductSpuDO 的映射
        Map<Integer, ProductSkuDetailBO.Spu> spuMap = spus.stream().collect(
                Collectors.toMap(ProductSpuDO::getId, spu -> ProductSpuConvert.this.convert3(spu).setPicUrls(StringUtil.split(spu.getPicUrls(), ","))));
        // 拼装结果
        List<ProductSkuDetailBO> spuDetailList = new ArrayList<>(skus.size());
        for (ProductSkuDO sku : skus) {
            // 创建 ProductSkuDetailBO 对象
            ProductSkuDetailBO skuDetail = ProductSpuConvert.this.convert3(sku)
                    .setAttrs(new ArrayList<>())
                    .setSpu(spuMap.get(sku.getSpuId()));
            spuDetailList.add(skuDetail);
            // 设置 ProductSpuDetailBO 的 attrs 规格属性
            List<String> attrs = StringUtil.split(sku.getAttrs(), ",");
            attrs.forEach(attr -> skuDetail.getAttrs().add(productAttrDetailBOMap.get(Integer.valueOf(attr))));
        }
        // 返回
        return spuDetailList;
    }



}
